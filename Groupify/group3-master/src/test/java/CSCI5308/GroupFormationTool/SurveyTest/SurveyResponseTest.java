package CSCI5308.GroupFormationTool.SurveyTest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.Survey.SurveyAnswer;
import CSCI5308.GroupFormationTool.Survey.SurveyResponse;

@SpringBootTest
@SuppressWarnings("deprecation")
public class SurveyResponseTest {

	@Test
	public void setCourseIDTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();

		SurveyResponse response = surveyAbstractFactoryTest.returnSurveyResponseInstance();
		response.setCourseId(10);
		Assert.isTrue(10==response.getCourseId());
	}
	
	@Test
	public void getCourseIDTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();

		SurveyResponse response = surveyAbstractFactoryTest.returnSurveyResponseInstance();
		response.setCourseId(10);
		Assert.isTrue(10==response.getCourseId());
	}
	
	@Test
	public void getUserId() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyResponse response = surveyAbstractFactoryTest.returnSurveyResponseInstance();
		response.setUserId(1);
		Assert.isTrue(1==response.getUserId());
	}
	
	@Test
	public void setUserId() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyResponse response = surveyAbstractFactoryTest.returnSurveyResponseInstance();
		response.setUserId(1);
		Assert.isTrue(1==response.getUserId());
	}
	
	@Test
	public void setAnswersTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyResponse response = surveyAbstractFactoryTest.returnSurveyResponseInstance();
		SurveyAnswer ans = surveyAbstractFactoryTest.returnSurveyAnswerInstance();
		List<SurveyAnswer> answers = surveyAbstractFactoryTest.returnSurveyAnswerListInstance();
		answers.add(ans);
		response.setAnswers(answers);
		Assert.isTrue(response.getAnswers().equals(answers));
	}
	
	@Test
	public void getAnswersTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyResponse response = surveyAbstractFactoryTest.returnSurveyResponseInstance();
		SurveyAnswer ans = surveyAbstractFactoryTest.returnSurveyAnswerInstance();
		List<SurveyAnswer> answers = surveyAbstractFactoryTest.returnSurveyAnswerListInstance();
		answers.add(ans);
		response.setAnswers(answers);
		Assert.isTrue(response.getAnswers().equals(answers));
	}
}
