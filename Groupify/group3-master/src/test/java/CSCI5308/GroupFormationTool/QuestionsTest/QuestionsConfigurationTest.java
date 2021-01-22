package CSCI5308.GroupFormationTool.QuestionsTest;


public class QuestionsConfigurationTest {

	private static QuestionsConfigurationTest uniqueInstance = null;
	private IQuestionAbstractFactoryTest questionAbstractFactory;
	
	private QuestionsConfigurationTest() {
		questionAbstractFactory = new QuestionAbstractFactoryTest();
    }
	
	public static QuestionsConfigurationTest instance() {

        if (uniqueInstance == null) {
            return new QuestionsConfigurationTest();
        }
        return uniqueInstance;
    }
	
	public IQuestionAbstractFactoryTest getQuestionAbstractFactory() {
        return questionAbstractFactory;
    }
}
