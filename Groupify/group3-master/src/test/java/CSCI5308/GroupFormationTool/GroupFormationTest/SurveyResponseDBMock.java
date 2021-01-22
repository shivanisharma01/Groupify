package CSCI5308.GroupFormationTool.GroupFormationTest;

import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.Survey.ISurveyResponsePersistence;

public class SurveyResponseDBMock implements ISurveyResponsePersistence{

	@Override
	public Map<Long, Map<Integer, List<String>>> loadSurveyResponse(long courseId) {
		IGroupFormationAbstractFactoryTest groupFormationAbstractFactoryTest = GroupFormationConfigurationTest.instance().getGroupFormationAbstractFactory();
		if(courseId == (long) 1)
		{
			Map<Long, Map<Integer, List<String>>> userResponse = groupFormationAbstractFactoryTest.returnMapInstanceOfLongAndMap();
			Map<Integer,List<String>> questionResponse = groupFormationAbstractFactoryTest.returnMapInstanceofIntegerAndList();
			List<String> answer = groupFormationAbstractFactoryTest.returnInstanceofStrings();
			answer.add("Example");
			questionResponse.put(1, answer);
			userResponse.put((long)1, questionResponse);
			return userResponse;
		}
		return null;
	}

}
