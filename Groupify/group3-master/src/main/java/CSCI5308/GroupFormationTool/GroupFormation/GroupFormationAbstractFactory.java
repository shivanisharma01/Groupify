package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.AccessControl.User;

public class GroupFormationAbstractFactory implements IGroupFormationAbstractFactory {

	@Override
	public IGroupFormationPolicyPersistence returnGroupFormationPolicyDB() {
		return new GroupFormationPolicyDB();
	}

	@Override
	public IGroupFormationService returnGroupFormationService() {
		return new GroupFormationService();
	}

	@Override
	public Map<Integer, Map<User, List<String>>> returnMapInstanceOfIntegerAndMap() {
		return new HashMap<Integer, Map<User, List<String>>>();
	}

	@Override
	public Map<User, List<String>> returnMapInstanceOfUserAndStrings() {
		return new HashMap<User, List<String>>();
	}

	@Override
	public List<String> returnStringListInstance() {
		return new ArrayList<String>();
	}

	@Override
	public Map<Long, Map<Integer, List<String>>> returnMapInstanceOfLongAndMap() {
		return new HashMap<Long, Map<Integer, List<String>>>();
	}

	@Override
	public Map<Integer, List<String>> returnMapInstanceOfIntegerAndString() {
		return new HashMap<>();
	}

	@Override
	public List<String> returnArrayListInstance() {
		return new ArrayList<>();
	}

	@Override
	public GroupFormationPolicy returnGroupFormationPolicy(int questionID, int questionType, long courseID,
			String groupBy, int compareValue, int groupSize, IGroupFormationPolicyPersistence groupFormationDBObj) {
		return new GroupFormationPolicy(questionID, questionType, courseID,
				 groupBy, compareValue, groupSize, groupFormationDBObj);
	}

	@Override
	public List<IGroupFormationPolicy> returnGroupFormationPolicyList() {
		return new ArrayList<IGroupFormationPolicy>();
	}

	@Override
	public GroupFormationForm returnGroupFormationFormInstance() {
		return new GroupFormationForm();
	}

}
