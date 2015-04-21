package org.exammodule.dao;

import java.util.Date;
import java.util.List;

import org.exammodule.dto.AttemptsDTO;
import org.exammodule.dto.QuestionsDTO;
import org.exammodule.dto.UserDTO;



public interface QuestionDAO 
{
	Integer getNumberOfQuestions() throws Exception;
	QuestionsDTO getThisQuestion(Integer questionOrder) throws Exception;
	void markAsRightAttempted(QuestionsDTO question,Date startTime, String selectedOption, UserDTO user) throws Exception;
	void markAsWrongAttempted(QuestionsDTO question,Date startTime, String selectedOption, UserDTO user) throws Exception;
	long getRightAttemptCount(String userName) throws Exception;
	long getWrongAttemptCount(String userName) throws Exception;
	List<AttemptsDTO> getQuestionsListForRightAttempts(String userName) throws Exception;
	List<AttemptsDTO> getQuestionsListForWrongAttempts(String userName) throws Exception;
	List<AttemptsDTO> getQuestionsListForRightAttempts(String userName, String questionType) throws Exception;
	List<AttemptsDTO> getQuestionsListForWrongAttempts(String userName, String questionType) throws Exception;
	String getFeedbackVideoLink(Integer questionOrder) throws Exception;
	String getFeedbackImageName(Integer questionOrder) throws Exception;
}
