package CSCI5308.GroupFormationTool.Survey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Database.DatabaseConfig;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.IQuestionsAbstractFactory;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Questions.QuestionConfiguration;

public class SurveyDB implements ISurveyPersistence {

	Logger logger = LoggerFactory.getLogger(SurveyDB.class);
	
	@Override
	public Stack<SurveyDetails> loadSurveyQuestions(long courseId) {
		ISurveyAbstractFactory surveyAbstractFactory = SurveyConfiguration.instance().getSurveyAbstractFactory();
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		Stack<SurveyDetails> surveyDetails = surveyAbstractFactory.returnSurveyDetailsStackInstance();
		Map<Integer, String> mapOfOptions;
		int tmpQuestionID = -1;
		CallStoredProcedure proc = null;
		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spGetSurveyQuestions(?)");
			proc.setParameter(1, courseId);
			ResultSet results = proc.executeWithResults();
			if (results != null) {
				while (results.next()) {
					if (tmpQuestionID == -1 || tmpQuestionID != results.getInt("questionID")) {
						mapOfOptions = surveyAbstractFactory.returnMapInstance();
						tmpQuestionID = results.getInt("questionID");
						SurveyDetails surveyDetail = surveyAbstractFactory.returnSurveyDetailsInstance();
						surveyDetail.setCourseId(results.getLong("courseID"));
						surveyDetail.setQuestionId(results.getInt("questionID"));
						surveyDetail.setQuestionText(results.getString("questionText"));
						surveyDetail.setCreationDate(results.getDate("creationDate"));
						surveyDetail.setQuestionTypeID(results.getInt("typeID"));
						surveyDetail.setQuestionTitle(results.getString("questionTitle"));
						surveyDetail.setIfPublished(results.getInt("ifPublished"));
						if (surveyDetail.getQuestionTypeID() == 2 || surveyDetail.getQuestionTypeID() == 3) {
							mapOfOptions = surveyDetail.getOptions();
							mapOfOptions.put(results.getInt("optionStoredAs"), results.getString("optionText"));
						}
						surveyDetails.add(surveyDetail);
					} else {
						SurveyDetails tempDetail = surveyDetails.pop();
						mapOfOptions = tempDetail.getOptions();
						mapOfOptions.put(results.getInt("optionStoredAs"), results.getString("optionText"));
						surveyDetails.add(tempDetail);
					}
				}
			}

		} catch (SQLException e) {
			logger.error("Error Connecting Database",e);
			e.printStackTrace();
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return surveyDetails;
	}

