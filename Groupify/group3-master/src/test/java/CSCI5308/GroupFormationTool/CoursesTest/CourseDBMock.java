package CSCI5308.GroupFormationTool.CoursesTest;

import java.util.List;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;

public class CourseDBMock implements ICoursePersistence {
	public List<Course> loadAllCourses() {
		ICourseAbstractFactoryTest courseAbstractFactoryTest = CourseConfigurationTest.instance().getCourseAbstractFactory();
		List<Course> courseList = courseAbstractFactoryTest.returnCourseListInstance();
		Course course = courseAbstractFactoryTest.returnCourseInstance();
		course.setId(0);
		course.setTitle("Software Engineering");
		courseList.add(course);
		course = courseAbstractFactoryTest.returnCourseInstance();
		course.setId(1);
		course.setTitle("Advanced Topics in Software Development");
		courseList.add(course);
		return courseList;
	}

	public void loadCourseByID(long id, Course course) {
		course.setId(id);
		course.setTitle("Software Engineering");
	}

	public boolean createCourse(Course course) {
		course.setId(0);
		course.setTitle("Software Engineering");
		return true;
	}

	public boolean deleteCourse(long id) {
		ICourseAbstractFactoryTest courseAbstractFactoryTest = CourseConfigurationTest.instance().getCourseAbstractFactory();
		Course course = courseAbstractFactoryTest.returnCourseInstance();
		course.setId(id);
		course.setTitle("Software Engineering");
		course.setDefaults();
		return true;
	}

}
