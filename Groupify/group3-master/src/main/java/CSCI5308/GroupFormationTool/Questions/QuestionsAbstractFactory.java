package CSCI5308.GroupFormationTool.Questions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class QuestionsAbstractFactory implements IQuestionsAbstractFactory {

	@Override
	public Question returnQuestionInstance() {
		return new Question();
	}

	@Override
	public Stack<Question> returnQuestionStackInstance() {
		return new Stack<Question>();
	}

	@Override
	public Options returnOptionsInstance() {
		return new Options();
	}

	@Override
	public List<Options> returnOptionsListInstance() {
		return new ArrayList<Options>();
	}

	@Override
	public List<Integer> returnIntegerListInstance() {
		return new ArrayList<Integer>();
	}

	@Override
	public Map<Integer, String> returnMapInstance() {
		return new HashMap<Integer, String>();
	}

	@Override
	public Date returnDateInstance() {
		return new Date();
	}

	@Override
	public IQuestionPersistence returnQuestionDB() {
		return new QuestionDB();
	}

}
