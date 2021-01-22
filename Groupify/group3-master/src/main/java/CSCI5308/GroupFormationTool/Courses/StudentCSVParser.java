package CSCI5308.GroupFormationTool.Courses;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import CSCI5308.GroupFormationTool.AccessControl.IUserAbstractFactory;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControl.UserConfiguration;

public class StudentCSVParser implements IStudentCSVParser {

	private MultipartFile uploadedFile;
	private List<User> studentList = new ArrayList<>();
	public StudentCSVParser(MultipartFile file) {
		this.uploadedFile = file;
	}

	@Override
	public List<User> parseCSVFile(List<String> failureResults) {
		try {
			IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
			ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
			Reader reader = courseAbstractFactory.returnInputStreamReaderInstance(uploadedFile.getInputStream());
			CSVReader csvReader = courseAbstractFactory.returnCSVReaderBuilderInstance(reader);
			List<String[]> records = csvReader.readAll();
			Iterator<String[]> iter = records.iterator();
			User u;
			while (iter.hasNext()) {
				String[] record = iter.next();
				String bannerID = record[0];
				String firstName = record[1];
				String lastName = record[2];
				String email = record[3];
				u = userAbstractFactory.returnUserInstance();
				u.setBannerID(bannerID);
				u.setFirstName(firstName);
				u.setLastName(lastName);
				u.setEmail(email);
				studentList.add(u);
			}

		} catch (IOException e) {
			failureResults.add("Failure reading uploaded file: " + e.getMessage());
		} catch (Exception e) {
			failureResults.add("Failure parsing CSV file: " + e.getMessage());
		}

		return studentList;

	}

}
