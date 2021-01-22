package CSCI5308.GroupFormationTool.SurveyTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.CoursesTest.CourseConfigurationTest;
import CSCI5308.GroupFormationTool.CoursesTest.ICourseAbstractFactoryTest;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.QuestionsTest.IQuestionAbstractFactoryTest;
import CSCI5308.GroupFormationTool.QuestionsTest.QuestionsConfigurationTest;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.SurveyResponse;

@SpringBootTest
@SuppressWarnings("deprecation")
public class SurveyDetailsTest {
	
	@Test
	public void checkIfSurveyExistTest() {
		ICourseAbstractFactoryTest courseAbstractFactoryTest = CourseConfigurationTest.instance().getCourseAbstractFactory();
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		ISurveyPersistence detailsDB = surveyAbstractFactoryTest.returnSurveyDetailsDBMockInstance();
		Course course = courseAbstractFactoryTest.returnCourseInstance();
		boolean return_value = detailsDB.checkIfSurveyExist(course);
		Assert.isTrue(return_value);
	}
	
	@Test
	public void uploadResponsesTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		ISurveyPersistence detailsDB =surveyAbstractFactoryTest.returnSurveyDetailsDBMockInstance();
		SurveyResponse response = surveyAbstractFactoryTest.returnSurveyResponseInstance();
		boolean return_value = detailsDB.uploadResponses(response);
		Assert.isTrue(return_value);
	} 
	
	@Test
	public void getSurveyQuestionsTest(){
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		ICourseAbstractFactoryTest courseAbstractFactoryTest = CourseConfigurationTest.instance().getCourseAbstractFactory();

		ISurveyPersistence detailsDB = surveyAbstractFactoryTest.returnSurveyDetailsDBMockInstance();
		List<Question> questions = questionAbstractFactoryTest.returnQuestionStack();
		Course course = courseAbstractFactoryTest.returnCourseInstance();
		Question que = questionAbstractFactoryTest.returnQuestionInstance();
		questions.add(que);
		List<Question> return_value = detailsDB.getSurveyQuestions(course);
		assertThat(return_value);
	}
}
