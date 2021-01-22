package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Survey.ISurveyResponsePersistence;
import CSCI5308.GroupFormationTool.Algorithm.IAlgorithmBuilder;

public interface IGroupFormationService {
	
	public Map<Integer, Map<User, List<String>>> generateGroups(List<IGroupFormationPolicy> surveyRulesObj,ISurveyResponsePersistence surveyResponseDBObj,IUserPersistence userDBObj,IAlgorithmBuilder algorithmBuilderObj);

}
