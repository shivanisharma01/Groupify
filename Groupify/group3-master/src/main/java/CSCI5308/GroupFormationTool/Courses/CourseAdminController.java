package CSCI5308.GroupFormationTool.Courses;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import CSCI5308.GroupFormationTool.AccessControl.IUserAbstractFactory;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControl.UserConfiguration;

@Controller
public class CourseAdminController {
	private static final String ID = "id";
	private static final String TITLE = "title";
	private static final String INSTRUCTOR = "instructor";
	Logger logger = LoggerFactory.getLogger(CourseAdminController.class);

	@GetMapping("/admin/course")
	public String course(Model model) {
		ICoursePersistence courseDB = CourseConfiguration.instance().getCourseDB();
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
		Course c = courseAbstractFactory.returnCourseInstance();
		List<Course> allCourses = c.loadAllCourses(courseDB);
		model.addAttribute("courses", allCourses);
		logger.info("Redirected to Course Page");
		return "admin/course";
	}

	@GetMapping("/admin/assigninstructor")
	public String assignInstructor(Model model, @RequestParam(name = ID) long courseID) {
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
		ICoursePersistence courseDB = CourseConfiguration.instance().getCourseDB();
		Course course = courseAbstractFactory.returnCourseInstance();
		course.setId(courseID);
		course.loadCourseByID(courseDB);
		model.addAttribute("course", course);
		ICourseUserRelationshipPersistence courseUserRelationshipDB = CourseConfiguration.instance().getCourseUserRelationshipDB();
		List<User> allUsersNotCurrentlyInstructors = courseUserRelationshipDB
				.findAllUsersWithoutCourseRole(Role.INSTRUCTOR, courseID);
		model.addAttribute("users", allUsersNotCurrentlyInstructors);
		logger.info("Redirected to Assign Instructor Page");
		return "admin/assigninstructor";
	}

	@GetMapping("/admin/deletecourse")
	public ModelAndView deleteCourse(@RequestParam(name = ID) long courseID) {
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
		ICoursePersistence courseDB = CourseConfiguration.instance().getCourseDB();
		Course course = courseAbstractFactory.returnCourseInstance();
		course.setId(courseID);
		course.delete(courseDB);
		ModelAndView modelAndview = courseAbstractFactory.returnModelAndViewInstance("redirect:/admin/course");
		logger.info("Delete Course Page is accessed");
		return modelAndview;
	}

	@RequestMapping(value = "/admin/createcourse", method = RequestMethod.POST)
	public ModelAndView createCourse(@RequestParam(name = TITLE) String title) {
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
		ICoursePersistence courseDB = CourseConfiguration.instance().getCourseDB();
		Course course = courseAbstractFactory.returnCourseInstance();
		course.setTitle(title);
		course.createCourse(courseDB);
		ModelAndView modelAndview = courseAbstractFactory.returnModelAndViewInstance("redirect:/admin/course");
		logger.info("Create Course Page is accessed");
		return modelAndview;
	}

	@RequestMapping(value = "/admin/assigninstructor", method = RequestMethod.POST)
	public ModelAndView assignInstructorToCourse(@RequestParam(name = INSTRUCTOR) List<Integer> instructor,
			@RequestParam(name = ID) long courseID) {
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
		IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
		Course course = courseAbstractFactory.returnCourseInstance();
		course.setId(courseID);
		Iterator<Integer> iterator = instructor.iterator();
		ICourseUserRelationshipPersistence courseUserRelationshipDB = CourseConfiguration.instance().getCourseUserRelationshipDB();
		while (iterator.hasNext()) {
			User user = userAbstractFactory.returnUserInstance();
			user.setId(iterator.next().longValue());
			courseUserRelationshipDB.enrollUser(course, user, Role.INSTRUCTOR);
		}
		ModelAndView modelAndview = courseAbstractFactory.returnModelAndViewInstance("redirect:/admin/course");
		logger.info("Assign Instructor Page is active");
		return modelAndview;
	}
}