package CSCI5308.GroupFormationTool.GroupFormationTest;

import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationPolicy;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicy;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicyPersistence;
import CSCI5308.GroupFormationTool.Survey.ISurveyResponsePersistence;

public interface IGroupFormationAbstractFactoryTest {

	GroupFormationPolicy returnGroupFormationPolicyInstance();
	ISurveyResponsePersistence returnSurveyResponseDBMock();
	IGroupFormationPolicy returnGroupFormationPolicy(int questionID, int questionType, long courseID, String groupBy, int compareValue,
			int groupSize, IGroupFormationPolicyPersistence groupFormationDBObj);
	IGroupFormationPolicyPersistence returnGroupFormationDBMock();
	List<IGroupFormationPolicy> returnGroupFormationPolicyListInstance();
	Map<Long, Map<Integer, List<String>>> returnMapInstanceOfLongAndMap();
	Map<Integer,List<String>> returnMapInstanceofIntegerAndList();
	List<String> returnInstanceofStrings();
}
