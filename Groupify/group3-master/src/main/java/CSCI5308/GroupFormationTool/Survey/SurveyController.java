package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserAbstractFactory;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControl.UserConfiguration;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.CourseConfiguration;
import CSCI5308.GroupFormationTool.Courses.ICourseAbstractFactory;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Courses.Role;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.IQuestionsAbstractFactory;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Questions.QuestionConfiguration;
import CSCI5308.GroupFormationTool.Security.SecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class SurveyController {

	Logger logger = LoggerFactory.getLogger(SurveyController.class);

    @GetMapping("/survey/surveyResponse/{courseID}")
    public String surveyResponse(Model model,@PathVariable("courseID") int courseID) {
        try{
		
        ICourseUserRelationshipPersistence checkUser = CourseConfiguration.instance().getCourseUserRelationshipDB();
        ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
        ICoursePersistence courseDB = CourseConfiguration.instance().getCourseDB();
        User user = CurrentUser.instance().getCurrentAuthenticatedUser();
        ISurveyPersistence surveyDetails = SurveyConfiguration.instance().getSurveyDB();
        Course course = courseAbstractFactory.returnCourseInstance();
        SurveyResponse surveyResponse = new SurveyResponse();
        List<SurveyAnswer> surveyAnswers = new ArrayList<SurveyAnswer>();
		courseDB.loadCourseByID(courseID, course);
        model.addAttribute("userID", user.getID());
        model.addAttribute("courseID", course.getId());
        surveyResponse.setAnswers(surveyAnswers);
        surveyResponse.setCourseId(courseID);
        surveyResponse.setUserId((int) (long) user.getID());
        List<Question> questions = surveyDetails.getSurveyQuestions(course);
        for (Question question : questions) {
            surveyAnswers.add(new SurveyAnswer(question));
        }
		List<Role> roles=  checkUser.loadUserRolesForCourse(course, user);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.isAuthenticated()) {
			logger.info("Authentication is being checked");
			IUserPersistence userDB = UserConfiguration.instance().getUserDB();
			List<Course> allCourses = course.loadAllCourses(courseDB);
			user.setBannerID(authentication.getPrincipal().toString());
			//userDB.loadUserByBannerID(authentication.getPrincipal().toString(), user);
			user.loadUserByBannerID(userDB);
			model.addAttribute("userID", user.getID());
			model.addAttribute("courses", allCourses);
		}
		if(!surveyDetails.checkSurveyTaken(course,(int) (long) user.getID())){
        	if(roles.contains(Role.STUDENT)){
            	model.addAttribute("surveyAnswers", surveyAnswers);
            	model.addAttribute("surveyResponse", surveyResponse);
            	return "survey/surveyResponse";
        	}
        	else{
				model.addAttribute("errorMessage", "Survey is only for Students");
				logger.error("Authorization failed to access survey");
				return "index";
			}
		}
		else{
			model.addAttribute("successMessage", "Response is already submitted");
			logger.info("Response is already submitted");
            return "index";
		}

    }catch(NullPointerException e){
		model.addAttribute("message", "Null Pointer Exception. Please Try Again.");
		logger.error("Null pointer Exception for user id", e);
		return "error";
	}
	
    }

    @GetMapping(value= "/survey/submitResponse")
    public String GetResponse(){
        return "survey/surveyResponse";
    }

    @PostMapping(value= "/survey/submitResponse")
    public String submitResponse(Model model,@ModelAttribute("surveyResponse") SurveyResponse answerResponse) {
		ISurveyPersistence surveyDetails = SurveyConfiguration.instance().getSurveyDB();
    	ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();

        if(surveyDetails.uploadResponses(answerResponse)){
			IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityConfig.instance().getPasswordPolicyObj();
		if (authentication.isAuthenticated()) {
			logger.info("User authentication is checked");
			User user = userAbstractFactory.returnUserInstance();
			Course course = courseAbstractFactory.returnCourseInstance();
			IUserPersistence userDB = UserConfiguration.instance().getUserDB();
			ICoursePersistence courseDB = CourseConfiguration.instance().getCourseDB();
			List<Course> allCourses = course.loadAllCourses(courseDB);
			user.setBannerID(authentication.getPrincipal().toString());
			user.loadUserByBannerID(userDB);
			model.addAttribute("userID", user.getID());
			model.addAttribute("courses", allCourses);
		}
			model.addAttribute("successMessage", "Response is successfully submitted. THANK YOU");
			logger.info("Response is submitted");
            return "index";
        }else{
			model.addAttribute("errorMessage", "Responses Not Submitted. Please try again.");
			logger.error("Responses Not Submitted. Please try again");
            return "index";
        }
        
    }

	@GetMapping("/createSurvey/{courseID}")
	public String createSurvey(Model model, 
								@PathVariable("courseID") int courseID) {
    	ISurveyAbstractFactory surveyAbstractFactory = SurveyConfiguration.instance().getSurveyAbstractFactory();
    	ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();

		long instructorID;
		ISurveyPersistence surveyDB = SurveyConfiguration.instance().getSurveyDB();
		Course course = courseAbstractFactory.returnCourseInstance();
		course.setId(courseID);
		SurveyDetails surveyDetail = surveyAbstractFactory.returnSurveyDetailsInstance();
		surveyDetail.setCourseId(courseID);
		if(course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR)) {
			instructorID =  (int)(CurrentUser.instance().getCurrentAuthenticatedUser().getId());
		}
		else {
			instructorID = surveyDetail.findSurveyInstructor(surveyDB);
		}
		Stack<SurveyDetails> surveyDetails = surveyAbstractFactory.returnSurveyDetailsStackInstance();
		surveyDetails = surveyDetail.loadSurveyQuestions(surveyDB);
		model.addAttribute("surveyDetails", surveyDetails);
		model.addAttribute("questionList", null);
		model.addAttribute("instructorID",instructorID);
		model.addAttribute("courseID",courseID);
		return "survey/createSurvey";
	}
	
	@GetMapping("/editSurvey/{courseID}")
	public String editSurveyAsTA(Model model, 
								@PathVariable("courseID") int courseID) {
		ISurveyAbstractFactory surveyAbstractFactory = SurveyConfiguration.instance().getSurveyAbstractFactory();
		ISurveyPersistence surveyDB = SurveyConfiguration.instance().getSurveyDB();
		SurveyDetails surveyDetail = surveyAbstractFactory.returnSurveyDetailsInstance();
		surveyDetail.setCourseId(courseID);
		long instructorID = surveyDetail.findSurveyInstructor(surveyDB);
		Stack<SurveyDetails> surveyDetails = surveyAbstractFactory.returnSurveyDetailsStackInstance();
		surveyDetails = surveyDetail.loadSurveyQuestions(surveyDB);
		model.addAttribute("surveyDetails", surveyDetails);
		model.addAttribute("questionList", null);
		model.addAttribute("instructorID",instructorID);
		model.addAttribute("courseID",courseID);
		return "survey/createSurvey";
	}
	
	
	@PostMapping("/createSurvey/{courseID}/{instructorID}")
	public String displayQuestionForSurvey(Model model, 
											@PathVariable("courseID") int courseID, 
											@PathVariable("instructorID") int instructorID, 
											@RequestParam("questionTitle") String questionTitle) {
		ISurveyAbstractFactory surveyAbstractFactory = SurveyConfiguration.instance().getSurveyAbstractFactory();
		IQuestionsAbstractFactory questionAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();
		IQuestionsAbstractFactory questionsAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();

		ISurveyPersistence surveyDB = SurveyConfiguration.instance().getSurveyDB();
		SurveyDetails surveyDetail = surveyAbstractFactory.returnSurveyDetailsInstance();
		Question question = questionsAbstractFactory.returnQuestionInstance();
		Stack<SurveyDetails> surveyDetails = surveyAbstractFactory.returnSurveyDetailsStackInstance();
		surveyDetail.setCourseId(courseID);
		surveyDetails = surveyDetail.loadSurveyQuestions(surveyDB);
		IQuestionPersistence questionDB = QuestionConfiguration.instance().getQuestionDB();
		Stack<Question> questions = questionAbstractFactory.returnQuestionStackInstance();
		questions = questionDB.loadQuestionsByTitle(instructorID, questionTitle);
		question.setQuestionTitle(questionTitle);
		questions = question.loadQuestionByTitle(questionDB, instructorID);
		model.addAttribute("surveyDetails", surveyDetails);
		model.addAttribute("questionList", questions);
		model.addAttribute("instructorID",instructorID);
		model.addAttribute("courseID",courseID);
		return "survey/createSurvey";
	}
	
	@GetMapping("/createSurvey/addQuestionToSurvey/{courseID}/{questionID}/{instructorID}")
	public String addQuestionToSurvey(Model model, 
									@PathVariable("courseID") int courseID,
									@PathVariable("questionID") int questionID,
									@PathVariable("instructorID") int instructorID) {
		ISurveyAbstractFactory surveyAbstractFactory = SurveyConfiguration.instance().getSurveyAbstractFactory();
		ISurveyPersistence surveyDB = SurveyConfiguration.instance().getSurveyDB();
		Stack<SurveyDetails> surveyDetails = surveyAbstractFactory.returnSurveyDetailsStackInstance();
		SurveyDetails surveyDetail = surveyAbstractFactory.returnSurveyDetailsInstance();
		surveyDetail.setCourseId(courseID);
		surveyDetail.setQuestionId(questionID);
		surveyDetail.addQuestionToSurvey(surveyDB);
		surveyDetails = surveyDetail.loadSurveyQuestions(surveyDB);
		model.addAttribute("surveyDetails", surveyDetails);
		model.addAttribute("questionList", null);
		model.addAttribute("instructorID",instructorID);
		model.addAttribute("courseID",courseID);
		return "survey/createSurvey";
	}
	
	@GetMapping("/createSurvey/removeQuestionFromSurvey/{courseID}/{questionID}/{instructorID}")
	public String removeQuestionFromSurvey(Model model, 
									@PathVariable("courseID") int courseID,
									@PathVariable("questionID") int questionID,
									@PathVariable("instructorID") int instructorID) {
		ISurveyAbstractFactory surveyAbstractFactory = SurveyConfiguration.instance().getSurveyAbstractFactory();

		ISurveyPersistence surveyDB = SurveyConfiguration.instance().getSurveyDB();
		Stack<SurveyDetails> surveyDetails = surveyAbstractFactory.returnSurveyDetailsStackInstance();
		SurveyDetails surveyDetail = surveyAbstractFactory.returnSurveyDetailsInstance();
		surveyDetail.setCourseId(courseID);
		surveyDetail.setQuestionId(questionID);
		surveyDetail.removeQuestionFromSurvey(surveyDB);
		surveyDetails = surveyDetail.loadSurveyQuestions(surveyDB);
		model.addAttribute("surveyDetails", surveyDetails);
		model.addAttribute("questionList", null);
		model.addAttribute("instructorID",instructorID);
		model.addAttribute("courseID",courseID);
		return "survey/createSurvey";
	}
	
	@GetMapping("/viewSurvey/{courseID}")
	public String viewSurvey(Model model, 
									@PathVariable("courseID") int courseID) {
		ISurveyAbstractFactory surveyAbstractFactory = SurveyConfiguration.instance().getSurveyAbstractFactory();

		ISurveyPersistence surveyDB = SurveyConfiguration.instance().getSurveyDB();
		SurveyDetails surveyDetail = surveyAbstractFactory.returnSurveyDetailsInstance();
		Stack<SurveyDetails> surveyDetails = surveyAbstractFactory.returnSurveyDetailsStackInstance();
		surveyDetail.setCourseId(courseID);
		surveyDetails = surveyDetail.loadSurveyQuestions(surveyDB);
		model.addAttribute("surveyDetails", surveyDetails);
		model.addAttribute("courseID", courseID);
		return "survey/viewSurvey";
	}
	
	@GetMapping(path = "/loadSurveyQuestion/{questionID}/{courseID}")
	public String loadSingleSurveyQuestion(Model model, 
									@PathVariable("questionID") int questionID,
									@PathVariable("courseID") int courseID) {
		IQuestionsAbstractFactory questionAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();

		Map<Integer, String> mapOfOptions = questionAbstractFactory.returnMapInstance();
		IQuestionPersistence questionDB = QuestionConfiguration.instance().getQuestionDB();
		Question question = questionAbstractFactory.returnQuestionInstance();
		Question tempquestion = questionAbstractFactory.returnQuestionInstance();
		tempquestion.setQuestionID(questionID);
		//question = questionDB.loadSingleQuestion(questionID);
		question = tempquestion.loadSingleQuestion(questionDB);
		mapOfOptions = question.getOptions();
		model.addAttribute("question", question);
		model.addAttribute("mapOfOptions", mapOfOptions);
		model.addAttribute("courseID",courseID);
		return "survey/singleSurveyQuestion";
	}
	
	@GetMapping("/publishSurvey/{courseID}")
	public String publishSurvey(Model model, 
									@PathVariable("courseID") long courseID) {
		ISurveyAbstractFactory surveyAbstractFactory = SurveyConfiguration.instance().getSurveyAbstractFactory();

		ISurveyPersistence surveyDB = SurveyConfiguration.instance().getSurveyDB();
		SurveyDetails surveyDetail = surveyAbstractFactory.returnSurveyDetailsInstance();
		surveyDetail.setCourseId(courseID);
		surveyDetail.publishSurvey(surveyDB);
		return "redirect:/course/instructoradmin?id=" + courseID;
	}
	
}
