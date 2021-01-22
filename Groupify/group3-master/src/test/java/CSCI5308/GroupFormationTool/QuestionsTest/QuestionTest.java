package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.Options;
import CSCI5308.GroupFormationTool.Questions.Question;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.*;

@SpringBootTest
@SuppressWarnings("deprecation")
public class QuestionTest {

	@Test
	public void ConstructorTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		Assert.isTrue(question.getQuestionID() == -1);
		Assert.isTrue(question.getQuestionTypeID() == -1);
		Assert.isTrue(question.getQuestionText().isEmpty());
		Assert.isTrue(question.getQuestionTitle().isEmpty());
		Assert.isTrue(question.getOptions().isEmpty());
	}

	@Test
	public void getCreationDateTest(){
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		Date date = questionAbstractFactoryTest.returnDateInstance();
		question.setCreationDate(date);
		Objects.equals(date, "2020-02-03");
		Assert.isTrue(question.getCreationDate().equals(date));
	}

	@Test
	public void setCreationDateTest(){
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		Date date = questionAbstractFactoryTest.returnDateInstance();
		date.equals("2020-02-03");
		question.setCreationDate(date);
		Assert.isTrue(question.getCreationDate().equals(date));
	}

	@Test
	public void setQuestionIDTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		question.setQuestionID(1);
		Assert.isTrue(1 == question.getQuestionID());
	}

	@Test
	public void getQuestionIDTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		question.setQuestionID(1);
		Assert.isTrue(1 == question.getQuestionID());
	}

	@Test
	public void getQuestionTextTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		question.setQuestionText("My first Question");
		Assert.isTrue(question.getQuestionText().equals("My first Question"));
	}

	@Test
	public void setQuestionTextTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		question.setQuestionText("My first Question");
		Assert.isTrue(question.getQuestionText().equals("My first Question"));
	}

	@Test
	public void setQuestionTitleTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		question.setQuestionTitle("Course Name Survey");
		Assert.isTrue(question.getQuestionTitle().equals("Course Name Survey"));
	}

	@Test
	public void getQuestionTitleTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		question.setQuestionTitle("Course Name Survey");
		Assert.isTrue(question.getQuestionTitle().equals("Course Name Survey"));
	}

	@Test
	public void setInstructorIDTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		question.setInstructorID(1);
		Assert.isTrue(question.getInstructorID() == 1);
	}

	@Test
	public void getInstructorIDTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		question.setInstructorID(1);
		Assert.isTrue(question.getInstructorID() == 1);
	}

	@Test
	public void getOptionsTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		Map<Integer, String> Options = questionAbstractFactoryTest.returnMapInstance();
		Options.put(1, "firstoption");
		question.setOptions(Options);
		Assert.isTrue(question.getOptions().equals(Options));
	}

	@Test
	public void setOptionsTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		Map<Integer, String> Options = questionAbstractFactoryTest.returnMapInstance();
		Options.put(1, "firstoption");
		question.setOptions(Options);
		Assert.isTrue(question.getOptions().equals(Options));
	}
	@Test
	public void setOptionTextTest(){
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Options options = questionAbstractFactoryTest.returnOptionInstance();
		options.setOptionText("new options");
		Assert.isTrue(options.getOptionText().equals("new options"));
	}

	@Test
	public void createQuestionTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		IQuestionPersistence questionPersistence = questionAbstractFactoryTest.returnQuestionDBMock();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		boolean return_value = questionPersistence.createQuestion(question);
		Assert.isTrue(return_value);
	}

	@Test
	public void deleteQuestionTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		IQuestionPersistence questionPersistence = questionAbstractFactoryTest.returnQuestionDBMock();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		question.setQuestionID(1);
		question.setQuestionText("New Question Text");
		question.setQuestionTitle("New Question");
		Map<Integer, String> Options = questionAbstractFactoryTest.returnMapInstance();
		Options.put(1, "firstoption");
		question.setOptions(Options);
		question.setQuestionID(-1);
		boolean success = questionPersistence.deleteQuestion(1);
		Assert.isTrue(success);
	}

	@Test
	public void loadAllQuestionsTest() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		IQuestionPersistence iQuestionPersistence = questionAbstractFactoryTest.returnQuestionDBMock();
		Stack<Question> list_of_questions = iQuestionPersistence.loadAllQuestions(1, 1);
		Assert.isTrue(list_of_questions.size() > 1);
		System.out.println(list_of_questions.get(0).getQuestionID());
		Assert.isTrue(list_of_questions.get(0).getQuestionID() == 1);
		Assert.isTrue(list_of_questions.get(1).getQuestionID() == 2);
	}
	@Test
	public void loadInstructorCourses() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		question.setQuestionID(1);
		question.setInstructorID(1);
		Assert.isTrue(question.getQuestionID()==1);
		Assert.isTrue(question.getInstructorID()==1);
	}

	@Test
	public void loadSingleQuestion() {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		IQuestionPersistence iQuestionPersistence = new QuestionDBMock();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		question.setQuestionID(-1);
		Question question1 = iQuestionPersistence.loadSingleQuestion(1);
		Assert.isTrue(question1.getQuestionID() == 1);
		Assert.isTrue(question1.getQuestionTitle().equals("New Question"));
		Assert.isTrue(question1.getQuestionText().equals("New Question Text"));
	}
	@Test
	public void createOptionsTest(){
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		IQuestionPersistence iQuestionPersistence = new QuestionDBMock();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		question.setQuestionID(1);
		Map<Integer,String> Options= questionAbstractFactoryTest.returnMapInstance();
		Options.put(1,"firstoptions");
		Options.put(2,"secondoptions");
		question.setOptions(Options);
		question.setQuestionText("newoption");
		boolean success = iQuestionPersistence.createOptions(1,1,"newoption");
		Assert.isTrue((success));
	}

}
