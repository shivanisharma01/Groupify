package CSCI5308.GroupFormationTool.Survey;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import CSCI5308.GroupFormationTool.Questions.Question;

public interface ISurveyAbstractFactory {
	SurveyDetails returnSurveyDetailsInstance();
	Stack<SurveyDetails> returnSurveyDetailsStackInstance();
	SurveyAnswer returnSurveyAnswerInstance();
	SurveyAnswer returnSurveyAnswerInstance(Question question);
	SurveyResponse returnSurveyResponseInstance();
	ISurveyPersistence returnSurveyDB();
	List<SurveyAnswer> returnSurveyAnswerListInstance();
	Map<Integer, String> returnMapInstance();
	ISurveyResponsePersistence returnSurveyResponseDB();
}