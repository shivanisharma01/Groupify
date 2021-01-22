package CSCI5308.GroupFormationTool.Survey;


public class SurveyConfiguration {

	private static SurveyConfiguration uniqueInstance = null;
	private ISurveyAbstractFactory surveyAbstractFactory;
	private ISurveyPersistence surveyDB;
	private ISurveyResponsePersistence surveyResponseDB;
	
	private SurveyConfiguration() {
		surveyAbstractFactory = new SurveyAbstractFactory();
		surveyDB = surveyAbstractFactory.returnSurveyDB();
		surveyResponseDB = surveyAbstractFactory.returnSurveyResponseDB();
	}
	
	public static SurveyConfiguration instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new SurveyConfiguration();
		}
		return uniqueInstance;
	}
	
	public ISurveyAbstractFactory getSurveyAbstractFactory() {
        return surveyAbstractFactory;
    }

    public void setSurveyAbstractFactory(ISurveyAbstractFactory surveyAbstractFactory) {
        this.surveyAbstractFactory = surveyAbstractFactory;
    }
	
	public void setSurveyDB(ISurveyPersistence surveyDB) {
		this.surveyDB = surveyDB;
	}

	public ISurveyPersistence getSurveyDB() {
		return surveyDB;
	}
	
	
	public ISurveyResponsePersistence getSurveyResponseDB() {
		return surveyResponseDB;
	}

	public void setSurveyResponseDB(ISurveyResponsePersistence surveyResponseDB) {
		this.surveyResponseDB = surveyResponseDB;
	}

}
