package CSCI5308.GroupFormationTool.Questions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import CSCI5308.GroupFormationTool.AccessControl.IUserAbstractFactory;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControl.UserConfiguration;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Database.DatabaseConfig;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuestionDB implements IQuestionPersistence {

	Logger logger = LoggerFactory.getLogger(QuestionDB.class);

	@Override
	public Stack<Question> loadAllQuestions(int instructorID, int sortFlag) {
		IQuestionsAbstractFactory questionsAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();
		Stack<Question> questions = questionsAbstractFactory.returnQuestionStackInstance();
		Map<Integer, String> mapOfOptions;
		int tmpQuestionID = -1;
		CallStoredProcedure proc = null;
		try {
			if (sortFlag == 1) {
				proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spGetQuestionsAscCreationDate(?)");
				proc.setParameter(1, instructorID);
			} else {
				proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spGetQuestionsAscQuestionTitle(?)");
				proc.setParameter(1, instructorID);
			}
			ResultSet results = proc.executeWithResults();
			if (results != null) {
				while (results.next()) {
					if (tmpQuestionID == -1 || tmpQuestionID != results.getInt(1)) {
						mapOfOptions = questionsAbstractFactory.returnMapInstance();
						tmpQuestionID = results.getInt(1);
						Question question = questionsAbstractFactory.returnQuestionInstance();
						question.setQuestionID(results.getInt(1));
						question.setQuestionText(results.getString(2));
						question.setCreationDate(results.getDate(3));
						question.setQuestionTypeID(results.getInt(4));
						question.setQuestionTitle(results.getString(5));
						question.setInstructorID(results.getInt(6));

						if (question.getQuestionTypeID() == 2 || question.getQuestionTypeID() == 3) {
							mapOfOptions = question.getOptions();
							mapOfOptions.put(results.getInt(8), results.getString(7));
						}
						questions.add(question);
					} else {
						Question tempQuestion = questions.pop();
						mapOfOptions = tempQuestion.getOptions();
						mapOfOptions.put(results.getInt(8), results.getString(7));
						questions.add(tempQuestion);
					}
				}
			}
			return questions;
		} catch (SQLException e) {
			logger.error("Database connection error", e);
			return null;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
	}

	@Override
	public Question loadSingleQuestion(int questionID) {
		IQuestionsAbstractFactory questionsAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		CallStoredProcedure proc = null;
		boolean flag = false;
		Map<Integer, String> mapOfOptions;
		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spGetSingleQuestion(?)");
			proc.setParameter(1, questionID);
			ResultSet results = proc.executeWithResults();
			Question question = null;
			if (results != null) {
				question = questionsAbstractFactory.returnQuestionInstance();
				while (results.next()) {
					if (flag == false) {
						question.setQuestionID(results.getInt(1));
						question.setQuestionText(results.getString(2));
						question.setCreationDate(results.getDate(3));
						question.setQuestionTypeID(results.getInt(4));
						question.setQuestionTitle(results.getString(5));
						question.setInstructorID(results.getInt(6));

						if (question.getQuestionTypeID() == 2 || question.getQuestionTypeID() == 3) {
							mapOfOptions = question.getOptions();
							mapOfOptions.put(results.getInt(8), results.getString(7));
						}
						flag = true;
					} else {
						mapOfOptions = question.getOptions();
						mapOfOptions.put(results.getInt(8), results.getString(7));
					}
				}
			}
			return question;
		} catch (SQLException e) {
			logger.error("Database connection error", e);
			return null;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}

	}

	@Override
	public boolean createQuestion(Question question) {
		CallStoredProcedure proc = null;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		int currentQuestionID = 0;
		boolean optionFlag = false;
		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spAddQuestions(?, ?, ?, ?, ?)");
			proc.setParameter(1, question.getQuestionText());
			proc.setParameter(2, question.getQuestionTitle());
			proc.setParameter(3, question.getQuestionTypeID());
			proc.setParameter(4, question.getInstructorID());
			proc.registerOutputParameterInt(5);
			proc.execute();
			currentQuestionID = proc.getInteger(5);
			if (question.getQuestionTypeID() == 2 || question.getQuestionTypeID() == 3) {
				if (null != proc) {
					proc.cleanup();
				}

				for (Map.Entry<Integer, String> mapElement : question.getOptions().entrySet()) {
					optionFlag = createOptions(currentQuestionID, mapElement.getKey(), mapElement.getValue());
				}
				return optionFlag;
			}
		} catch (SQLException e) {
			logger.error("Database connection error", e);
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	@Override
	public boolean deleteQuestion(int questionID) {
		CallStoredProcedure proc = null;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spDeleteQuestionAndOptions(?)");
			proc.setParameter(1, questionID);
			proc.execute();
		} catch (SQLException e) {
			logger.error("Database connection error", e);
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	public boolean createOptions(int questionID, int optionStoredAs, String optionText) {
		CallStoredProcedure proc = null;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spAddOptions(?, ?, ?)");
			proc.setParameter(1, questionID);
			proc.setParameter(2, optionText);
			proc.setParameter(3, optionStoredAs);
			proc.execute();
		} catch (SQLException e) {
			logger.error("Database connection error", e);
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	@Override
	public List<Integer> checkQuestionAccess() {
		IQuestionsAbstractFactory questionsAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();
		IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();

		
		User user = userAbstractFactory.returnUserInstance();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		IUserPersistence userDB = UserConfiguration.instance().getUserDB();
		userDB.loadUserByBannerID(authentication.getPrincipal().toString(), user);
		List<Integer> courses = questionsAbstractFactory.returnIntegerListInstance();
		int courseID = -1;
		CallStoredProcedure proc = null;
		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spInstructorCourses(?)");
			proc.setParameter(1, user.getID());
			ResultSet results = proc.executeWithResults();
			if (results != null) {
				System.out.println("The connection is established");
				while (results.next()) {
					courseID = results.getInt(1);
					courses.add(courseID);
				}
			}
			return courses;
		} catch (SQLException e) {
			logger.error("Database connection error", e);
			return null;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
	}

	@Override
	public Stack<Question> loadQuestionsByTitle(int instructorID, String questionTitle) {
		IQuestionsAbstractFactory questionsAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		Stack<Question> questions = questionsAbstractFactory.returnQuestionStackInstance();
		Map<Integer, String> mapOfOptions;
		int tmpQuestionID = -1;
		CallStoredProcedure proc = null;
		try {

			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spGetQuestionByTitle(?,?)");
			proc.setParameter(1, questionTitle);
			proc.setParameter(2, instructorID);

			ResultSet results = proc.executeWithResults();
			if (results != null) {
				while (results.next()) {
					if (tmpQuestionID == -1 || tmpQuestionID != results.getInt(1)) {
						mapOfOptions = questionsAbstractFactory.returnMapInstance();
						tmpQuestionID = results.getInt(1);
						
						Question question = questionsAbstractFactory.returnQuestionInstance();
						question.setQuestionID(results.getInt(1));
						question.setQuestionText(results.getString(2));
						question.setCreationDate(results.getDate(3));
						question.setQuestionTypeID(results.getInt(4));
						question.setQuestionTitle(results.getString(5));
						question.setInstructorID(results.getInt(6));
						
						if (question.getQuestionTypeID() == 2 || question.getQuestionTypeID() == 3) {
							mapOfOptions = question.getOptions();
							mapOfOptions.put(results.getInt(8), results.getString(7));
						}
						questions.add(question);
					} else {
						Question tempQuestion = questions.pop();
						mapOfOptions = tempQuestion.getOptions();
						mapOfOptions.put(results.getInt(8), results.getString(7));
						questions.add(tempQuestion);
					}
				}
			}

		} catch (SQLException e) {
			logger.error("Database connection error", e);
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return questions;
	}

}
