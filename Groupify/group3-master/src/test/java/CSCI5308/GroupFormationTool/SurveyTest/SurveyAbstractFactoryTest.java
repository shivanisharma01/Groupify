package CSCI5308.GroupFormationTool.SurveyTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.SurveyAnswer;
import CSCI5308.GroupFormationTool.Survey.SurveyDetails;
import CSCI5308.GroupFormationTool.Survey.SurveyResponse;

public class SurveyAbstractFactoryTest implements ISurveyAbstractFactoryTest {

	@Override
	public SurveyAnswer returnSurveyAnswerInstance() {
		return new SurveyAnswer();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Date returnDateInstance(int year, int month, int day) {
		return new Date(year, month, day);
	}

	@Override
	public SurveyDetails returnSurveyDetails() {
		return new SurveyDetails();
	}

	@Override
	public SurveyResponse returnSurveyResponseInstance() {
		return new SurveyResponse();
	}

	@Override
	public ISurveyPersistence returnSurveyDetailsDBMockInstance() {
		return new SurveyDBMock();
	}

	@Override
	public List<SurveyAnswer> returnSurveyAnswerListInstance() {
		return new ArrayList<SurveyAnswer>(); 
	}

	@Override
	public Date returnDateInstance() {
		return new Date();
	}

	@Override
	public ISurveyPersistence returnSurveyDBMock() {
		return new SurveyDBMock();
	}
}
