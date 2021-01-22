package CSCI5308.GroupFormationTool.GroupFormation;

public class GroupFormationPolicy implements IGroupFormationPolicy {
	private int questionID;
	private int questionType;
	private long courseID;
	private String groupBy;
	private int compareValue;
	private int groupSize;

	public GroupFormationPolicy() {
	}

	public GroupFormationPolicy(int questionID, int questionType, long courseID, String groupBy, int compareValue,
			int groupSize, IGroupFormationPolicyPersistence groupFormationDBObj) {
		this.questionID = questionID;
		this.questionType = questionType;
		this.courseID = courseID;
		this.groupBy = groupBy;
		this.compareValue = compareValue;
		this.groupSize = groupSize;
		groupFormationDBObj.insertSurveyPolicy(this);
	}

	public int getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public long getCourseID() {
		return courseID;
	}

	public void setCourseID(long courseID) {
		this.courseID = courseID;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public int getCompareValue() {
		return compareValue;
	}

	public void setCompareValue(int compareValue) {
		this.compareValue = compareValue;
	}

}
