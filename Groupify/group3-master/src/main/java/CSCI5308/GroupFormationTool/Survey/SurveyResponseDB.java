package CSCI5308.GroupFormationTool.Survey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Database.DatabaseConfig;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationConfiguration;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationAbstractFactory;

public class SurveyResponseDB implements ISurveyResponsePersistence {

	Logger logger = LoggerFactory.getLogger(SurveyResponse.class);

	@Override
	public Map<Long, Map<Integer, List<String>>> loadSurveyResponse(long courseID) {
		IGroupFormationAbstractFactory groupFormationAbstractFactory = GroupFormationConfiguration.instance().getGroupFormationAbstractFactory();
		Map<Long, Map<Integer, List<String>>> multipleChoiceQuestionResponse = null;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();
		CallStoredProcedure proc = null;
		try {
			multipleChoiceQuestionResponse = groupFormationAbstractFactory.returnMapInstanceOfLongAndMap();
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spGetMultipleOptionResponse(?)");
			proc.setParameter(1, courseID);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					System.out.println("USER ID" + results.getLong(1));
					long userID = results.getLong(1);
					int questionID = results.getInt(2);
					String response = results.getString(3);
					if (null != multipleChoiceQuestionResponse.get(userID)) {
						if (null != multipleChoiceQuestionResponse.get(userID).get(questionID)) {
							multipleChoiceQuestionResponse.get(userID).get(questionID).add(response);
						} else {
							List<String> questionResponse = groupFormationAbstractFactory.returnArrayListInstance();
							questionResponse.add(response);
							multipleChoiceQuestionResponse.get(userID).put(questionID, questionResponse);
						}
					} else {
						Map<Integer, List<String>> questionResponseMap = groupFormationAbstractFactory.returnMapInstanceOfIntegerAndString();
						List<String> questionResponse = groupFormationAbstractFactory.returnArrayListInstance();
						questionResponse.add(response);
						questionResponseMap.put(questionID, questionResponse);
						multipleChoiceQuestionResponse.put(userID, questionResponseMap);
					}
				}
			}
		} catch (SQLException e) {
			logger.error("Error Connecting Database",e);
			e.printStackTrace();
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		System.out.println("Size"+multipleChoiceQuestionResponse.size());
		return multipleChoiceQuestionResponse;
	}
}
