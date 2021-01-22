package CSCI5308.GroupFormationTool.Algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AlgorithmAbstractFactory implements IAlgorithmAbstractFactory {

	@Override
	public LinkedList<List<Long>> returnLinkedListInstance() {
		return new LinkedList<List<Long>>();
	}

	@Override
	public Map<Long, Double> returnMapInstanceOfLongAndDouble() {
		return new HashMap<Long, Double>();
	}

	@Override
	public AlgorithmBuilder returnAlgorithmBuilder() {
		return new AlgorithmBuilder();
	}

	@Override
	public List<Long> returnListInstance() {
		return new LinkedList<>();
	}

	@Override
	public Map<Long, Map<Integer, List<String>>> returnMapInstanceOfLongAndMap(
			Map<Long, Map<Integer, List<String>>> surveyResponse) {
		return new HashMap<>(surveyResponse);
	}
}
