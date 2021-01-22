package CSCI5308.GroupFormationTool.Courses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.AccessControl.IUserAbstractFactory;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControl.UserConfiguration;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Database.DatabaseConfig;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;

public class CourseUserRelationshipDB implements ICourseUserRelationshipPersistence {
	Logger logger = LoggerFactory.getLogger(CourseUserRelationshipDB.class);
	public List<User> findAllUsersWithoutCourseRole(Role role, long courseID) {
		IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		List<User> users = userAbstractFactory.returnUserListInstance();
		CallStoredProcedure proc = null;
		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spFindUsersWithoutCourseRole(?, ?)");
			proc.setParameter(1, role.toString());
			proc.setParameter(2, courseID);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					long userID = results.getLong(1);
					String bannerID = results.getString(2);
					String firstName = results.getString(3);
					String lastName = results.getString(4);
					User user = userAbstractFactory.returnUserInstance();
					user.setID(userID);
					user.setBannerID(bannerID);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					users.add(user);
				}
			}
		} catch (SQLException e) {
			logger.error("Error connecting to database",e);
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return users;
	}

	public List<User> findAllUsersWithCourseRole(Role role, long courseID) {
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();
		IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();

		List<User> users = userAbstractFactory.returnUserListInstance();

		CallStoredProcedure proc = null;
		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spFindUsersWithCourseRole(?, ?)");
			proc.setParameter(1, role.toString());
			proc.setParameter(2, courseID);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					long userID = results.getLong(1);
					User u = new User();
					u.setID(userID);
					users.add(u);
				}
			}
		} catch (SQLException e) {
			// Logging needed.
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return users;
	}

	public boolean enrollUser(Course course, User user, Role role) {
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		CallStoredProcedure proc = null;
		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spEnrollUser(?, ?, ?)");
			proc.setParameter(1, course.getId());
			proc.setParameter(2, user.getID());
			proc.setParameter(3, role.toString());
			proc.execute();
		} catch (SQLException e) {
			// Logging needed
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	public List<Role> loadUserRolesForCourse(Course course, User user) {
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		List<Role> roles = courseAbstractFactory.returnRoleListInstance();
		CallStoredProcedure proc = null;
		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spLoadUserRolesForCourse(?, ?)");
			proc.setParameter(1, course.getId());
			proc.setParameter(2, user.getID());
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					Role role = Role.valueOf(results.getString(1).toUpperCase());
					roles.add(role);
				}
			}
		} catch (SQLException e) {
			// Logging needed.
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return roles;
	}
}
