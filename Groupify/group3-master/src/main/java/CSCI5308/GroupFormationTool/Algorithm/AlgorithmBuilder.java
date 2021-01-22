package CSCI5308.GroupFormationTool.Algorithm;

import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicy;

public class AlgorithmBuilder implements IAlgorithmBuilder {

	private List<IGroupFormationPolicy> algorithmPolicies;
	private String algorithmType;
	private Map<Long, Map<Integer, List<String>>> surveyResponses;

	public Map<Long, Map<Integer, List<String>>> getSurveyResponses() {
		return surveyResponses;
	}

	public void setSurveyResponses(Map<Long, Map<Integer, List<String>>> surveyResponses) {
		this.surveyResponses = surveyResponses;
	}

	public List<IGroupFormationPolicy> getAlgorithmPolicies() {
		return algorithmPolicies;
	}

	public void setAlgorithmPolicies(List<IGroupFormationPolicy> algorithmPolicies) {
		this.algorithmPolicies = algorithmPolicies;
	}

	public String getAlgorithmType() {
		return algorithmType;
	}

	public void setAlgorithmType(String algorithmType) {
		this.algorithmType = algorithmType;
	}

	public IGroupFormationAlgorithm build() {
		switch (this.algorithmType) {
		case "random":
			return new GroupFormationAlgorithm(this.algorithmPolicies, this.surveyResponses);
		default:
			return new GroupFormationAlgorithm(this.algorithmPolicies, this.surveyResponses);
		}
	}

}