	@Override
	public boolean addQuestionToSurvey(SurveyDetails surveyQuestion) {
		CallStoredProcedure proc = null;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spAddQuestionToSurvey(?, ?)");
			proc.setParameter(1, surveyQuestion.getCourseId());
			proc.setParameter(2, surveyQuestion.getQuestionId());
			proc.execute();

		} catch (SQLException e) {
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	@Override
	public boolean removeQuestionFromSurvey(SurveyDetails surveyQuestion) {
		CallStoredProcedure proc = null;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spDeleteQuestionFromSurvey(?, ?)");
			proc.setParameter(1, surveyQuestion.getCourseId());
			proc.setParameter(2, surveyQuestion.getQuestionId());
			proc.execute();

		} catch (SQLException e) {
			logger.error("Error Connecting Database",e);
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	@Override
	public boolean publishSurvey(long courseId) {
		CallStoredProcedure proc = null;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spPublishSurvey(?)");
			proc.setParameter(1, courseId);
			proc.execute();

		} catch (SQLException e) {
			logger.error("Error Connecting Database",e);
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	@Override
	public boolean checkIfCourseContainsSurvey(SurveyDetails surveyDetail) {
		CallStoredProcedure proc = null;
		boolean surveyExists = false;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spCheckSurveyExists(?, ?)");
			proc.setParameter(1, surveyDetail.getCourseId());
			proc.registerOutputParameterBoolean(2);
			proc.execute();
			surveyExists = proc.getBoolean(2);
			if (surveyExists) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			logger.error("Error Connecting Database",e);
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
	}

	@Override
	public boolean checkIfSurveyPublished(SurveyDetails surveyDetail) {
		CallStoredProcedure proc = null;
		boolean surveyPublished = false;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spCheckSurveyPublished(?, ?)");
			proc.setParameter(1, surveyDetail.getCourseId());
			proc.registerOutputParameterBoolean(2);
			proc.execute();
			surveyPublished = proc.getBoolean(2);
			if (surveyPublished) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			logger.error("Error Connecting Database",e);
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
	}

	@Override
	public long findSurveyInstructor(long courseID) {
		CallStoredProcedure proc = null;
		long surveyPublished = 0;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spFindInstructorID(?, ?)");
			proc.setParameter(1, courseID);
			proc.registerOutputParameterLong(2);
			proc.execute();
			surveyPublished = proc.getLong(2);
			return surveyPublished;
		} catch (SQLException e) {
			logger.error("Error Connecting Database",e);
			return surveyPublished;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
	}

	@Override
	public boolean checkIfSurveyExist(Course course) {
		CallStoredProcedure proc = null;
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

        System.out.println("Course: "+ course.getId());
        try {
            proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spCheckIfSurveyPublished(?)");
            proc.setParameter(1, course.getId());
            ResultSet results = proc.executeWithResults();
			if (null != results) {
                while(results.next()){
                    System.out.println("Survey Exist: "+ results.getInt(1));
                    if(results.getInt(1) == 1){
                        return true;
                    }else{
                        return false;
                    }   
                }
            }
          
            
        } catch (SQLException e) {
            logger.error("Error Connecting Database",e);
            e.printStackTrace();
            
        }finally{
            if (null != proc) {
				proc.cleanup();
			}
        }

        return false;
	}

	@Override
	public List<Question> getSurveyQuestions(Course course) {
		IQuestionsAbstractFactory questionsAbstractFactory = QuestionConfiguration.instance().getQuestionsAbstractFactory();
    	IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory(); 	
        CallStoredProcedure proc = null;
        IQuestionPersistence quesDB = QuestionConfiguration.instance().getQuestionDB();
        List<Question> questions = questionsAbstractFactory.returnQuestionStackInstance();
        System.out.println("Course: "+ course.getId());
        try {
        	proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spLoadSurveyQuestionsU(?)");
            proc.setParameter(1, course.getId());
            ResultSet results = proc.executeWithResults();
			if (null != results) {
                while(results.next()){
                    System.out.println("Survey Question IDs: "+ results.getInt(1));
                    questions.add(quesDB.loadSingleQuestion(results.getInt(1)));
				}
				logger.info("Fetched Survey Questions");
                return questions;
            }
            
            
        } catch (SQLException e) {
            logger.error("Error Connecting Database",e);
            e.printStackTrace();
            
        }finally{
            if (null != proc) {
				proc.cleanup();
			}
        }

        return null;

	}

	@Override
	public boolean uploadResponses(SurveyResponse surveyResponse) {
		CallStoredProcedure proc = null;
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

        Long courseID = surveyResponse.getCourseId();
        Integer userID=surveyResponse.getUserId();
      
            try {
                for (SurveyAnswer answer : surveyResponse.getAnswers()) {
                    System.out.println("QuestionID in DAO: "+answer.getQuestion().getQuestionTypeID()); 
                    System.out.println("Answer: "+answer.getSingleAnswer()); 
                    Integer questionID = answer.getQuestion().getQuestionID();
                    String response =  answer.getSingleAnswer();
                    if(answer.getQuestion().getQuestionTypeID() == 3 && response.contains(",")){
                        List<String> multipleAnswers = Arrays.asList(response.split(","));
                        for(String multipleAnswer: multipleAnswers){
                            proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spInsertSurveyAnswers(?,?,?,?)");
                            proc.setParameter(1, userID);
                            proc.setParameter(2, courseID);
                            proc.setParameter(3, questionID);
                            proc.setParameter(4, multipleAnswer);
                            if(response != null){
                                proc.execute();
                            }
                        }
                    }else{
                        proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spInsertSurveyAnswers(?,?,?,?)");
                        proc.setParameter(1, userID);
                        proc.setParameter(2, courseID);
                        proc.setParameter(3, questionID);
                        proc.setParameter(4, response);
                        if(response != null){
                            proc.execute();
                        }
                    }       
				}
				logger.info("Responses uploaded");
                return true;
                
            } catch (SQLException e) {
                logger.error("Error Connecting Database",e);
                e.printStackTrace();
                
            }finally{
                if (null != proc) {
                    proc.cleanup();
                }
            }
    
            return false;
        
       
	}

	@Override
	public boolean checkSurveyTaken(Course course, Integer userId) {
	    IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();
		CallStoredProcedure proc = null;
        System.out.println("Inside surveydetails db user id: "+userId); 
        System.out.println("Inside surveydetails db course id: "+course.getId()); 
        try {
            proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spCheckIfSurveyTaken(?,?)");
            proc.setParameter(1, userId);
            proc.setParameter(2, course.getId());
            ResultSet results = proc.executeWithResults();
			if (results.next()) {
                System.out.println("Survey Exists"); 
               return true;
            }else{
                System.out.println("Survey Does Not Exists"); 
                return false;
            }
              
        } catch (SQLException e) {
            logger.error("Survey data is not recieved from database");
            e.printStackTrace();
            
        }finally{
            if (null != proc) {
				proc.cleanup();
			}
        }
		System.out.println("Survey Does Not Exists"); 
		logger.info("Survey Does Not Exists");
        return false;
	}

}
