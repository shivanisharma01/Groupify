package CSCI5308.GroupFormationTool.SurveyTest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.Questions.Options;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.QuestionsTest.IQuestionAbstractFactoryTest;
import CSCI5308.GroupFormationTool.QuestionsTest.QuestionsConfigurationTest;
import CSCI5308.GroupFormationTool.Survey.SurveyAnswer;

@SpringBootTest
@SuppressWarnings("deprecation")
public class SurveyAnswerTest {
	
	@Test
	public void setQuestionTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();

		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		SurveyAnswer answer = surveyAbstractFactoryTest.returnSurveyAnswerInstance();
		answer.setQuestion(question);
		Assert.isTrue(answer.getQuestion().equals(question));
	}
	
	@Test
	public void getQuestionTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();

		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		SurveyAnswer answer = surveyAbstractFactoryTest.returnSurveyAnswerInstance();
		answer.setQuestion(question);
		Assert.isTrue(answer.getQuestion().equals(question));
	}
	
	@Test
	public void setSingleAnswerTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();

		SurveyAnswer answer = surveyAbstractFactoryTest.returnSurveyAnswerInstance();
		answer.setSingleAnswer("I like this Course");
		Assert.isTrue(answer.getSingleAnswer().equals("I like this Course"));
	}
	
	@Test
	public void getSingleAnswerTest() {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();

		SurveyAnswer answer = surveyAbstractFactoryTest.returnSurveyAnswerInstance();
		answer.setSingleAnswer("I like this Course");
		Assert.isTrue(answer.getSingleAnswer().equals("I like this Course"));
	}
	
	@Test
	public void setMultipleAnswersTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();

		List<Options> multipleOptions = questionAbstractFactoryTest.returnOptionsListInstance();
		SurveyAnswer answer = surveyAbstractFactoryTest.returnSurveyAnswerInstance();
		answer.setMultipleAnswers(multipleOptions);
		Assert.isTrue(answer.getMultipleAnswers().equals(multipleOptions));
	}
	
	@Test
	public void getMultipleAnswersTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();

		List<Options> multipleOptions = questionAbstractFactoryTest.returnOptionsListInstance();
		SurveyAnswer answer = surveyAbstractFactoryTest.returnSurveyAnswerInstance();
		answer.setMultipleAnswers(multipleOptions);
		Assert.isTrue(answer.getMultipleAnswers().equals(multipleOptions));
	}
}
