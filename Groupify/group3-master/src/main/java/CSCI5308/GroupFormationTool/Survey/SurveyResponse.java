package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.List;


public class SurveyResponse {
    private long courseId;
    private int userId;
    private List<SurveyAnswer> answers = new ArrayList<SurveyAnswer>();

    public long getCourseId() {
        return courseId;
    }

    public List<SurveyAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SurveyAnswer> answers) {
        this.answers = answers;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}