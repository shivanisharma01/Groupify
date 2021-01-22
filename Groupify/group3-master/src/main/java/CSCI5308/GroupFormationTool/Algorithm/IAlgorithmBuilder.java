package CSCI5308.GroupFormationTool.Algorithm;

import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicy;

public interface IAlgorithmBuilder {

	public Map<Long, Map<Integer, List<String>>> getSurveyResponses();
	public void setSurveyResponses(Map<Long, Map<Integer, List<String>>> surveyResponses);
	public List<IGroupFormationPolicy> getAlgorithmPolicies();
	public void setAlgorithmPolicies(List<IGroupFormationPolicy> algorithmPolicies);
	public String getAlgorithmType();
	public void setAlgorithmType(String algorithmType);
	public IGroupFormationAlgorithm build();

}
