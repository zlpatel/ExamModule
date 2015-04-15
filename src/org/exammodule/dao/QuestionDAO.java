package org.exammodule.dao;

import java.util.List;

import org.exammodule.dto.AdditionalQuestionDTO;
import org.exammodule.dto.AdditionalQuestionLookupDTO;
import org.exammodule.dto.RegularQuestionDTO;
import org.exammodule.dto.RightAttemptsDTO;
import org.exammodule.dto.UserDTO;
import org.exammodule.dto.WrongAttemptsDTO;


public interface QuestionDAO 
{
	boolean checkInRightAttempted(String userName,Integer questionId) throws Exception;
	Integer getNumberOfQuestions() throws Exception;
	RegularQuestionDTO getThisRegularQuestion(String questionId) throws Exception;
	AdditionalQuestionDTO getThisAdditionalQuestion(String questionId) throws Exception;
	void markAsRightAttemptedAdditional(String questionId, String selectedOption, UserDTO user) throws Exception;
	void markAsRightAttemptedRegular(String questionId, String selectedOption, UserDTO user) throws Exception;
	void markAsWrongAttemptedRegular(String questionId, String selectedOption, UserDTO user) throws Exception;
	void markAsWrongAttemptedAdditional(String questionId, String selectedOption, UserDTO user) throws Exception;
	RegularQuestionDTO getTodaysQuestion() throws Exception;
	AdditionalQuestionLookupDTO checkIfLookUpTableIsEmpty(String userName) throws Exception;
	AdditionalQuestionDTO getNextAdditionalQuestion(AdditionalQuestionLookupDTO aQlookUpDTO, String userName) throws Exception;
	AdditionalQuestionDTO getFirstAdditionalQuestion() throws Exception;
	String getVideoLink(String questionId) throws Exception;
	long getRightAttemptCount(String userName) throws Exception;
	long getWrongAttemptCount(String userName) throws Exception;
	List<RightAttemptsDTO> getQuestionsListForRightAttempts(String userName, int questionType) throws Exception;
	List<WrongAttemptsDTO> getQuestionsListForWrongAttempts(String userName, int questionType) throws Exception;
}
