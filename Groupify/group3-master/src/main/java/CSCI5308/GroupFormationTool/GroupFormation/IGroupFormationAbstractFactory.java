package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.AccessControl.User;

public interface IGroupFormationAbstractFactory {

	GroupFormationPolicy returnGroupFormationPolicy(int questionID, 
						int questionType, long courseID, String groupBy, 
						int compareValue, int groupSize,
						IGroupFormationPolicyPersistence groupFormationDBObj);
	List<IGroupFormationPolicy> returnGroupFormationPolicyList();
	IGroupFormationPolicyPersistence returnGroupFormationPolicyDB();
	IGroupFormationService returnGroupFormationService();
	Map<Integer, Map<User, List<String>>> returnMapInstanceOfIntegerAndMap(); 
	Map<User, List<String>> returnMapInstanceOfUserAndStrings();
	List<String> returnStringListInstance();
	Map<Long, Map<Integer, List<String>>> returnMapInstanceOfLongAndMap();
	Map<Integer, List<String>> returnMapInstanceOfIntegerAndString();
	List<String> returnArrayListInstance();
	GroupFormationForm returnGroupFormationFormInstance();
}
