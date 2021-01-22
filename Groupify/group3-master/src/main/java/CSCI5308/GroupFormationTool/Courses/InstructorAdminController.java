package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import CSCI5308.GroupFormationTool.Survey.ISurveyAbstractFactory;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.SurveyConfiguration;
import CSCI5308.GroupFormationTool.Survey.SurveyDetails;

@Controller
public class InstructorAdminController {
	private static final String ID = "id";
	private static final String FILE = "file";
	private static final String SUCCESSFUL = "successful";
	private static final String FAILURES = "failures";
	private static final String DISPLAY_RESULTS = "displayresults";
	Logger logger = LoggerFactory.getLogger(InstructorAdminController.class);

	@GetMapping("/course/instructoradmin")
	public String instructorAdmin(Model model, @RequestParam(name = ID) long courseID) {
		
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
		ISurveyAbstractFactory surveyAbstractFactory = SurveyConfiguration.instance().getSurveyAbstractFactory();
		boolean surveyExists = false, surveyPublished = false;
		ICoursePersistence courseDB = CourseConfiguration.instance().getCourseDB();
		ISurveyPersistence surveyDB = SurveyConfiguration.instance().getSurveyDB();
		Course course = courseAbstractFactory.returnCourseInstance();
		SurveyDetails surveyDetail = surveyAbstractFactory.returnSurveyDetailsInstance();

		course.setId(courseID);
		course.loadCourseByID(courseDB);
		surveyDetail.setCourseId(courseID);
		surveyExists = surveyDetail.checkIfCourseContainsSurvey(surveyDB);
		surveyPublished = surveyDetail.checkIfSurveyPublished(surveyDB);
		model.addAttribute("course", course);
		model.addAttribute("displayresults", false);
		model.addAttribute("surveyExists", surveyExists);
		model.addAttribute("surveyPublished", surveyPublished);
		model.addAttribute("instructor", course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR));
		model.addAttribute("ta", course.isCurrentUserEnrolledAsRoleInCourse(Role.TA));
		if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR)
				|| course.isCurrentUserEnrolledAsRoleInCourse(Role.TA)) {
			logger.info("Instructor page is accessed");
			return "course/instructoradmin";
		} else {
			logger.info("User session is terminated");
			return "logout";
		}
	}

	@GetMapping("/course/instructoradminresults")
	public String instructorAdmin(Model model, @RequestParam(name = ID) long courseID,
			@RequestParam(name = SUCCESSFUL, required = false) List<String> successful,
			@RequestParam(name = FAILURES, required = false) List<String> failures,
			@RequestParam(name = DISPLAY_RESULTS) boolean displayResults) {
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
		ICoursePersistence courseDB = CourseConfiguration.instance().getCourseDB();
		Course course = courseAbstractFactory.returnCourseInstance();
		course.setId(courseID);
		course.loadCourseByID(courseDB);
		model.addAttribute("course", course);
		model.addAttribute("displayresults", false);
		model.addAttribute(SUCCESSFUL, successful);
		model.addAttribute(FAILURES, failures);
		model.addAttribute(DISPLAY_RESULTS, displayResults);
		if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR)
				|| course.isCurrentUserEnrolledAsRoleInCourse(Role.TA)) {
			return "course/instructoradmin";
		} else {
			logger.info("User session is terminated");
			return "logout";
		}
	}

	@GetMapping("/course/enrollta")
	public String enrollTA(Model model, @RequestParam(name = ID) long courseID) {
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
		ICoursePersistence courseDB = CourseConfiguration.instance().getCourseDB();
		Course course = courseAbstractFactory.returnCourseInstance();
		course.setId(courseID);
		course.loadCourseByID(courseDB);
		//courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR)
				|| course.isCurrentUserEnrolledAsRoleInCourse(Role.TA)) {
			return "course/enrollta";
		} else {
			logger.info("User session is terminated");
			return "logout";
		}
	}

	@RequestMapping(value = "/course/uploadcsv", consumes = { "multipart/form-data" })
	public ModelAndView upload(@RequestParam(name = FILE) MultipartFile file, @RequestParam(name = ID) long courseID) {
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
		ICoursePersistence courseDB = CourseConfiguration.instance().getCourseDB();
		Course course = courseAbstractFactory.returnCourseInstance();
		course.setId(courseID);
		course.loadCourseByID(courseDB);
		logger.info("Uploading CSV file");
		IStudentCSVParser parser = courseAbstractFactory.returnStudentCSVParserInstance(file);
		StudentCSVImport importer = courseAbstractFactory.returnStudentCSVImportInstance(parser, course);
		ModelAndView mav = courseAbstractFactory.returnModelAndViewInstance("redirect:/course/instructoradminresults?id=" + Long.toString(courseID));
		mav.addObject("successful", importer.getSuccessResults());
		mav.addObject("failures", importer.getFailureResults());
		mav.addObject("displayresults", true);
		return mav;
	}
}
