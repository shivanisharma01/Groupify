package CSCI5308.GroupFormationTool.CoursesTest;

import java.util.List;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Courses.Role;

public interface ICourseAbstractFactoryTest {

	Course returnCourseInstance();
	Course returnCourseInstance(int value, ICoursePersistence courseDB);
	ICoursePersistence returnCourseDBMock();
	List<Course> returnCourseListInstance();
	CSVReaderMock returnCSVReaderMockInstance();
	ICourseUserRelationshipPersistence returnCourseUserRelationshipDBMock();
	List<String[]> returnStringListInstance();
	List<Role> returnRoleListInstance();
	List<String> returnListInstance();
}
