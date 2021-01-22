package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import CSCI5308.GroupFormationTool.AccessControl.IUserAbstractFactory;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControl.UserConfiguration;
import CSCI5308.GroupFormationTool.Algorithm.AlgorithmConfiguration;
import CSCI5308.GroupFormationTool.Algorithm.IAlgorithmAbstractFactory;
import CSCI5308.GroupFormationTool.Algorithm.IAlgorithmBuilder;
import CSCI5308.GroupFormationTool.Algorithm.IGroupFormationAlgorithm;
import CSCI5308.GroupFormationTool.Survey.ISurveyResponsePersistence;

public class GroupFormationService implements IGroupFormationService {

	Logger logger = LoggerFactory.getLogger(GroupFormationService.class);

	@Override
	public Map<Integer, Map<User, List<String>>> generateGroups(List<IGroupFormationPolicy> surveyRulesObj,
			ISurveyResponsePersistence surveyResponseDBObj, IUserPersistence userDBObj,IAlgorithmBuilder algorithmBuilderObj) {

		Map<Long, Map<Integer, List<String>>> surveyResponses = surveyResponseDBObj
				.loadSurveyResponse(surveyRulesObj.get(0).getCourseID());
		IGroupFormationAbstractFactory groupFormationAbstractFactory = GroupFormationConfiguration.instance().getGroupFormationAbstractFactory();
		IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
		IAlgorithmAbstractFactory algorithmAbstractFactory = AlgorithmConfiguration.instance().getAlgorithmAbstractFactory();
		Map<Integer, Map<User, List<String>>> groupDetails = groupFormationAbstractFactory.returnMapInstanceOfIntegerAndMap();
		List<List<Long>> generatedGroups = null;
		System.out.println("surveyResponses" + surveyResponses.size());
		int groupID = 0;
		
		if (surveyResponses.size() == 0) {
			return null;
		} else {
			algorithmBuilderObj = algorithmAbstractFactory.returnAlgorithmBuilder();
			algorithmBuilderObj.setAlgorithmPolicies(surveyRulesObj);
			algorithmBuilderObj.setAlgorithmType("random");
			algorithmBuilderObj.setSurveyResponses(surveyResponses);
			IGroupFormationAlgorithm groupFormationAlgorithmObj = algorithmBuilderObj.build();
			generatedGroups = groupFormationAlgorithmObj.generateGroup();
		}

		for (List<Long> group : generatedGroups) {
			System.out.println(groupID);
			groupID++;
			Map<User, List<String>> groupUserDetails = groupFormationAbstractFactory.returnMapInstanceOfUserAndStrings();
			for (long userID : group) {
				logger.info("Formed Group" + userID);
				User userDetails = userAbstractFactory.returnUserInstance(userID, userDBObj);
				Map<Integer, List<String>> userResponse = surveyResponses.get(userID);
				System.out.println("User stories"+userResponse.size());
				List<String> userAnswer = groupFormationAbstractFactory.returnArrayListInstance();
				for (int questionID : userResponse.keySet()) {
					String tempAnswer="";
					for (String choice : userResponse.get(questionID)) {
						tempAnswer+=choice+",";
					}
					tempAnswer=tempAnswer.substring(0,tempAnswer.length()-1);
					userAnswer.add(tempAnswer);
				}
				groupUserDetails.put(userDetails, userAnswer);
			}
			groupDetails.put(groupID, groupUserDetails);
		}
		logger.info("group details are fetched");
		return groupDetails;
	}

}
