package CSCI5308.GroupFormationTool.Questions;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Question {

	private int questionID;
	private String questionText;
	private Date creationDate;
	private int questionTypeID;
	private String questionTitle;
	private int instructorID;
	private Map<Integer, String> options;
	private List<Options> tOption;

	public Question() {
		setDefaults();
	}

	public void addOption(Options option) {
		this.tOption.add(option);
	}

	public List<Options> gettOption() {
		return tOption;
	}

	public void settOption(List<Options> tOption) {
		this.tOption = tOption;
	}

	public void setDefaults() {
		IQuestionsAbstractFactory questionsAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();
		questionID = -1;
		questionText = "";
		questionTitle = "";
		creationDate = questionsAbstractFactory.returnDateInstance();
		questionTypeID = -1;
		options = questionsAbstractFactory.returnMapInstance();
		tOption = questionsAbstractFactory.returnOptionsListInstance();
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
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

	public int getInstructorID() {
		return instructorID;
	}

	public void setInstructorID(int instructorID) {
		this.instructorID = instructorID;
	}

	public Map<Integer, String> getOptions() {
		return options;
	}

	public void setOptions(Map<Integer, String> options) {
		this.options = options;
	}

	public boolean delete(IQuestionPersistence questionDB) {
		return questionDB.deleteQuestion(questionID);
	}

	public List<Integer> checkQuestionAccess(IQuestionPersistence questionDB) {
		return questionDB.checkQuestionAccess();
	}

	public Stack<Question> loadAllQuestions(IQuestionPersistence questionDB, int instructorID, int sortFlag) {
		return questionDB.loadAllQuestions(instructorID, sortFlag);
		
	}

	public Question loadSingleQuestion(IQuestionPersistence questionDB) {
		return questionDB.loadSingleQuestion(this.questionID);
		
	}

	public void createQuestion(IQuestionPersistence questionDB) {
		questionDB.createQuestion(this);
	}

	public Stack<Question> loadQuestionByTitle(IQuestionPersistence questionDB, int instructorID) {
		return questionDB.loadQuestionsByTitle(instructorID, questionTitle);
	}
}
