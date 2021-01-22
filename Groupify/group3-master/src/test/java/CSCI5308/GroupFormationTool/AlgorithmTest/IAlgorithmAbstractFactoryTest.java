package CSCI5308.GroupFormationTool.AlgorithmTest;

import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.Algorithm.AlgorithmBuilder;

public interface IAlgorithmAbstractFactoryTest {

	AlgorithmBuilder returnAlgorithmBuilderInstance();
	Map<Long, Map<Integer, List<String>>> returnMapInstanceOfLongAndMap();
	Map<Long, Map<Integer, List<String>>> returnMapInstanceOfSurveyResponse(Map<Long, Map<Integer, List<String>>> surveyResponse);
}
