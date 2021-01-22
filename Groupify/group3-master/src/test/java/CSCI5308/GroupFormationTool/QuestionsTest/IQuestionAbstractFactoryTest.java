package CSCI5308.GroupFormationTool.QuestionsTest;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.Options;
import CSCI5308.GroupFormationTool.Questions.Question;

public interface IQuestionAbstractFactoryTest {

	Question returnQuestionInstance();
	Stack<Question> returnQuestionStack();
	Options returnOptionInstance();
	List<Options> returnOptionsListInstance();
	Map<Integer, String> returnMapInstance();
	IQuestionPersistence returnQuestionDBMock();
	Date returnDateInstance();
}
