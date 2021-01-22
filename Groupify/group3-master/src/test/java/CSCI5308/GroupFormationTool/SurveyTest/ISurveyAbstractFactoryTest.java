package CSCI5308.GroupFormationTool.SurveyTest;

import java.util.Date;
import java.util.List;

import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.SurveyAnswer;
import CSCI5308.GroupFormationTool.Survey.SurveyDetails;
import CSCI5308.GroupFormationTool.Survey.SurveyResponse;

public interface ISurveyAbstractFactoryTest {

	SurveyAnswer returnSurveyAnswerInstance();
	Date returnDateInstance(int year, int month, int day);
	SurveyDetails returnSurveyDetails();
	SurveyResponse returnSurveyResponseInstance();
	ISurveyPersistence returnSurveyDetailsDBMockInstance();
	List<SurveyAnswer> returnSurveyAnswerListInstance();
	Date returnDateInstance();
	ISurveyPersistence returnSurveyDBMock();
}
