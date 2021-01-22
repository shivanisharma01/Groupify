package CSCI5308.GroupFormationTool.SurveyTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Objects;
import java.util.Stack;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.SurveyDetails;

@SpringBootTest
@SuppressWarnings("deprecation")
public class SurveyTest {
	
	@Test
	public void ConstructorTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		Date date = surveyAbstractFactoryTest.returnDateInstance();
		Objects.equals(date, "2020-02-03");
		Assert.isTrue(survey.getCourseId()==-1);
		Assert.isTrue(survey.getQuestionId()==-1);
		Assert.isTrue(survey.getQuestionTypeID()==-1);
		Assert.isTrue(survey.getQuestionText().isEmpty());
		Assert.isTrue(survey.getQuestionTitle().isEmpty());
		Assert.isTrue(survey.getOptions().isEmpty());
		Assert.isTrue(survey.getCreationDate().equals(date));
	}
	
	@Test
	public void setQuestionIDTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setQuestionId(1);
		Assert.isTrue(1==survey.getQuestionId());
	}
	
	@Test
	public void getQuestionIDTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setQuestionId(1);
		Assert.isTrue(1==survey.getQuestionId());
	}
	
	@Test
	public void setCourseIdTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setCourseId(1);
		Assert.isTrue(1==survey.getCourseId());
	}
	
	@Test
	public void getCourseIdTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setCourseId(1);
		Assert.isTrue(1==survey.getCourseId());
	}
	
	@Test
	public void setQuestionTypeIdTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setQuestionTypeID(1);
		Assert.isTrue(1==survey.getQuestionTypeID());
	}
	
	@Test
	public void getQuestionTypeIdTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setQuestionTypeID(1);
		Assert.isTrue(1==survey.getQuestionTypeID());
	}
	
	@Test
	public void setQuestionTitleTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setQuestionTitle("SDC");
		Assert.isTrue(survey.getQuestionTitle().equals("SDC"));
	}
	
	@Test
	public void getQuestionTitleTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setQuestionTitle("SDC");
		Assert.isTrue(survey.getQuestionTitle().equals("SDC"));
	}
	
	@Test
	public void getQuestionTextTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setQuestionText("How is the Course");
		Assert.isTrue(survey.getQuestionText().equals("How is the Course"));
	}
	
	@Test
	public void setQuestionTextTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setQuestionText("How is the Course");
		Assert.isTrue(survey.getQuestionText().equals("How is the Course"));
	}
	
	@Test
	public void setCreationDateTest(){
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		Date date = surveyAbstractFactoryTest.returnDateInstance();
		date.equals("2020-02-03");
		survey.setCreationDate(date);
		Assert.isTrue(survey.getCreationDate().equals(date));
	}
	
	@Test
	public void getCreationDateTest(){
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		Date date = surveyAbstractFactoryTest.returnDateInstance();
		date.equals("2020-02-03");
		survey.setCreationDate(date);
		Assert.isTrue(survey.getCreationDate().equals(date));
	}
	
	@Test
	public void setIfPublishedTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setIfPublished(1);
		Assert.isTrue(1==survey.getIfPublished());
	}
	
	@Test
	public void getIfPublishedTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setIfPublished(1);
		Assert.isTrue(1==survey.getIfPublished());
	}
	
	@Test
	public void loadSurveyQuestions(){
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		ISurveyPersistence surveyPersistence = surveyAbstractFactoryTest.returnSurveyDBMock();
		Stack<SurveyDetails> surveyDetails = surveyPersistence.loadSurveyQuestions(1);
		Assert.isTrue(surveyDetails.size()>0);
		Assert.isTrue(surveyDetails.get(0).getCourseId() == 1);
	}
	
	@Test
	public void addQuestionToSurveyTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		ISurveyPersistence surveyPersistence = surveyAbstractFactoryTest.returnSurveyDBMock();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		boolean return_value = surveyPersistence.addQuestionToSurvey(survey);
		Assert.isTrue(return_value);
	}
	
	@Test
	public void publishSurveyTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		ISurveyPersistence surveyPersistence = surveyAbstractFactoryTest.returnSurveyDBMock();
		boolean return_value = surveyPersistence.publishSurvey(1);
		Assert.isTrue(return_value);
	}
	
	@Test
	public void removeQuestionFromSurveyTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		ISurveyPersistence surveyPersistence = surveyAbstractFactoryTest.returnSurveyDBMock();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();	
		boolean return_value = surveyPersistence.removeQuestionFromSurvey(survey);
		Assert.isTrue(return_value);
	}
	
	@Test
	public void findSurveyInstructorTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		ISurveyPersistence surveyPersistence = surveyAbstractFactoryTest.returnSurveyDBMock();
		long return_value = surveyPersistence.findSurveyInstructor(1);
		assertEquals(0, return_value);
	}
	
	@Test
	public void checkIfCoursehasSurveyTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		ISurveyPersistence surveyPersistence = surveyAbstractFactoryTest.returnSurveyDBMock();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();	
		boolean return_value = surveyPersistence.checkIfCourseContainsSurvey(survey);
		Assert.isTrue(return_value);
	}
	
	@Test
	public void checkIfSurveyPublished() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		ISurveyPersistence surveyPersistence = surveyAbstractFactoryTest.returnSurveyDBMock();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();	
		boolean return_value = surveyPersistence.checkIfSurveyPublished(survey);
		Assert.isTrue(return_value);
	}
}
