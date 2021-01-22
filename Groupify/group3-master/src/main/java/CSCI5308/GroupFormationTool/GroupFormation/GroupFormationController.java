package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControl.UserConfiguration;
import CSCI5308.GroupFormationTool.Algorithm.AlgorithmBuilder;
import CSCI5308.GroupFormationTool.Algorithm.IAlgorithmBuilder;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.ISurveyResponsePersistence;
import CSCI5308.GroupFormationTool.Survey.ISurveyAbstractFactory;
import CSCI5308.GroupFormationTool.Survey.SurveyConfiguration;
import CSCI5308.GroupFormationTool.Survey.SurveyDetails;

@Controller
public class GroupFormationController {

	private static final String ID = "id";
	Logger logger = LoggerFactory.getLogger(GroupFormationController.class);

	@GetMapping("/groupformation/algorithm")
	public String algorithm(Model model, @RequestParam(name = ID) long courseID) {
		IGroupFormationAbstractFactory groupFormationAbstractFactory = GroupFormationConfiguration.instance().getGroupFormationAbstractFactory();
		ISurveyAbstractFactory surveyAbstractFactory = SurveyConfiguration.instance().getSurveyAbstractFactory();
		ISurveyPersistence surveyDB = SurveyConfiguration.instance().getSurveyDB();
		SurveyDetails surveyDetail = surveyAbstractFactory.returnSurveyDetailsInstance();
		surveyDetail.setCourseId(courseID);
		Stack<SurveyDetails> surveyDetails = surveyDetail.loadSurveyQuestions(surveyDB);
		GroupFormationForm groupFormationObj = groupFormationAbstractFactory.returnGroupFormationFormInstance();
		groupFormationObj.setSurveyDetails(surveyDetails);
		groupFormationObj.setGroupSize(0);
		model.addAttribute("groupFormationForm", groupFormationObj);
		logger.info("algorithm is triggered");
		return "survey/surveyAlgorithm";
	}

	@PostMapping("/groupformation/algorithm/save")
	public String algorithm(Model model, @ModelAttribute("groupFormationForm") GroupFormationForm groupFormationObj) {
		Map<Integer, Map<User, List<String>>> groups;
		IGroupFormationAbstractFactory groupFormationAbstractFactory = GroupFormationConfiguration.instance().getGroupFormationAbstractFactory();
		List<IGroupFormationPolicy> surveyRulesObj = groupFormationAbstractFactory.returnGroupFormationPolicyList();
		IGroupFormationPolicyPersistence groupFormationPolicyDB = GroupFormationConfiguration.instance().getGroupFormationPolicyObj();
		IGroupFormationService groupFormationServiceObj = GroupFormationConfiguration.instance().getGroupFormationServiceObj();
		ISurveyResponsePersistence surveyResponseDBObj = SurveyConfiguration.instance().getSurveyResponseDB();
		IUserPersistence userDBObj = UserConfiguration.instance().getUserDB();
		IAlgorithmBuilder algorithmBuilderObj=new AlgorithmBuilder();
		for (SurveyDetails surveyDetailsObj : groupFormationObj.getSurveyDetails()) {
			GroupFormationPolicy groupFormationPolicyObj = groupFormationAbstractFactory.returnGroupFormationPolicy(
					surveyDetailsObj.getQuestionId(),
					surveyDetailsObj.getQuestionTypeID(), surveyDetailsObj.getCourseId(), surveyDetailsObj.getGroupBy(),
					surveyDetailsObj.getCompareValue(), groupFormationObj.getGroupSize(), groupFormationPolicyDB);		
			surveyRulesObj.add(groupFormationPolicyObj);
			logger.info("Groups of size "+groupFormationObj.getGroupSize()+" are being created");
		}
		groups=groupFormationServiceObj.generateGroups(surveyRulesObj,surveyResponseDBObj,userDBObj,algorithmBuilderObj);
		System.out.println(surveyRulesObj.size());
		model.addAttribute("groupSize",groupFormationObj.getGroupSize());
		model.addAttribute("surveyRulesObj",groupFormationObj.getSurveyDetails());
		model.addAttribute("groups",groups);
		logger.info("algorithm is triggered");
		return "survey/groupResults";
	}
}
