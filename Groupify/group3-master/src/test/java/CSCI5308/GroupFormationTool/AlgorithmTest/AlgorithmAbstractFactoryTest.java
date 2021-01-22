package CSCI5308.GroupFormationTool.AlgorithmTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.Algorithm.AlgorithmBuilder;

public class AlgorithmAbstractFactoryTest implements IAlgorithmAbstractFactoryTest {

	@Override
	public AlgorithmBuilder returnAlgorithmBuilderInstance() {
		return new AlgorithmBuilder();
	}

	@Override
	public Map<Long, Map<Integer, List<String>>> returnMapInstanceOfLongAndMap() {
		return new HashMap<Long, Map<Integer, List<String>>>();
	}

	@Override
	public Map<Long, Map<Integer, List<String>>> returnMapInstanceOfSurveyResponse(
			Map<Long, Map<Integer, List<String>>> surveyResponse) {
		return new HashMap<>(surveyResponse);
	}
}
