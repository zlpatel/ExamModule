package org.exammodule.daoImpl;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.exammodule.dao.QuestionDAO;
import org.exammodule.dto.AdditionalQuestionDTO;
import org.exammodule.dto.AdditionalQuestionLookupDTO;
import org.exammodule.dto.RegularQuestionDTO;
import org.exammodule.dto.RightAttemptsDTO;
import org.exammodule.dto.UserDTO;
import org.exammodule.dto.WrongAttemptsDTO;
import org.exammodule.exception.AllAdditionalQuestionAnsweredException;
import org.exammodule.exception.NoAdditionalQuestionAvailableException;
import org.exammodule.exception.NoAssignedQuestionException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDAOImpl implements QuestionDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	protected static Logger logger = Logger.getLogger("dao");

	public boolean checkInRightAttempted(String userName,Integer questionId) throws Exception
	{
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM RightAttemptsDTO r WHERE r.questionRegular.questionId = :questionId and r.user.userName = :userName");
		query.setString("questionId", questionId.toString());
		query.setString("userName", userName);
		long count = (Long) query.uniqueResult();
		if(count!=0)
			return true;
		return false;
	}

	@Override
	public RegularQuestionDTO getThisRegularQuestion(String questionId) throws Exception 
	{
		logger.debug("Request to find a given question in QuestionDAO");
		RegularQuestionDTO questionDTO=new RegularQuestionDTO(); 
		Query query = sessionFactory.getCurrentSession().createQuery("FROM RegularQuestionDTO q WHERE q.questionId = :questionId");
		query.setString("questionId", questionId);
		questionDTO = (RegularQuestionDTO) query.uniqueResult();
		return questionDTO;
		
	}
    @Override
    public AdditionalQuestionDTO getThisAdditionalQuestion(String questionId) throws Exception
	{
		logger.debug("Request to find a given question in QuestionDAO");
		AdditionalQuestionDTO questionDTO=new AdditionalQuestionDTO(); 
		Query query = sessionFactory.getCurrentSession().createQuery("FROM AdditionalQuestionDTO q WHERE q.questionId = :questionId");
		query.setString("questionId", questionId);
		questionDTO = (AdditionalQuestionDTO) query.uniqueResult();
		return questionDTO;
	}
	@Override
	public void markAsRightAttemptedRegular(String questionId,String selectedAnswer, UserDTO user) throws Exception {
		logger.debug("Marking the question as Right Attempt");
		RightAttemptsDTO rightAttempt=new RightAttemptsDTO();
		java.util.Date date= new java.util.Date();
		rightAttempt.setAttemptTime(new Timestamp(date.getTime()));
		RegularQuestionDTO question=getThisRegularQuestion(questionId);
		rightAttempt.setQuestionRegular(question);
		rightAttempt.setSelectedAnswer(selectedAnswer);
		rightAttempt.setUser(user);
		rightAttempt.setType(question.getType());
		sessionFactory.getCurrentSession().save(rightAttempt);
	}
	@Override
	public void markAsRightAttemptedAdditional(String questionId,String selectedAnswer, UserDTO user) throws Exception{
		logger.debug("Marking the question as Right Attempt");
		RightAttemptsDTO rightAttempt=new RightAttemptsDTO();
		java.util.Date date= new java.util.Date();
		rightAttempt.setAttemptTime(new Timestamp(date.getTime()));
		AdditionalQuestionDTO questionAdditional=getThisAdditionalQuestion(questionId);
		rightAttempt.setQuestionAdditional(questionAdditional);
		rightAttempt.setSelectedAnswer(selectedAnswer);
		rightAttempt.setUser(user);
		rightAttempt.setType(questionAdditional.getType());
		sessionFactory.getCurrentSession().save(rightAttempt);
		AdditionalQuestionLookupDTO lookUp=new AdditionalQuestionLookupDTO();
		lookUp.setQuestion(questionAdditional);
		lookUp.setUser(user);
		sessionFactory.getCurrentSession().saveOrUpdate(lookUp);
	}
	@Override
	public void markAsWrongAttemptedRegular(String questionId,String selectedAnswer, UserDTO user) throws Exception{
		logger.debug("Marking the question as Wrong Attempt");
		System.out.println("Selected Answer is :::: "+selectedAnswer);
		WrongAttemptsDTO wrongAttempt = new WrongAttemptsDTO();
		wrongAttempt=new WrongAttemptsDTO();
		RegularQuestionDTO question=getThisRegularQuestion(questionId);
		wrongAttempt.setQuestionRegular(question);
		wrongAttempt.setSelectedAnswer(selectedAnswer);
		wrongAttempt.setUser(user);
		wrongAttempt.setType(question.getType());
		java.util.Date date= new java.util.Date();
		wrongAttempt.setAttemptTime(new Timestamp(date.getTime()));
		sessionFactory.getCurrentSession().save(wrongAttempt);
	}

	@Override
	public void markAsWrongAttemptedAdditional(String questionId,String selectedAnswer, UserDTO user) throws Exception{
		logger.debug("Marking the question as Wrong Attempt");
		WrongAttemptsDTO wrongAttempt = new WrongAttemptsDTO();
		wrongAttempt=new WrongAttemptsDTO();
		AdditionalQuestionDTO additionalQuestion=getThisAdditionalQuestion(questionId);
		wrongAttempt.setQuestionAdditional(additionalQuestion);
		wrongAttempt.setSelectedAnswer(selectedAnswer);
		wrongAttempt.setUser(user);
		wrongAttempt.setType(additionalQuestion.getType());
		java.util.Date date= new java.util.Date();
		wrongAttempt.setAttemptTime(new Timestamp(date.getTime()));
		sessionFactory.getCurrentSession().save(wrongAttempt);
		
	}

	@Override
	public RegularQuestionDTO getTodaysQuestion() throws NoAssignedQuestionException,Exception{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		// get the assigned question for today
		RegularQuestionDTO questionDTO;
		Query query = sessionFactory.getCurrentSession().createQuery("FROM RegularQuestionDTO q WHERE q.assignedDate = :assignedDate");
		query.setString("assignedDate", dateFormat.format(date));
		questionDTO = (RegularQuestionDTO) query.uniqueResult();
		if(questionDTO!=null)
			return questionDTO;
		logger.error("Question assigneed for today!");
		throw new NoAssignedQuestionException("No Question assigneed for today!");
	}

	@Override
	public AdditionalQuestionLookupDTO checkIfLookUpTableIsEmpty(String userName) throws Exception{
		Query query = sessionFactory.getCurrentSession().createQuery("FROM AdditionalQuestionLookupDTO aql WHERE aql.user.userName = :userName");
		query.setString("userName", userName);
		AdditionalQuestionLookupDTO AQlookUpDTO= (AdditionalQuestionLookupDTO) query.uniqueResult();
		return AQlookUpDTO;
	}

	@Override
	public AdditionalQuestionDTO getNextAdditionalQuestion(AdditionalQuestionLookupDTO AQlookUpDTO, String userName) throws AllAdditionalQuestionAnsweredException,Exception{
		Integer nextQuestionId;
		nextQuestionId=AQlookUpDTO.getQuestion().getQuestionId() + 1;
		Query query = sessionFactory.getCurrentSession().createQuery("FROM AdditionalQuestionDTO aq WHERE aq.questionId = :questionId");
		query.setString("questionId", nextQuestionId.toString());
		AdditionalQuestionDTO nextAditionalQuestion= (AdditionalQuestionDTO) query.uniqueResult();
		if(nextAditionalQuestion!=null)
			return nextAditionalQuestion;
		else
			throw new AllAdditionalQuestionAnsweredException("All additional questions already answered!");
	}

	@Override
	public AdditionalQuestionDTO getFirstAdditionalQuestion() throws NoAdditionalQuestionAvailableException,Exception{
		Query query = sessionFactory.getCurrentSession().createQuery("FROM AdditionalQuestionDTO aq WHERE aq.questionId = :questionId");
		query.setString("questionId", new Integer(1).toString());
		AdditionalQuestionDTO firstAditionalQuestion= (AdditionalQuestionDTO) query.uniqueResult();
		if(firstAditionalQuestion!=null)
			return firstAditionalQuestion;
		else
			throw new NoAdditionalQuestionAvailableException("No additional questions added!");
	}

	@Override
	public String getVideoLink(String questionId) throws Exception{
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT q.videoLink FROM RegularQuestionDTO q WHERE q.questionId = :questionId");
		query.setString("questionId", questionId);
		String videoLink = (String) query.uniqueResult();
		return videoLink;
	}

	@Override
	public long getRightAttemptCount(String userName) throws Exception{
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM RightAttemptsDTO r WHERE r.user.userName = :userName");
		query.setString("userName", userName);
		long count = (Long) query.uniqueResult();
		return count;
	}
	@Override
	public long getWrongAttemptCount(String userName) throws Exception{
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM WrongAttemptsDTO w WHERE w.user.userName = :userName");
		query.setString("userName", userName);
		long count = (Long) query.uniqueResult();
		return count;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<RightAttemptsDTO> getQuestionsListForRightAttempts(String userName, int questionType) throws Exception{
		Query query = sessionFactory.getCurrentSession().createQuery("FROM RightAttemptsDTO r WHERE r.user.userName = :userName and r.type.typeId = :typeId");
		query.setString("userName", userName);
		query.setInteger("typeId", questionType);
		List<RightAttemptsDTO> list=query.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<WrongAttemptsDTO> getQuestionsListForWrongAttempts(String userName, int questionType) throws Exception{
		Query query = sessionFactory.getCurrentSession().createQuery("FROM WrongAttemptsDTO w WHERE w.user.userName = :userName and w.type.typeId = :typeId");
		query.setString("userName", userName);
		query.setInteger("typeId", questionType);
		List<WrongAttemptsDTO> list=query.list();
		return list;
	}
	@Override
	public Integer getNumberOfQuestions() throws Exception {
		logger.debug("Request to find number of questions");
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT COUNT(q) FROM RegularQuestionDTO q");
		Long qouestionCount = (Long)query.uniqueResult();
		Integer i = (int) (long) qouestionCount;
		return i;
	}
}
