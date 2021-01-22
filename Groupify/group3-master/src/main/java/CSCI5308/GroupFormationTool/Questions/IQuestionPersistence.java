package CSCI5308.GroupFormationTool.Questions;

import java.util.List;
import java.util.Stack;

public interface IQuestionPersistence {

	public Stack<Question> loadAllQuestions(int instructorID, int sortFlag);

	public Question loadSingleQuestion(int questionID);

	public boolean createQuestion(Question question);

	public boolean deleteQuestion(int questionID);

	public List<Integer> checkQuestionAccess();

	public boolean createOptions(int questionID, int optionStoredAs, String optionText);
	
	public Stack<Question> loadQuestionsByTitle(int instructorID, String questionTitle);
}
