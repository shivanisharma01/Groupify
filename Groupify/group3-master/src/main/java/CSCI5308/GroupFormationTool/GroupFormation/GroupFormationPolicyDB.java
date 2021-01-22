package CSCI5308.GroupFormationTool.GroupFormation;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Database.DatabaseConfig;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;

public class GroupFormationPolicyDB implements IGroupFormationPolicyPersistence {

	Logger logger = LoggerFactory.getLogger(GroupFormationPolicyDB.class);
	
	@Override
	public boolean insertSurveyPolicy(IGroupFormationPolicy groupFormationPolicyObj) {
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();
		CallStoredProcedure proc = null;
		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spCreateGroupFormationPolicy(?, ?, ?, ?, ?)");
			proc.setParameter(1, groupFormationPolicyObj.getQuestionID());
			proc.setParameter(2, groupFormationPolicyObj.getGroupBy());
			proc.setParameter(3, groupFormationPolicyObj.getCompareValue());
			proc.setParameter(4, groupFormationPolicyObj.getCourseID());
			proc.setParameter(5, groupFormationPolicyObj.getGroupSize());
			proc.execute();
		} catch (SQLException e) {
			logger.error("Error getting survey policy ", e);
			e.printStackTrace();
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

}
