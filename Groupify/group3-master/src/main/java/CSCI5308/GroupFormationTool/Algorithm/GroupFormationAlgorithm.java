package CSCI5308.GroupFormationTool.Algorithm;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationPolicy;

public class GroupFormationAlgorithm implements IGroupFormationAlgorithm {

	Logger logger = LoggerFactory.getLogger(GroupFormationAlgorithm.class);
	private static final String SIMILAR = "similar";
	private static final String DISSIMILAR = "dissimilar";
	private static final String GREATER = "greater";
	private static final String LESSER = "lesser";
	private List<IGroupFormationPolicy> algorithmPolicies;
	private List<List<Long>> groups;
	private Map<Long, Map<Integer, List<String>>> surveyResponses;

	public GroupFormationAlgorithm() {
		super();
		algorithmPolicies = null;
		groups = null;
		surveyResponses = null;
	}

	public GroupFormationAlgorithm(List<IGroupFormationPolicy> algorithmPolicies,
			Map<Long, Map<Integer, List<String>>> surveyResponses) {
		super();
		IAlgorithmAbstractFactory algorithmAbstractFactory = AlgorithmConfiguration.instance()
				.getAlgorithmAbstractFactory();
		this.algorithmPolicies = algorithmPolicies;
		this.surveyResponses = algorithmAbstractFactory.returnMapInstanceOfLongAndMap(surveyResponses);
	}

	@Override
	public List<List<Long>> generateGroup() {
		IAlgorithmAbstractFactory algorithmAbstractFactory = AlgorithmConfiguration.instance()
				.getAlgorithmAbstractFactory();
		if (surveyResponses == null) {
			return null;
		}
		groups = algorithmAbstractFactory.returnLinkedListInstance();
		int groupSize = this.algorithmPolicies.get(0).getGroupSize();
		while (surveyResponses.size() != 0) {
			logger.info("Generating gropus for a size of "+groupSize+ " student");
			generateGroupAlgorithm(groupSize);
		}
		int i = 0;
		for (List<Long> groupID : groups) {
			i++;
			for (long userID : groupID) {
				logger.info("Formed Group " + i + userID);
			}
		}
		return groups;
	}

	private void generateGroupAlgorithm(int groupSize) {
		IAlgorithmAbstractFactory algorithmAbstractFactory = AlgorithmConfiguration.instance()
				.getAlgorithmAbstractFactory();
		Map<Long, Double> usersScore = algorithmAbstractFactory.returnMapInstanceOfLongAndDouble();
		long randomUserResponseKey = surveyResponses.keySet().stream().findAny().get();
		logger.info("validating responses");
		Map<Integer, List<String>> randomUserResponseValue = surveyResponses.get(randomUserResponseKey);
		for (IGroupFormationPolicy policy : algorithmPolicies) {
			for (long userID : surveyResponses.keySet()) {
				if (userID != randomUserResponseKey) {
					List<String> currentUserResponse = surveyResponses.get(userID).get(policy.getQuestionID());
					List<String> randomUserResponse = randomUserResponseValue.get(policy.getQuestionID());
					if (null != randomUserResponse && null != currentUserResponse) {
						double currentUserScore = 0.0;
						double maximumScore = 1.0 / randomUserResponse.size();
						double compareScore = 2.0;
						for (String s : randomUserResponse) {
							if (policy.getGroupBy().equalsIgnoreCase(SIMILAR)) {
								if (currentUserResponse.contains(s)) {
									currentUserScore += maximumScore;
								}
							} else if (policy.getGroupBy().equalsIgnoreCase(DISSIMILAR)) {
								if (!currentUserResponse.contains(s)) {
									currentUserScore += maximumScore;
								}
							} else if (policy.getGroupBy().equalsIgnoreCase(GREATER)
									&& policy.getCompareValue() != -1) {
								if (Integer.parseInt(s) > policy.getCompareValue()) {
									currentUserScore += compareScore;
								}
							} else if (policy.getGroupBy().equalsIgnoreCase(LESSER) && policy.getCompareValue() != -1) {
								if (Integer.parseInt(s) > policy.getCompareValue()) {
									currentUserScore += compareScore;
								}
							}
						}
						double score = usersScore.getOrDefault(userID, 0.0);
						score += currentUserScore;
						usersScore.put(userID, score);
					}
				}
			}
		}
		List<Long> group = algorithmAbstractFactory.returnListInstance();
		group.add(randomUserResponseKey);
		if (usersScore.size() > 0) {
			Map<Long, Double> sortedUsersScore = usersScore.entrySet().stream()
					.sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors
							.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
			for (long tempUserID : sortedUsersScore.keySet()) {
				System.out.println("User ID" + tempUserID);
				System.out.println("Score" + sortedUsersScore.get(tempUserID));
				if (group.size() < groupSize && sortedUsersScore.size() > 0) {
					group.add(tempUserID);
				}
			}
		}
		groups.add(group);
		logger.info("Groups created");
		for (long userID : group) {
			surveyResponses.remove(userID);
		}
	}

}
