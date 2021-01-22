package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.AccessControl.*;

public class CourseUserRelationship implements ICourseUserRelationship {

	Logger logger = LoggerFactory.getLogger(CurrentUser.class);
	
	public boolean userHasRoleInCourse(User user, Role role, Course course) {
		if (null == user || !user.isValidUser()) {
			return false;
		}
		if (null == role) {
			return false;
		}
		if (null == course) {
			return false;
		}
		ICourseUserRelationshipPersistence userCourseRelationshipDB = CourseConfiguration.instance().getCourseUserRelationshipDB();
		List<Role> roles = userCourseRelationshipDB.loadUserRolesForCourse(course, user);
		if (null != roles && roles.contains(role)) {
			logger.info(user.getBannerID()+" role is verified");
			return true;
		}
		logger.error(user.getBannerID()+" role verification failed");
		return false;
	}

	public List<Role> loadAllRoluesForUserInCourse(User user, Course course) {
		ICourseUserRelationshipPersistence userCourseRelationshipDB = CourseConfiguration.instance().getCourseUserRelationshipDB();
		List<Role> roles = userCourseRelationshipDB.loadUserRolesForCourse(course, user);
		logger.info("fetching roles for user "+user.getBannerID());
		return roles;
	}

	public boolean enrollUserInCourse(User user, Course course, Role role) {
		ICourseUserRelationshipPersistence userCourseRelationshipDB = CourseConfiguration.instance().getCourseUserRelationshipDB();
		return userCourseRelationshipDB.enrollUser(course, user, role);
	}
}
