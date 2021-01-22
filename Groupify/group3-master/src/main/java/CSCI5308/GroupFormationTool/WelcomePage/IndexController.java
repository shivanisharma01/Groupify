package CSCI5308.GroupFormationTool.WelcomePage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUserAbstractFactory;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControl.UserConfiguration;
import CSCI5308.GroupFormationTool.Courses.*;
import CSCI5308.GroupFormationTool.Security.SecurityConfig;

@Controller
public class IndexController {
	@GetMapping("/")
	public String greeting(Model model) {
		IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityConfig.instance().getPasswordPolicyObj();
		if (authentication.isAuthenticated()) {
			User user = userAbstractFactory.returnUserInstance();
			IUserPersistence userDB = UserConfiguration.instance().getUserDB();
			ICoursePersistence courseDB = CourseConfiguration.instance().getCourseDB();
			List<Course> allCourses = courseDB.loadAllCourses();
			userDB.loadUserByBannerID(authentication.getPrincipal().toString(), user);
			model.addAttribute("userID", user.getID());
			model.addAttribute("courses", allCourses);
		}
		return "index";
	}
}