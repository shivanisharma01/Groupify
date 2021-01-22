package CSCI5308.GroupFormationTool.Questions;

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
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.AccessControl.IUserAbstractFactory;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControl.UserConfiguration;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.CourseConfiguration;
import CSCI5308.GroupFormationTool.Courses.ICourseAbstractFactory;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Security.SecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class QuestionPageController {

	Logger logger = LoggerFactory.getLogger(QuestionPageController.class);

	@GetMapping("/questionCreate/{instructorID}")
	public String questionCreate(Model model, Question question, @PathVariable("instructorID") int instructorID) {
		IQuestionsAbstractFactory questionsAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();
		IQuestionPersistence questionDB = QuestionConfiguration.instance().getQuestionDB();
		List<Integer> questionAccess = questionsAbstractFactory.returnIntegerListInstance();
		model.addAttribute("instructorID", instructorID);
		model.addAttribute("question", question);
		Question tempQuestion = questionsAbstractFactory.returnQuestionInstance();
		questionAccess = tempQuestion.checkQuestionAccess(questionDB);
		if (questionAccess.size() > 0) {
			return "question/questionCreate";
		} else {
			model.addAttribute("message", "Access Restricted. Please Try Again.");
			logger.error("Access Restricted. Please Try Again");
            return "error";
		}

	}

	@GetMapping(path = "/question/{instructorID}/{sortFlag}")
	public String questionManager(Model model, @PathVariable("instructorID") int instructorID,
			@PathVariable("sortFlag") int sortFlag) {
		IQuestionsAbstractFactory questionsAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();
		IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
		Stack<Question> questions = questionsAbstractFactory.returnQuestionStackInstance();
		IQuestionPersistence questionDB = QuestionConfiguration.instance().getQuestionDB();
		List<Integer> questionAccess = questionsAbstractFactory.returnIntegerListInstance();
		Question question = questionsAbstractFactory.returnQuestionInstance();
		questions = question.loadAllQuestions(questionDB, instructorID, sortFlag);
		//questions = questionDB.loadAllQuestions(instructorID, sortFlag);
		model.addAttribute("listOfQuestions", questions);
		model.addAttribute("instructorID", instructorID);
		questionAccess = question.checkQuestionAccess(questionDB);
		if (questionAccess.size() > 0) {
			return "question/questionManager";
		} else {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			SecurityConfig.instance().getPasswordPolicyObj();
			if (authentication.isAuthenticated()) {
				User user = userAbstractFactory.returnUserInstance();
				Course course = courseAbstractFactory.returnCourseInstance();
				IUserPersistence userDB = UserConfiguration.instance().getUserDB();
				ICoursePersistence courseDB = CourseConfiguration.instance().getCourseDB();
				List<Course> allCourses = course.loadAllCourses(courseDB);
				//List<Course> allCourses = courseDB.loadAllCourses();
				user.setBannerID(authentication.getPrincipal().toString());
				//userDB.loadUserByBannerID(authentication.getPrincipal().toString(), user);
				user.loadUserByBannerID(userDB);
				model.addAttribute("userID", user.getID());
				model.addAttribute("courses", allCourses);
			}
			model.addAttribute("errorMessage", "Only Instructors can access the link.");
			logger.error("Only Instructors can access the link");
			return "index";
		}

	}

	@GetMapping(path = "/question/deleteQuestion/{instructorID}/{questionID}/{sortFlag}")
	public String deleteQuestion(Model model, @PathVariable("questionID") int questionID,
											@PathVariable("instructorID") int instructorID, 
											@PathVariable("sortFlag") int sortFlag) {
		IQuestionsAbstractFactory questionsAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();

		List<Integer> questionAccess = questionsAbstractFactory.returnIntegerListInstance();
		Stack<Question> questions = questionsAbstractFactory.returnQuestionStackInstance();
		IQuestionPersistence questionDB = QuestionConfiguration.instance().getQuestionDB();
		Question question = questionsAbstractFactory.returnQuestionInstance();
		question.setQuestionID(questionID);
		question.delete(questionDB);
		questions = question.loadAllQuestions(questionDB, instructorID, sortFlag);
		model.addAttribute("listOfQuestions", questions);
		questionAccess = question.checkQuestionAccess(questionDB);
		if (questionAccess.size() > 0) {
			return "question/questionManager";
		} else {
			IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			SecurityConfig.instance().getPasswordPolicyObj();
			if (authentication.isAuthenticated()) {
				User user = userAbstractFactory.returnUserInstance();
				Course course = courseAbstractFactory.returnCourseInstance();
				IUserPersistence userDB = UserConfiguration.instance().getUserDB();
				ICoursePersistence courseDB = CourseConfiguration.instance().getCourseDB();
				List<Course> allCourses = course.loadAllCourses(courseDB);
				user.setBannerID(authentication.getPrincipal().toString());
				//userDB.loadUserByBannerID(authentication.getPrincipal().toString(), user);
				user.loadUserByBannerID(userDB);
				model.addAttribute("userID", user.getID());
				model.addAttribute("courses", allCourses);
			}
			model.addAttribute("errorMessage", "Could not delete question. Please Try Again");
			logger.error("Could not delete question");
			return "index";
		}

	}

	@GetMapping(path = "/question/sortQuestion/{instructorID}/{sortFlag}")
	public String sortQuestions(Model model, @PathVariable("instructorID") int instructorID,
			@PathVariable("sortFlag") int sortFlag) {
		IQuestionsAbstractFactory questionsAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();
		Question question = questionsAbstractFactory.returnQuestionInstance();
		Stack<Question> questions = questionsAbstractFactory.returnQuestionStackInstance();
		IQuestionPersistence questionDB = QuestionConfiguration.instance().getQuestionDB();
		questions = question.loadAllQuestions(questionDB, instructorID, sortFlag);
		model.addAttribute("listOfQuestions", questions);
		List<Integer> questionAccess = questionsAbstractFactory.returnIntegerListInstance();
		questionAccess = question.checkQuestionAccess(questionDB);
		if (questionAccess.size() > 0) {
			return "question/questionManager";
		} else {
			IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			SecurityConfig.instance().getPasswordPolicyObj();
			if (authentication.isAuthenticated()) {
				User user = userAbstractFactory.returnUserInstance();
				Course course = courseAbstractFactory.returnCourseInstance();
				IUserPersistence userDB = UserConfiguration.instance().getUserDB();
				ICoursePersistence courseDB = CourseConfiguration.instance().getCourseDB();
				List<Course> allCourses = course.loadAllCourses(courseDB);
				user.setBannerID(authentication.getPrincipal().toString());
				//userDB.loadUserByBannerID(authentication.getPrincipal().toString(), user);
				user.loadUserByBannerID(userDB);
				model.addAttribute("userID", user.getID());
				model.addAttribute("courses", allCourses);
			}
			model.addAttribute("errorMessage", "Could not sort question. Please Try Again");
			logger.error("Could not sort question");
			return "index";
		}
	}

	@GetMapping(path = "/question/loadQuestion/{questionID}")
	public String loadQuestions(Model model, @PathVariable("questionID") int questionID) {
		IQuestionsAbstractFactory questionsAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();
		Map<Integer, String> mapOfOptions = questionsAbstractFactory.returnMapInstance();
		IQuestionPersistence questionDB = QuestionConfiguration.instance().getQuestionDB();
		Question question = questionsAbstractFactory.returnQuestionInstance();
		Question tempquestion = questionsAbstractFactory.returnQuestionInstance();
		tempquestion.setQuestionID(questionID);
		//question = questionDB.loadSingleQuestion(questionID);
		question = tempquestion.loadSingleQuestion(questionDB);
		mapOfOptions = question.getOptions();
		model.addAttribute("question", question);
		model.addAttribute("mapOfOptions", mapOfOptions);
		return "question/singleQuestion";
	}

	@PostMapping("/questionCreate/{instructorID}")
	public String questionPostCreate(@ModelAttribute("question") Question question, Model model,
			@PathVariable("instructorID") int instructorID) {
		IQuestionsAbstractFactory questionsAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();

		question.setInstructorID(instructorID);
		IQuestionPersistence questionDB = QuestionConfiguration.instance().getQuestionDB();
		model.addAttribute("question", question);
		List<Integer> questionAccess =questionsAbstractFactory.returnIntegerListInstance();
		questionAccess = question.checkQuestionAccess(questionDB);
		if (questionAccess.size() > 0) {
			if (question.getQuestionTypeID() == 1 || question.getQuestionTypeID() == 4) {
				questionDB.createQuestion(question);
				return "redirect:/question/sortQuestion/" + instructorID + "/1";
			} else if (question.getQuestionTypeID() == 2 || question.getQuestionTypeID() == 3) {
				return "redirect:/question/answerCreate?quesTitle=" + question.getQuestionTitle() + "&quesType="
						+ question.getQuestionTypeID() + "&instructorID=" + question.getInstructorID() + "&quesText="
						+ question.getQuestionText() + "&optList=0";
			} else {
				return "redirect:/questionCreate/" + instructorID;
			}
		} else {
			IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			SecurityConfig.instance().getPasswordPolicyObj();
			if (authentication.isAuthenticated()) {
				User user = userAbstractFactory.returnUserInstance();
				Course course = courseAbstractFactory.returnCourseInstance();
				IUserPersistence userDB = UserConfiguration.instance().getUserDB();
				ICoursePersistence courseDB = CourseConfiguration.instance().getCourseDB();
				List<Course> allCourses = course.loadAllCourses(courseDB);
				user.setBannerID(authentication.getPrincipal().toString());
				//userDB.loadUserByBannerID(authentication.getPrincipal().toString(), user);
				user.loadUserByBannerID(userDB);
				model.addAttribute("userID", user.getID());
				model.addAttribute("courses", allCourses);
			}
			model.addAttribute("errorMessage", "Could not create question. Please Try Again");
			logger.error("Could not create question");
			return "index";
		}

	}

	@GetMapping("question/answerCreate")
	public String answerManager(@RequestParam("quesTitle") String quesTitle, Model model,
			@RequestParam("quesType") int quesType, @RequestParam("instructorID") int instructorID,
			@RequestParam("quesText") String quesText, Question question, @RequestParam("optList") int optList) {
		IQuestionsAbstractFactory questionsAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();

		question.setQuestionText(quesText);
		question.setQuestionTitle(quesTitle);
		question.setQuestionTypeID(quesType);
		question.setInstructorID(instructorID);
		IQuestionPersistence questionDB = QuestionConfiguration.instance().getQuestionDB();
		for (int i = 0; i < optList + 1; i++) {
			question.addOption(questionsAbstractFactory.returnOptionsInstance());
		}
		model.addAttribute("question", question);
		List<Integer> questionAccess = questionsAbstractFactory.returnIntegerListInstance();
		questionAccess = question.checkQuestionAccess(questionDB);
		if (questionAccess.size() > 0) {
			return "question/answerCreate";
		} else {
			IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			SecurityConfig.instance().getPasswordPolicyObj();
			if (authentication.isAuthenticated()) {
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
			model.addAttribute("errorMessage", "Could not create answer. Please Try Again");
			logger.error("Could not create answer");
			return "index";
		}

	}

	@PostMapping("/question/answerCreate")
	public String answerPostCreate(@ModelAttribute("question") Question question,Model model) {
		IQuestionsAbstractFactory questionsAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();
		ICourseAbstractFactory courseAbstractFactory = CourseConfiguration.instance().getCourseAbstractFactory();

		IQuestionPersistence questionDB = QuestionConfiguration.instance().getQuestionDB();
		int size = question.gettOption().size();
		List<Options> testOp = question.gettOption();
		Map<Integer, String> options = questionsAbstractFactory.returnMapInstance();
		for (int i = 0; i < size; i++) {
			options.put(testOp.get(i).getOptionID(), testOp.get(i).getOptionText());
		}
		question.setOptions(options);
		//questionDB.createQuestion(question);
		question.createQuestion(questionDB);
		List<Integer> questionAccess = questionsAbstractFactory.returnIntegerListInstance();
		questionAccess = question.checkQuestionAccess(questionDB);
		if (questionAccess.size() > 0) {
			return "redirect:/question/sortQuestion/" + question.getInstructorID() + "/1";
		} else {
			IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			SecurityConfig.instance().getPasswordPolicyObj();
			if (authentication.isAuthenticated()) {
				User user = userAbstractFactory.returnUserInstance();
				Course course = courseAbstractFactory.returnCourseInstance();
				IUserPersistence userDB = UserConfiguration.instance().getUserDB();
				ICoursePersistence courseDB = CourseConfiguration.instance().getCourseDB();
				List<Course> allCourses = course.loadAllCourses(courseDB);
				user.setBannerID(authentication.getPrincipal().toString());
				//userDB.loadUserByBannerID(authentication.getPrincipal().toString(), user);
				user.loadUserByBannerID(userDB);
				model.addAttribute("userID", user.getID());
				model.addAttribute("courses", allCourses);
			}
			model.addAttribute("errorMessage", "Could not create answer. Please Try Again");
			logger.error("Could not create answer");
			return "index";
		}

	}

}