package CSCI5308.GroupFormationTool.Survey;

import java.util.Date;
import java.util.Map;
import java.util.Stack;

public class SurveyDetails {
	private long courseId;
	private int questionId;
	private String questionText;
	private Date creationDate;
	private int questionTypeID;
	private String questionTitle;
	private Map<Integer, String> options;
	private int ifPublished;
	private String groupBy;
	private int compareValue;

	public SurveyDetails() {
		setDefaults();
	}

	public void setDefaults() {
		ISurveyAbstractFactory surveyAbstractFactory = SurveyConfiguration.instance().getSurveyAbstractFactory();

		courseId = -1;
		questionId = -1;
		questionText = "";
		questionTitle = "";
		creationDate = new Date();
		questionTypeID = -1;
		options = surveyAbstractFactory.returnMapInstance();
		ifPublished = 0;
		groupBy = "";
		compareValue = -1;
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

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getQuestionTypeID() {
		return questionTypeID;
	}

	public void setQuestionTypeID(int questionTypeID) {
		this.questionTypeID = questionTypeID;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public Map<Integer, String> getOptions() {
		return options;
	}

	public int getIfPublished() {
		return ifPublished;
	}

	public void setIfPublished(int ifPublished) {
		this.ifPublished = ifPublished;
	}
		

	public boolean addQuestionToSurvey(ISurveyPersistence surveyDB) {
		return surveyDB.addQuestionToSurvey(this);
	}
	
	public boolean removeQuestionFromSurvey(ISurveyPersistence surveyDB) {
		return surveyDB.removeQuestionFromSurvey(this);
	}
	
	public boolean checkIfCourseContainsSurvey(ISurveyPersistence surveyDB) {
		return surveyDB.checkIfCourseContainsSurvey(this);
	}
	
	public boolean checkIfSurveyPublished(ISurveyPersistence surveyDB) {
		return surveyDB.checkIfSurveyPublished(this);
	}
	
	public long findSurveyInstructor(ISurveyPersistence surveyDB) {
		return surveyDB.findSurveyInstructor(this.courseId);
	}
	
	public boolean publishSurvey(ISurveyPersistence surveyDB) {
		return surveyDB.publishSurvey(this.courseId);
	}

	public Stack<SurveyDetails> loadSurveyQuestions(ISurveyPersistence surveyDB) {
		return surveyDB.loadSurveyQuestions(this.courseId);
	}

}
