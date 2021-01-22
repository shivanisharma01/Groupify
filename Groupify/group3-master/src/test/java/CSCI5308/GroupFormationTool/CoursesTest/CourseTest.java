package CSCI5308.GroupFormationTool.CoursesTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;

@SpringBootTest
@SuppressWarnings("deprecation")
class CourseTest {
	@Test
	public void ConstructorTests() {
		ICourseAbstractFactoryTest courseAbstractFactoryTest = CourseConfigurationTest.instance().getCourseAbstractFactory();
		Course course = courseAbstractFactoryTest.returnCourseInstance();
		Assert.isTrue(course.getId() == -1);
		Assert.isTrue(course.getTitle().isEmpty());
		ICoursePersistence courseDB = courseAbstractFactoryTest.returnCourseDBMock();
		course = courseAbstractFactoryTest.returnCourseInstance(0, courseDB);
		Assert.isTrue(course.getId() == 0);
		Assert.isTrue(course.getTitle().equals("Software Engineering"));
	}

	@Test
	public void setIdTest() {
		ICourseAbstractFactoryTest courseAbstractFactoryTest = CourseConfigurationTest.instance().getCourseAbstractFactory();
		Course course = courseAbstractFactoryTest.returnCourseInstance();
		course.setId(7);
		Assert.isTrue(course.getId() == 7);
	}

	@Test
	public void getIdTest() {
		ICourseAbstractFactoryTest courseAbstractFactoryTest = CourseConfigurationTest.instance().getCourseAbstractFactory();
		Course course = courseAbstractFactoryTest.returnCourseInstance();
		course.setId(7);
		Assert.isTrue(course.getId() == 7);
	}

	@Test
	public void setTitleTest() {
		ICourseAbstractFactoryTest courseAbstractFactoryTest = CourseConfigurationTest.instance().getCourseAbstractFactory();
		Course course = courseAbstractFactoryTest.returnCourseInstance();
		course.setTitle("Advanced Topics in Software Development");
		Assert.isTrue(course.getTitle().equals("Advanced Topics in Software Development"));
	}

	@Test
	public void getTitleTest() {
		ICourseAbstractFactoryTest courseAbstractFactoryTest = CourseConfigurationTest.instance().getCourseAbstractFactory();
		Course course = courseAbstractFactoryTest.returnCourseInstance();
		course.setTitle("Advanced Topics in Software Development");
		Assert.isTrue(course.getTitle().equals("Advanced Topics in Software Development"));
	}

	@Test
	public void deleteCourseTest() {
		ICourseAbstractFactoryTest courseAbstractFactoryTest = CourseConfigurationTest.instance().getCourseAbstractFactory();
		ICoursePersistence courseDB = courseAbstractFactoryTest.returnCourseDBMock();
		boolean status = courseDB.deleteCourse(0);
		Assert.isTrue(status);
	}

	@Test
	public void createCourseTest() {
		ICourseAbstractFactoryTest courseAbstractFactoryTest = CourseConfigurationTest.instance().getCourseAbstractFactory();
		ICoursePersistence courseDB = courseAbstractFactoryTest.returnCourseDBMock();
		Course course = courseAbstractFactoryTest.returnCourseInstance();
		course.setId(0);
		course.setTitle("Software Engineering");
		courseDB.createCourse(course);
		Assert.isTrue(course.getId() == 0);
		Assert.isTrue(course.getTitle().equals("Software Engineering"));
	}

}
