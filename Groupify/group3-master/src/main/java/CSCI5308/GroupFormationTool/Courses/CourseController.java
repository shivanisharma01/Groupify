package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.SurveyConfiguration;

@Controller
public class CourseController {
	private static final String ID = "id";
	Logger logger = LoggerFactory.getLogger(CourseController.class);
	@GetMapping("/course/course")
	public String course(Model model, @RequestParam(name = ID) long courseID) {
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
		ICoursePersistence courseDB =  CourseConfiguration.instance().getCourseDB();
		ISurveyPersistence surveyDetailsDB = SurveyConfiguration.instance().getSurveyDB();
		Course course = courseAbstractFactory.returnCourseInstance();
		course.setId(courseID);
		course.loadCourseByID(courseDB);
		logger.info(course.getTitle()+" details are fetched");
		model.addAttribute("course", course);
		List<Role> userRoles = course.getAllRolesForCurrentUserInCourse();
		model.addAttribute("surveyExist", surveyDetailsDB.checkIfSurveyExist(course));
		if (null == userRoles) {
			model.addAttribute("instructor", false);
			model.addAttribute("ta", false);
			model.addAttribute("student", false);
			model.addAttribute("guest", true);
		} else {
			logger.info("Fetching user roles for authorization");
			model.addAttribute("instructor", userRoles.contains(Role.INSTRUCTOR));
			model.addAttribute("ta", userRoles.contains(Role.TA));
			model.addAttribute("student", userRoles.contains(Role.STUDENT));
			model.addAttribute("guest", userRoles.isEmpty());
		}
		logger.info(course.getTitle()+" is accessed");
		return "course/course";
	}
}
