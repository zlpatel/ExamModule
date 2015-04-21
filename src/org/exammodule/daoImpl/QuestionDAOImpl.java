package org.exammodule.daoImpl;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.exammodule.dao.QuestionDAO;
import org.exammodule.dto.AttemptsDTO;
import org.exammodule.dto.QuestionsDTO;
import org.exammodule.dto.UserDTO;
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

	
	@Override
	public QuestionsDTO getThisQuestion(Integer questionOrder) throws Exception 
	{
		logger.debug("Request to find a given question in QuestionDAO");
		QuestionsDTO questionsDTO=new QuestionsDTO(); 
		Query query = sessionFactory.getCurrentSession().createQuery("FROM QuestionsDTO q WHERE q.questionOrder = :questionOrder");
		query.setInteger("questionOrder", questionOrder);
		questionsDTO = (QuestionsDTO) query.uniqueResult();
		return questionsDTO;
		
	}
    
	@Override
	public void markAsRightAttempted(QuestionsDTO questionsDTO,Date startTime,String selectedAnswer, UserDTO user) throws Exception {
		logger.debug("Marking the question as Right Attempt");
		AttemptsDTO rightAttempt=new AttemptsDTO();
		java.util.Date date= new java.util.Date();
		rightAttempt.setStartTime(new Timestamp(startTime.getTime()));
		rightAttempt.setAttemptTime(new Timestamp(date.getTime()));
		rightAttempt.setQuestion(questionsDTO);
		rightAttempt.setSelectedAnswer(selectedAnswer);
		rightAttempt.setUser(user);
		rightAttempt.setRightAttempt(true);
		sessionFactory.getCurrentSession().save(rightAttempt);
	}
	
	@Override
	public void markAsWrongAttempted(QuestionsDTO questionsDTO,Date startTime,String selectedAnswer, UserDTO user) throws Exception{
		logger.debug("Marking the question as Wrong Attempt");
		AttemptsDTO wrongAttempt=new AttemptsDTO();
		java.util.Date date= new java.util.Date();
		wrongAttempt.setStartTime(new Timestamp(startTime.getTime()));
		wrongAttempt.setAttemptTime(new Timestamp(date.getTime()));
		wrongAttempt.setQuestion(questionsDTO);
		wrongAttempt.setSelectedAnswer(selectedAnswer);
		wrongAttempt.setUser(user);
		wrongAttempt.setWrongAttempt(true);
		sessionFactory.getCurrentSession().save(wrongAttempt);
	}

	@Override
	public long getRightAttemptCount(String userName) throws Exception{
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM AttemptsDTO r WHERE r.user.userName = :userName and r.rightAttempt = :rightAttempt");
		query.setString("userName", userName);
		query.setBoolean("rightAttempt", true);
		long count = (Long) query.uniqueResult();
		return count;
	}
	@Override
	public long getWrongAttemptCount(String userName) throws Exception{
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM AttemptsDTO r WHERE r.user.userName = :userName and r.wrongAttempt = :wrongAttempt");
		query.setString("userName", userName);
		query.setBoolean("wrongAttempt", true);
		long count = (Long) query.uniqueResult();
		return count;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<AttemptsDTO> getQuestionsListForRightAttempts(String userName) throws Exception{
		Query query = sessionFactory.getCurrentSession().createQuery("FROM AttemptsDTO r WHERE r.user.userName = :userName and r.rightAttempt = :rightAttempt");
		query.setString("userName", userName);
		query.setBoolean("rightAttempt", true);
		List<AttemptsDTO> list=query.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<AttemptsDTO> getQuestionsListForWrongAttempts(String userName) throws Exception{
		Query query = sessionFactory.getCurrentSession().createQuery("FROM AttemptsDTO w WHERE w.user.userName = :userName and r.wrongAttempt = :wrongAttempt");
		query.setString("userName", userName);
		query.setBoolean("wrongAttempt", true);
		List<AttemptsDTO> list=query.list();
		return list;
	}
	@Override
	public Integer getNumberOfQuestions() throws Exception {
		logger.debug("Request to find number of questions");
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT COUNT(q) FROM QuestionsDTO q");
		Long qouestionCount = (Long)query.uniqueResult();
		Integer i = (int) (long) qouestionCount;
		return i;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttemptsDTO> getQuestionsListForRightAttempts(
			String userName, String questionType) throws Exception {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM AttemptsDTO r WHERE r.user.userName = :userName and r.question.questionType = :questionType and r.rightAttempt = :rightAttempt");
		query.setString("userName", userName);
		query.setString("questionType", questionType);
		query.setBoolean("rightAttempt", true);
		List<AttemptsDTO> list=query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttemptsDTO> getQuestionsListForWrongAttempts(
			String userName, String questionType) throws Exception {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM AttemptsDTO r WHERE r.user.userName = :userName and r.question.questionType = :questionType and r.wrongAttempt = :wrongAttempt");
		query.setString("userName", userName);
		query.setString("questionType", questionType);
		query.setBoolean("wrongAttempt", true);
		List<AttemptsDTO> list=query.list();
		return list;
	}

	@Override
	public String getFeedbackVideoLink(Integer questionOrder) throws Exception{
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT q.feedbackVideoLink FROM QuestionsDTO q WHERE q.questionOrder = :questionOrder");
		query.setInteger("questionOrder", questionOrder);
		String feedbackVideoLink = (String) query.uniqueResult();
		return feedbackVideoLink;
	}

	
	@Override
	public String getFeedbackImageName(Integer questionOrder) throws Exception {
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT q.feedbackImage FROM QuestionsDTO q WHERE q.questionOrder = :questionOrder");
		query.setInteger("questionOrder", questionOrder);
		String feedbackImageName = (String) query.uniqueResult();
		return feedbackImageName;
	}
}
