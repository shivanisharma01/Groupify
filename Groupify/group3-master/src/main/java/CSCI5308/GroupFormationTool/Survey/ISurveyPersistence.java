package CSCI5308.GroupFormationTool.Survey;

import java.util.List;
import java.util.Stack;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Questions.Question;

public interface ISurveyPersistence {
	public Stack<SurveyDetails> loadSurveyQuestions(long courseId);
	public boolean addQuestionToSurvey(SurveyDetails surveyQuestion);
	public boolean publishSurvey(long courseId);
	public boolean removeQuestionFromSurvey(SurveyDetails surveyQuestion);
	public boolean checkIfCourseContainsSurvey(SurveyDetails surveyDetail);
	public boolean checkIfSurveyPublished(SurveyDetails surveyDetail);
	public long findSurveyInstructor(long courseID);
	public boolean checkIfSurveyExist(Course course);
    public List<Question> getSurveyQuestions(Course course);
    public boolean uploadResponses(SurveyResponse surveyResponse);
    public boolean checkSurveyTaken(Course course, Integer userId);
}
