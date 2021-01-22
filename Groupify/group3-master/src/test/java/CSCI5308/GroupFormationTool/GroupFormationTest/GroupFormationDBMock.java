package CSCI5308.GroupFormationTool.GroupFormationTest;

import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicy;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicyPersistence;

public class GroupFormationDBMock implements IGroupFormationPolicyPersistence {

	@Override
	public boolean insertSurveyPolicy(IGroupFormationPolicy groupFormationPolicyObj) {
		if(groupFormationPolicyObj.getCompareValue() == -1)
		{
			return true;
		}
		return false;
	}
}
