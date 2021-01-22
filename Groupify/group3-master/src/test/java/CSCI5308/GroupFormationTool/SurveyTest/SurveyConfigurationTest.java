package CSCI5308.GroupFormationTool.SurveyTest;

public class SurveyConfigurationTest {

	private static SurveyConfigurationTest uniqueInstance = null;
	private ISurveyAbstractFactoryTest surveyAbstractFactory;
	
	private SurveyConfigurationTest() {
		surveyAbstractFactory = new SurveyAbstractFactoryTest();
    }
	
	public static SurveyConfigurationTest instance() {

        if (uniqueInstance == null) {
            return new SurveyConfigurationTest();
        }
        return uniqueInstance;
    }
	
	public ISurveyAbstractFactoryTest getSurveyAbstractFactory() {
        return surveyAbstractFactory;
    }
}
