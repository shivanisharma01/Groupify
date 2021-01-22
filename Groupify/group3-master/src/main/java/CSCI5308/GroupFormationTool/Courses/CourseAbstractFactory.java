package CSCI5308.GroupFormationTool.Courses;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class CourseAbstractFactory implements ICourseAbstractFactory {

	@Override
	public Course returnCourseInstance() {
		return new Course();
	}

	@Override
	public List<Course> returnCourseListInstance() {
		return new ArrayList<Course>();
	}

	@Override
	public ModelAndView returnModelAndViewInstance(String mapping) {
		return new ModelAndView(mapping);
	}

	@Override
	public List<Role> returnRoleListInstance() {
		return new ArrayList<Role>();
	}

	@Override
	public IStudentCSVParser returnStudentCSVParserInstance(MultipartFile file) {
		return new StudentCSVParser(file);
	}

	@Override
	public StudentCSVImport returnStudentCSVImportInstance(IStudentCSVParser parser, Course course) {
		return new StudentCSVImport(parser, course);
	}

	@Override
	public ICoursePersistence returnCourseDB() {
		return new CourseDB();
	}

	@Override
	public ICourseUserRelationship returnCourseUserRelationship() {
		return new CourseUserRelationship();
	}

	@Override
	public ICourseUserRelationshipPersistence returnCourseUserRelationshipDB() {
		return new CourseUserRelationshipDB();
	}

	@Override
	public List<String> returnStringListInstance() {
		return new ArrayList<String>();
	}

	@Override
	public Reader returnInputStreamReaderInstance(InputStream in) {
		return new InputStreamReader(in);
	}

	@Override
	public CSVReader returnCSVReaderBuilderInstance(Reader reader) {
		return new CSVReaderBuilder(reader).build();
	}

}
