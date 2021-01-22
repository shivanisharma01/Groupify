package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.SecurityConfig;

public class StudentCSVImport {
	private List<String> successResults;
	private List<String> failureResults;
	private Course course;
	private IUserPersistence userDB;
	private IPasswordEncryption passwordEncryption;
	private IStudentCSVParser parser;
	private UserNotification notify;

	public StudentCSVImport(IStudentCSVParser parser, Course course) {
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
		this.course = course;
		successResults = courseAbstractFactory.returnStringListInstance();
		failureResults = courseAbstractFactory.returnStringListInstance();
		userDB =UserConfiguration.instance().getUserDB();
		passwordEncryption = SecurityConfig.instance().getPasswordEncryption();
		this.parser = parser;
		enrollStudentFromRecord();
	}

	private void enrollStudentFromRecord() {
		IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
		List<User> studentList = parser.parseCSVFile(failureResults);
		notify = (UserNotification) userAbstractFactory.returnUserNotification();
		for (User u : studentList) {
			String bannerID = u.getBanner();
			String firstName = u.getFirstName();
			String lastName = u.getLastName();
			String email = u.getEmail();
			String userDetails = bannerID + " " + firstName + " " + lastName + " " + email;

			User user = userAbstractFactory.returnUserInstance();
			userDB.loadUserByBannerID(bannerID, user);
			if (!user.isValidUser()) {
				user.setBannerID(bannerID);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				if (user.createUser(userDB, passwordEncryption, notify)) {
					successResults.add("Created: " + userDetails);
					userDB.loadUserByBannerID(bannerID, user);
				} else {
					failureResults.add("Unable to save this user to DB: " + userDetails);
					return;
				}
			}
			if (course.enrollUserInCourse(Role.STUDENT, user)) {
				successResults.add("User enrolled in course: " + userDetails);
			} else {
				failureResults.add("Unable to enroll user in course: " + userDetails);
			}
		}
	}

	public List<String> getSuccessResults() {
		return successResults;
	}

	public List<String> getFailureResults() {
		return failureResults;
	}
}
