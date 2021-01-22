package CSCI5308.GroupFormationTool.QuestionsTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.Options;
import CSCI5308.GroupFormationTool.Questions.Question;

public class QuestionAbstractFactoryTest implements IQuestionAbstractFactoryTest {

	@Override
	public Question returnQuestionInstance() {
		return new Question();
	}

	@Override
	public Stack<Question> returnQuestionStack() {
		return new Stack<Question>();
	}

	@Override
	public Options returnOptionInstance() {
		return new Options();
	}

	@Override
	public List<Options> returnOptionsListInstance() {
		return new ArrayList<Options>();
	}

	@Override
	public Map<Integer, String> returnMapInstance() {
		return new HashMap<Integer, String>();
	}

	@Override
	public IQuestionPersistence returnQuestionDBMock() {
		return new QuestionDBMock();
	}

	@Override
	public Date returnDateInstance() {
		return new Date();
	}

}
