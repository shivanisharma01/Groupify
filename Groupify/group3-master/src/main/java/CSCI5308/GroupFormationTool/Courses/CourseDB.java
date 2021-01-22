package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Database.DatabaseConfig;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDB implements ICoursePersistence {

	Logger logger = LoggerFactory.getLogger(CourseDB.class);

	public List<Course> loadAllCourses() {
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();
		List<Course> courses = courseAbstractFactory.returnCourseListInstance();
		CallStoredProcedure proc = null;
		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spLoadAllCourses()");
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					long id = results.getLong(1);
					String title = results.getString(2);
					Course course = courseAbstractFactory.returnCourseInstance();
					course.setId(id);
					course.setTitle(title);
					courses.add(course);
				}
				logger.info("All courses are loaded to main page");
			}
		} catch (SQLException e) {
			logger.error("Loading Courses Failed", e);
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return courses;
	}

	public void loadCourseByID(long id, Course course) {
		CallStoredProcedure proc = null;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spFindCourseByID(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					String title = results.getString(2);
					course.setId(id);
					course.setTitle(title);
				}
				logger.info("Course with "+id+" is loaded");
			}
		} catch (SQLException e) {
			logger.error("Loading Course Failed", e);
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
	}

	public boolean createCourse(Course course) {
		CallStoredProcedure proc = null;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spCreateCourse(?, ?)");
			proc.setParameter(1, course.getTitle());
			proc.registerOutputParameterLong(2);
			proc.execute();
		} catch (SQLException e) {
			logger.error("Course Creation Failed", e);
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		logger.info("Course is created");
		return true;
	}

	public boolean deleteCourse(long id) {
		CallStoredProcedure proc = null;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spDeleteCourse(?)");
			proc.setParameter(1, id);
			proc.execute();
		} catch (SQLException e) {
			logger.error("Course Deletion Failed", e);
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		logger.info("Course with "+id+" is deleted");
		return true;
	}
}
