package CSCI5308.GroupFormationTool.GroupFormationTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationPolicy;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicy;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicyPersistence;
import CSCI5308.GroupFormationTool.Survey.ISurveyResponsePersistence;

public class GroupFormationAbstractFactoryTest implements IGroupFormationAbstractFactoryTest {

	@Override
	public GroupFormationPolicy returnGroupFormationPolicyInstance() {
		return new GroupFormationPolicy();
	}

	@Override
	public ISurveyResponsePersistence returnSurveyResponseDBMock() {
		return new SurveyResponseDBMock();
	}

	@Override
	public IGroupFormationPolicy returnGroupFormationPolicy(int questionID, int questionType, long courseID,
			String groupBy, int compareValue, int groupSize, IGroupFormationPolicyPersistence groupFormationDBObj) {
		return new GroupFormationPolicy(questionID, questionType, courseID,
				 groupBy, compareValue, groupSize, groupFormationDBObj);
	}

	@Override
	public IGroupFormationPolicyPersistence returnGroupFormationDBMock() {
		return new GroupFormationDBMock();
	}

	@Override
	public List<IGroupFormationPolicy> returnGroupFormationPolicyListInstance() {
		return new ArrayList<IGroupFormationPolicy>();
	}

	@Override
	public Map<Long, Map<Integer, List<String>>> returnMapInstanceOfLongAndMap() {
		return new HashMap<Long, Map<Integer,List<String>>>();
	}

	@Override
	public Map<Integer, List<String>> returnMapInstanceofIntegerAndList() {
		return new HashMap<Integer, List<String>>();
	}

	@Override
	public List<String> returnInstanceofStrings() {
		return new ArrayList<String>();
	}

}
