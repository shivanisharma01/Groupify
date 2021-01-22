package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.Stack;

import CSCI5308.GroupFormationTool.Survey.SurveyDetails;

public class GroupFormationForm {

	public Stack<SurveyDetails> surveyDetails;
	public int groupSize;

	public int getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public Stack<SurveyDetails> getSurveyDetails() {
		return surveyDetails;
	}

	public void setSurveyDetails(Stack<SurveyDetails> surveyDetails) {
		this.surveyDetails = surveyDetails;
	}
}
