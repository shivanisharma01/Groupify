package CSCI5308.GroupFormationTool.AlgorithmTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.Algorithm.AlgorithmBuilder;
import CSCI5308.GroupFormationTool.Algorithm.AlgorithmConfiguration;
import CSCI5308.GroupFormationTool.Algorithm.IAlgorithmAbstractFactory;
import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationPolicy;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicy;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicyPersistence;
import CSCI5308.GroupFormationTool.GroupFormationTest.GroupFormationConfigurationTest;
import CSCI5308.GroupFormationTool.GroupFormationTest.GroupFormationDBMock;
import CSCI5308.GroupFormationTool.GroupFormationTest.IGroupFormationAbstractFactoryTest;

public class AlgorithmBuilderTest{
	
	public static final int VALUE=1;
	public static final long courseID=1;
	public static final String type="similar";
	
	@Test
	public void getSurveyResponses() {
		IAlgorithmAbstractFactoryTest algorithmAbstractFactoryTest = AlgorithmConfigurationTest.instance().getAlgorithmAbstractFactory();
		AlgorithmBuilder builderObj = algorithmAbstractFactoryTest.returnAlgorithmBuilderInstance();
		Map<Long, Map<Integer, List<String>>> check = algorithmAbstractFactoryTest.returnMapInstanceOfLongAndMap();
		builderObj.setSurveyResponses(check);
		assertNotNull(builderObj.getSurveyResponses());
	}

	@Test
	public void setSurveyResponses() {
		IAlgorithmAbstractFactoryTest algorithmAbstractFactoryTest = AlgorithmConfigurationTest.instance().getAlgorithmAbstractFactory();
		AlgorithmBuilder builderObj = algorithmAbstractFactoryTest.returnAlgorithmBuilderInstance();
		Map<Long, Map<Integer, List<String>>> check = algorithmAbstractFactoryTest.returnMapInstanceOfLongAndMap();
		builderObj.setSurveyResponses(check);
		assertNotNull(builderObj.getSurveyResponses());
	}

	@Test
	public void getAlgorithmPolicies() {
		IAlgorithmAbstractFactoryTest algorithmAbstractFactoryTest = AlgorithmConfigurationTest.instance().getAlgorithmAbstractFactory();
		IGroupFormationAbstractFactoryTest groupFormationAbstractFactoryTest = GroupFormationConfigurationTest.instance().getGroupFormationAbstractFactory();
		IGroupFormationPolicyPersistence groupFormationDBMockObj = groupFormationAbstractFactoryTest.returnGroupFormationDBMock();
		IGroupFormationPolicy groupFormationPolicy = groupFormationAbstractFactoryTest.returnGroupFormationPolicy(VALUE, VALUE, courseID,type,VALUE,VALUE,groupFormationDBMockObj);
		List<IGroupFormationPolicy> surveyRulesObj = groupFormationAbstractFactoryTest.returnGroupFormationPolicyListInstance();
		surveyRulesObj.add(groupFormationPolicy);
		AlgorithmBuilder builderObj = algorithmAbstractFactoryTest.returnAlgorithmBuilderInstance();
		builderObj.setAlgorithmPolicies(surveyRulesObj);
		assertTrue(builderObj.getAlgorithmPolicies().size() > 0);
		assertFalse(builderObj.getAlgorithmPolicies().size() > 1);
	}

	@Test
	public void setAlgorithmPolicies() {
		IAlgorithmAbstractFactoryTest algorithmAbstractFactoryTest = AlgorithmConfigurationTest.instance().getAlgorithmAbstractFactory();
		IGroupFormationAbstractFactoryTest groupFormationAbstractFactoryTest = GroupFormationConfigurationTest.instance().getGroupFormationAbstractFactory();

		IGroupFormationPolicyPersistence groupFormationDBMockObj = groupFormationAbstractFactoryTest.returnGroupFormationDBMock();
		IGroupFormationPolicy groupFormationPolicy = groupFormationAbstractFactoryTest.returnGroupFormationPolicy(VALUE, VALUE, courseID,type,VALUE,VALUE,groupFormationDBMockObj);
		List<IGroupFormationPolicy> surveyRulesObj = groupFormationAbstractFactoryTest.returnGroupFormationPolicyListInstance();
		surveyRulesObj.add(groupFormationPolicy);
		AlgorithmBuilder builderObj = algorithmAbstractFactoryTest.returnAlgorithmBuilderInstance();
		builderObj.setAlgorithmPolicies(surveyRulesObj);
		assertTrue(builderObj.getAlgorithmPolicies().size() > 0);
		assertFalse(builderObj.getAlgorithmPolicies().size() > 1);
	}

	@Test
	public void getAlgorithmType() {
		IAlgorithmAbstractFactoryTest algorithmAbstractFactoryTest = AlgorithmConfigurationTest.instance().getAlgorithmAbstractFactory();
		AlgorithmBuilder builderObj = algorithmAbstractFactoryTest.returnAlgorithmBuilderInstance();
		builderObj.setAlgorithmType(type);
		assertTrue(builderObj.getAlgorithmType().equals(type));
	}

	@Test
	public void setAlgorithmType() {
		IAlgorithmAbstractFactoryTest algorithmAbstractFactoryTest = AlgorithmConfigurationTest.instance().getAlgorithmAbstractFactory();
		AlgorithmBuilder builderObj = algorithmAbstractFactoryTest.returnAlgorithmBuilderInstance();
		builderObj.setAlgorithmType(type);
		assertTrue(builderObj.getAlgorithmType().equals(type));
	}
}
