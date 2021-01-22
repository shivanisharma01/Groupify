package CSCI5308.GroupFormationTool.Questions;

public class QuestionConfiguration {
	private static QuestionConfiguration uniqueInstance = null;
	private IQuestionsAbstractFactory questionsAbstractFactory;
	private IQuestionPersistence questionDB;
	
	private QuestionConfiguration() {
		questionsAbstractFactory = new QuestionsAbstractFactory();
		questionDB = questionsAbstractFactory.returnQuestionDB();
	}
	
	public static QuestionConfiguration instance() {
		// Using lazy initialization, this is the one and only place that the System
		// object will be instantiated.
		if (null == uniqueInstance) {
			uniqueInstance = new QuestionConfiguration();
		}
		return uniqueInstance;
	}
	
	public IQuestionsAbstractFactory getQuestionsAbstractFactory() {
        return questionsAbstractFactory;
    }

    public void setQuestionsAbstractFactory(IQuestionsAbstractFactory questionsAbstractFactory) {
        this.questionsAbstractFactory = questionsAbstractFactory;
    }
    
	public void setQuestionDB(IQuestionPersistence questionDB) {
		this.questionDB = questionDB;
	}

	public IQuestionPersistence getQuestionDB() {
		return questionDB;
	}
}
