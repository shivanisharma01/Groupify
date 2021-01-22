package CSCI5308.GroupFormationTool.CoursesTest;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Courses.Role;

public class CourseAbstractFactoryTest implements ICourseAbstractFactoryTest {

	@Override
	public Course returnCourseInstance() {
		return new Course();
	}

	@Override
	public ICoursePersistence returnCourseDBMock() {
		return new CourseDBMock();
	}

	@Override
	public List<Course> returnCourseListInstance() {
		return new ArrayList<Course>();
	}

	@Override
	public CSVReaderMock returnCSVReaderMockInstance() {
		return new CSVReaderMock();
	}

	@Override
	public ICourseUserRelationshipPersistence returnCourseUserRelationshipDBMock() {
		return new CourseUserRelationshipDBMock();
	}

	@Override
	public List<String[]> returnStringListInstance() {
		return new ArrayList<String[]>();
	}

	@Override
	public Course returnCourseInstance(int value, ICoursePersistence courseDB) {
		return new Course(value, courseDB);
	}

	@Override
	public List<Role> returnRoleListInstance() {
		return new ArrayList<Role>();
	}

	@Override
	public List<String> returnListInstance() {
		return new ArrayList<String>();
	}

}
