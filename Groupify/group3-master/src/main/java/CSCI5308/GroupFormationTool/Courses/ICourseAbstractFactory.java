package CSCI5308.GroupFormationTool.Courses;

import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.opencsv.CSVReader;

public interface ICourseAbstractFactory {

	Course returnCourseInstance();
	List<Course> returnCourseListInstance();
	ModelAndView returnModelAndViewInstance(String mapping);
	List<Role> returnRoleListInstance();
	List<String> returnStringListInstance();
	IStudentCSVParser returnStudentCSVParserInstance(MultipartFile file);
	StudentCSVImport returnStudentCSVImportInstance(IStudentCSVParser parser, Course course);
	ICoursePersistence returnCourseDB();
	ICourseUserRelationship returnCourseUserRelationship();
	ICourseUserRelationshipPersistence returnCourseUserRelationshipDB();
	Reader returnInputStreamReaderInstance(InputStream in);
	CSVReader returnCSVReaderBuilderInstance(Reader reader);
}
