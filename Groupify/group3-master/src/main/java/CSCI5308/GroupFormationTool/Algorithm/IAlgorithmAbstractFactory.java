package CSCI5308.GroupFormationTool.Algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface IAlgorithmAbstractFactory {

	LinkedList<List<Long>> returnLinkedListInstance();
	Map<Long, Double> returnMapInstanceOfLongAndDouble();
	AlgorithmBuilder returnAlgorithmBuilder();
	List<Long> returnListInstance();
	Map<Long, Map<Integer, List<String>>> returnMapInstanceOfLongAndMap(Map<Long, Map<Integer, List<String>>> surveyResponse);
}
