package CSCI5308.GroupFormationTool.Questions;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public interface IQuestionsAbstractFactory {
	Question returnQuestionInstance();
	Stack<Question> returnQuestionStackInstance();
	Options returnOptionsInstance();
	List<Options> returnOptionsListInstance();
	List<Integer> returnIntegerListInstance();
	Map<Integer, String> returnMapInstance();
	Date returnDateInstance();
	IQuestionPersistence returnQuestionDB();
}
