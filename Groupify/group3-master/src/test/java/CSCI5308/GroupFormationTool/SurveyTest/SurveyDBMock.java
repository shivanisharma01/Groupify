package CSCI5308.GroupFormationTool.SurveyTest;

import java.util.Date;
import java.util.List;
import java.util.Stack;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.QuestionsTest.IQuestionAbstractFactoryTest;
import CSCI5308.GroupFormationTool.QuestionsTest.QuestionsConfigurationTest;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.SurveyDetails;
import CSCI5308.GroupFormationTool.Survey.SurveyResponse;

public class SurveyDBMock implements ISurveyPersistence {

	@Override
	public Stack<SurveyDetails> loadSurveyQuestions(long courseId) {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance()
				.getSurveyAbstractFactory();
		int ifPublished = 0;

		Date d1 = surveyAbstractFactoryTest.returnDateInstance(2020, 01, 01);
		Stack<SurveyDetails> surveyDetails = new Stack<SurveyDetails>();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setCourseId(1);
		survey.setQuestionId(1);
		survey.setCreationDate(d1);
		survey.setIfPublished(ifPublished);
		survey.setQuestionText("How is the course");
		survey.setQuestionTitle("SDC");
		survey.setQuestionTypeID(1);
		surveyDetails.add(survey);
		return surveyDetails;
	}

	@Override
	public boolean addQuestionToSurvey(SurveyDetails surveyQuestion) {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance()
				.getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setCourseId(1);
		survey.setQuestionId(1);
		return true;
	}

	@Override
	public boolean publishSurvey(long courseId) {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance()
				.getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setCourseId(1);
		return true;
	}

	@Override
	public boolean removeQuestionFromSurvey(SurveyDetails surveyQuestion) {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance()
				.getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setCourseId(1);
		survey.setQuestionId(1);
		return true;
	}

	@Override
	public boolean checkIfCourseContainsSurvey(SurveyDetails surveyDetail) {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance()
				.getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setCourseId(1);
		survey.setQuestionId(1);
		survey.setIfPublished(1);
		return true;
	}

	@Override
	public boolean checkIfSurveyPublished(SurveyDetails surveyDetail) {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance()
				.getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setCourseId(1);
		survey.setQuestionId(1);
		survey.setIfPublished(1);
		return true;
	}

	@Override
	public long findSurveyInstructor(long courseID) {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance()
				.getSurveyAbstractFactory();
		SurveyDetails survey = surveyAbstractFactoryTest.returnSurveyDetails();
		survey.setCourseId(1);
		survey.setQuestionId(1);
		survey.setIfPublished(1);
		return 0;
	}

	public boolean checkIfSurveyExist(Course course) {
		course.setId(1);
		course.getId();
		return true;
	}

	@Override
	public List<Question> getSurveyQuestions(Course course) {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		List<Question> questions = questionAbstractFactoryTest.returnQuestionStack();
		course.setId(1);
		course.getId(); 
		questions.add(question);
		return questions;
	}

	@Override
	public boolean uploadResponses(SurveyResponse surveyResponse) {
		ISurveyAbstractFactoryTest surveyAbstractFactoryTest = SurveyConfigurationTest.instance().getSurveyAbstractFactory();
		SurveyResponse survey = surveyAbstractFactoryTest.returnSurveyResponseInstance();
		survey.setCourseId(1);
		survey.setUserId(1);
		return true;
	}

	@Override
	public boolean checkSurveyTaken(Course course, Integer userId) {
		return false;
	}
}
