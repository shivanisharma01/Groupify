package CSCI5308.GroupFormationTool.GroupFormationTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.Algorithm.AlgorithmBuilder;
import CSCI5308.GroupFormationTool.Algorithm.IAlgorithmBuilder;
import CSCI5308.GroupFormationTool.Algorithm.IGroupFormationAlgorithm;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicy;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicyPersistence;
import CSCI5308.GroupFormationTool.Survey.ISurveyResponsePersistence;

public class GroupFormationServiceTest{
	
	public static final int VALUE=1;
	public static final long courseID=1;
	
	@Test
	public void generateGroupTest()
	{
		IGroupFormationAbstractFactoryTest groupFormationAbstractFactoryTest = GroupFormationConfigurationTest.instance().getGroupFormationAbstractFactory();
		ISurveyResponsePersistence surveyResponseDBMockObj = groupFormationAbstractFactoryTest.returnSurveyResponseDBMock();
		IGroupFormationPolicyPersistence groupFormationDBMockObj = groupFormationAbstractFactoryTest.returnGroupFormationDBMock();
		IGroupFormationPolicy groupFormationPolicy = 
				groupFormationAbstractFactoryTest.returnGroupFormationPolicy(VALUE, VALUE, courseID,"similar",VALUE,VALUE,groupFormationDBMockObj);
		Map<Long, Map<Integer, List<String>>> surveyResponses=surveyResponseDBMockObj.loadSurveyResponse((long) 1);
		List<List<Long>> generatedGroups = null;
		IAlgorithmBuilder algorithmBuilderObj = new AlgorithmBuilder();
		List<IGroupFormationPolicy> surveyRulesObj = groupFormationAbstractFactoryTest.returnGroupFormationPolicyListInstance();
		surveyRulesObj.add(groupFormationPolicy);
		algorithmBuilderObj.setAlgorithmPolicies(surveyRulesObj);
		algorithmBuilderObj.setAlgorithmType("random");
		algorithmBuilderObj.setSurveyResponses(surveyResponses);
		IGroupFormationAlgorithm groupFormationAlgorithmObj = algorithmBuilderObj.build();
		generatedGroups = groupFormationAlgorithmObj.generateGroup();
		assertTrue(generatedGroups.size() > 0);
	}
	

}
