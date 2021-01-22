package CSCI5308.GroupFormationTool.CoursesTest;


public class CourseConfigurationTest {

	private static CourseConfigurationTest uniqueInstance = null;
	private ICourseAbstractFactoryTest courseAbstractFactory;
	
	private CourseConfigurationTest() {
		courseAbstractFactory = new CourseAbstractFactoryTest();
    }
	
	public static CourseConfigurationTest instance() {

        if (uniqueInstance == null) {
            return new CourseConfigurationTest();
        }
        return uniqueInstance;
    }
	
	public ICourseAbstractFactoryTest getCourseAbstractFactory() {
        return courseAbstractFactory;
    }
}
