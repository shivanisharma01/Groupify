package CSCI5308.GroupFormationTool.Courses;

public class CourseConfiguration {

	private static CourseConfiguration uniqueInstance = null;
	private ICourseAbstractFactory courseAbstractFactory;
	private ICoursePersistence courseDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	
	private CourseConfiguration() {
		courseAbstractFactory = new CourseAbstractFactory();
		courseDB = courseAbstractFactory.returnCourseDB();
		courseUserRelationshipDB = courseAbstractFactory.returnCourseUserRelationshipDB();
	}
	
	public static CourseConfiguration instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new CourseConfiguration();
		}
		return uniqueInstance;
	}
	
	public ICourseAbstractFactory getCourseAbstractFactory() {
        return courseAbstractFactory;
    }

    public void setCourseAbstractFactory(ICourseAbstractFactory courseAbstractFactory) {
        this.courseAbstractFactory = courseAbstractFactory;
    }
	
	public void setCourseDB(ICoursePersistence courseDB) {
		this.courseDB = courseDB;
	}

	public ICoursePersistence getCourseDB() {
		return courseDB;
	}
	
	public void setCourseUserRelationshipDB(ICourseUserRelationshipPersistence courseUserRelationshipDB) {
		this.courseUserRelationshipDB = courseUserRelationshipDB;
	}

	public ICourseUserRelationshipPersistence getCourseUserRelationshipDB() {
		return courseUserRelationshipDB;
	}
	
}
