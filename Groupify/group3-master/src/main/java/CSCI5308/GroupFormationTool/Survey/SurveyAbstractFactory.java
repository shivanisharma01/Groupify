package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import CSCI5308.GroupFormationTool.Questions.Question;

public class SurveyAbstractFactory implements ISurveyAbstractFactory {

	@Override
	public SurveyDetails returnSurveyDetailsInstance() {
		return new SurveyDetails();
	}

	@Override
	public Stack<SurveyDetails> returnSurveyDetailsStackInstance() {
		return new Stack<SurveyDetails>();
	}

	@Override
	public SurveyAnswer returnSurveyAnswerInstance() {
		return new SurveyAnswer();
	}

	@Override
	public SurveyResponse returnSurveyResponseInstance() {
		return new SurveyResponse();
	}

	@Override
	public ISurveyPersistence returnSurveyDB() {
		return new SurveyDB();
	}



	@Override
	public List<SurveyAnswer> returnSurveyAnswerListInstance() {
		return new ArrayList<SurveyAnswer>();  
	}
	
	@Override
	public Map<Integer, String> returnMapInstance() {
		return new HashMap<Integer, String>();
	}

	@Override
	public SurveyAnswer returnSurveyAnswerInstance(Question question) {
		return new SurveyAnswer(question);
	}

	@Override
	public ISurveyResponsePersistence returnSurveyResponseDB() {
		return new SurveyResponseDB();
	}

}
