package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.Options;
import CSCI5308.GroupFormationTool.Questions.Question;

import java.util.*;

public class QuestionDBMock implements IQuestionPersistence {
	@Override
	public Stack<Question> loadAllQuestions(int instructorID, int sortFlag) {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Stack<Question> questions = questionAbstractFactoryTest.returnQuestionStack();
		Question questionbank = questionAbstractFactoryTest.returnQuestionInstance();
		Question questionbank1 = questionAbstractFactoryTest.returnQuestionInstance();
		questionbank.setQuestionID(1);
		questionbank.setQuestionTitle("Title");
		questionbank.setQuestionText("Question text");
		Map<Integer, String> Options = new HashMap<>();
		Options.put(1, "first Option");
		questionbank.setOptions(Options);
		questions.add(questionbank);
		questionbank1.setQuestionID(2);
		questionbank1.setQuestionTitle("Second Question");
		questionbank1.setQuestionText("Second Question Text");
		Map<Integer, String> Options2 = questionAbstractFactoryTest.returnMapInstance();
		Options2.put(2, "Second Option");
		questionbank1.setOptions(Options2);
		questions.add(questionbank1);
		return questions;
	}

	@Override
	public Question loadSingleQuestion(int questionID) {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question qu = questionAbstractFactoryTest.returnQuestionInstance();
		qu.setQuestionID(1);
		qu.setQuestionText("New Question Text");
		qu.setQuestionTitle("New Question");
		Map<Integer, String> Options = new HashMap<>();
		Options.put(1, "first Option");
		qu.setOptions(Options);
		return qu;
	}

	@Override
	public boolean createQuestion(Question question) {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Map<Integer, String> Options = questionAbstractFactoryTest.returnMapInstance();
		Options.put(1, "first Option");
		question.setQuestionText("How are the course content");
		question.setQuestionTitle("Ratings");
		question.setOptions(Options);
		return true;
	}

	@Override
	public boolean deleteQuestion(int questionID) {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		question.setQuestionID(1);
		question.setQuestionTitle("New Question");
		question.setQuestionText("New Option Text");
		return true;
	}

	@Override
	public List<Integer> checkQuestionAccess() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		question.setQuestionID(1);
		question.setInstructorID(1);
		question.setQuestionText("New Questions Text");
		question.setQuestionTitle("New Question");
		Map<Integer, String> Options = questionAbstractFactoryTest.returnMapInstance();
		Options.put(1, "firstoption");
		return null;
	}
	public boolean createOptions(int questionID, int optionStoredAs, String optionText){
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		Options options = questionAbstractFactoryTest.returnOptionInstance();
		question.setQuestionID(1);
		options.setOptionText("newoption");
		Map<Integer, String> Options = questionAbstractFactoryTest.returnMapInstance();
		Options.put(1,"firstoption");
		return true;
	}

	@Override
	public Stack<Question> loadQuestionsByTitle(int instructorID, String questionTitle) {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();

		Stack<Question> questions = questionAbstractFactoryTest.returnQuestionStack();
		Question questionbank = questionAbstractFactoryTest.returnQuestionInstance();
		Question questionbank1 = questionAbstractFactoryTest.returnQuestionInstance();
		questionbank.setQuestionID(1);
		questionbank.setQuestionTitle("Title");
		questionbank.setQuestionText("Question text");
		Map<Integer, String> Options = questionAbstractFactoryTest.returnMapInstance();
		Options.put(1, "first Option");
		questionbank.setOptions(Options);
		questions.add(questionbank);
		questionbank1.setQuestionID(2);
		questionbank1.setQuestionTitle("Second Question");
		questionbank1.setQuestionText("Second Question Text");
		Map<Integer, String> Options2 = questionAbstractFactoryTest.returnMapInstance();
		Options2.put(2, "Second Option");
		questionbank1.setOptions(Options2);
		questions.add(questionbank1);
		return questions;
	}

}
