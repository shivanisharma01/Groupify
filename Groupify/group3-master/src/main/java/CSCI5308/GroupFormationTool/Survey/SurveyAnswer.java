package CSCI5308.GroupFormationTool.Survey;

import java.util.List;

import CSCI5308.GroupFormationTool.Questions.IQuestionsAbstractFactory;
import CSCI5308.GroupFormationTool.Questions.Options;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Questions.QuestionConfiguration;

public class SurveyAnswer {

    private Question question;

    private List<Options> multipleAnswers;

    private String singleAnswer;

    public SurveyAnswer(){
    	IQuestionsAbstractFactory questionsAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();
    	multipleAnswers =  questionsAbstractFactory.returnOptionsListInstance();
    }

    public SurveyAnswer(Question question){
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public String getSingleAnswer() {
        return singleAnswer;
    }

    public void setSingleAnswer(String singleAnswer) {
        this.singleAnswer = singleAnswer;
    }

    public List<Options> getMultipleAnswers() {
        return multipleAnswers;
    }

    public void setMultipleAnswers(List<Options> multipleAnswers) {
        this.multipleAnswers = multipleAnswers;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}