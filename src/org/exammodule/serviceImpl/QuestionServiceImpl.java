package org.exammodule.serviceImpl;

import java.util.Date;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.exammodule.dao.QuestionDAO;
import org.exammodule.dao.UserDAO;
import org.exammodule.dto.QuestionsDTO;
import org.exammodule.dto.UserDTO;
import org.exammodule.exception.AllQuestionsAnsweredException;
import org.exammodule.form.QuestionFormBean;
import org.exammodule.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
	protected static Logger logger = Logger.getLogger("question service");
	@Autowired
	private QuestionDAO questionDAO;
	@Autowired
	private UserDAO userDAO;

	// @Transactional
	// @Override
	// public QuestionFormBean getAQuestion(String userName) throws
	// NoAdditionalQuestionAvailableException,NoAssignedQuestionException,AllAdditionalQuestionAnsweredException,Exception
	// {
	// boolean isAlreadyAnsweredCorrectly=true;
	// logger.debug("Request to find a question in QuestionService");
	// TreeMap<String,String> optionList = new TreeMap<String,String>();
	// StringBuffer wholeQuestion=new StringBuffer();
	// wholeQuestion.append("$");
	// QuestionFormBean questionFormBean=new QuestionFormBean();
	// RegularQuestionDTO questionDTO=questionDAO.getTodaysQuestion();
	// if(questionDTO!=null)
	// isAlreadyAnsweredCorrectly=questionDAO.checkInRightAttempted(userName,questionDTO.getQuestionId());
	// else
	// throw new Exception("No assigned question found!");
	// if(isAlreadyAnsweredCorrectly)
	// {
	// AdditionalQuestionLookupDTO
	// AQlookUpDTO=questionDAO.checkIfLookUpTableIsEmpty(userName);
	// AdditionalQuestionDTO additionalQuestionDTO;
	// if(AQlookUpDTO!=null){
	// additionalQuestionDTO=questionDAO.getNextAdditionalQuestion(AQlookUpDTO,userName);
	// }
	// else {
	// additionalQuestionDTO=questionDAO.getFirstAdditionalQuestion();
	// }
	// questionFormBean.setQuestionId(additionalQuestionDTO.getQuestionId().toString());
	// questionFormBean.setImageName(additionalQuestionDTO.getImageName());
	// questionFormBean.setTypeId(additionalQuestionDTO.getType().getTypeId());
	// questionFormBean.setStatement(additionalQuestionDTO.getStatement());
	// wholeQuestion.append("$ \\documentclass[20pt]{report} $ $\\begin{document} $ "+additionalQuestionDTO.getStatement());
	// wholeQuestion.append(" $ \\\\ $");
	// optionList.put("1",additionalQuestionDTO.getOption1());
	// wholeQuestion.append("1. "+additionalQuestionDTO.getOption1()+" \\newline ");
	// optionList.put("2",additionalQuestionDTO.getOption2());
	// wholeQuestion.append("2. "+additionalQuestionDTO.getOption2()+" \\newline ");
	// optionList.put("3",additionalQuestionDTO.getOption3());
	// wholeQuestion.append("3. "+additionalQuestionDTO.getOption3()+" \\newline ");
	// optionList.put("4",additionalQuestionDTO.getOption4());
	// wholeQuestion.append("4. "+additionalQuestionDTO.getOption4()+" \\newline ");
	// optionList.put("5",additionalQuestionDTO.getOption5());
	// wholeQuestion.append("5. "+additionalQuestionDTO.getOption5()+" \\newline ");
	// wholeQuestion.append(" $\\end{document} $");
	// questionFormBean.setOptionList(optionList);
	// questionFormBean.setWholeQuestion(wholeQuestion.toString());
	// questionFormBean.setSelectedOption(optionList.get("1"));
	// return questionFormBean;
	// }
	// questionFormBean.setQuestionId(questionDTO.getQuestionId().toString());
	// questionFormBean.setImageName(questionDTO.getImageName());
	// questionFormBean.setTypeId(questionDTO.getType().getTypeId());
	// questionFormBean.setStatement(questionDTO.getStatement());
	// wholeQuestion.append("$ \\documentclass[20pt]{report} $ $\\begin{document} $ "+questionDTO.getStatement());
	// wholeQuestion.append(" $ \\\\ $");
	// optionList.put("1",questionDTO.getOption1());
	// wholeQuestion.append("1. "+questionDTO.getOption1()+" \\newline ");
	// optionList.put("2",questionDTO.getOption2());
	// wholeQuestion.append("2. "+questionDTO.getOption2()+" \\newline ");
	// optionList.put("3",questionDTO.getOption3());
	// wholeQuestion.append("3. "+questionDTO.getOption3()+" \\newline ");
	// optionList.put("4",questionDTO.getOption4());
	// wholeQuestion.append("4. "+questionDTO.getOption4()+" \\newline ");
	// optionList.put("5",questionDTO.getOption5());
	// wholeQuestion.append("5. "+questionDTO.getOption5()+" \\newline ");
	// wholeQuestion.append(" $\\end{document} $");
	// questionFormBean.setOptionList(optionList);
	// questionFormBean.setWholeQuestion(wholeQuestion.toString());
	// questionFormBean.setSelectedOption(optionList.get("1"));
	// return questionFormBean;
	//
	// }

	@Transactional
	@Override
	public boolean checkAnswer(QuestionFormBean question, String userName)
			throws Exception {
		QuestionsDTO questionsDTO;
		UserDTO user = userDAO.fetchUserByUserName(userName);

		questionsDTO = (QuestionsDTO) questionDAO.getThisQuestion(question
				.getQuestionOrder());

		if (question.getSelectedOption().equals(questionsDTO.getAnswer())) {
			questionDAO.markAsRightAttempted(questionsDTO,question.getStartTime(),
					question.getSelectedOption(), user);
			return true;
		} else {
			questionDAO.markAsWrongAttempted(questionsDTO,question.getStartTime(),
					question.getSelectedOption(), user);
			return false;
		}
	}

	@Override
	public QuestionFormBean getAQuestion(Integer questionOrder)
			throws AllQuestionsAnsweredException,Exception {
		TreeMap<String, String> optionList = new TreeMap<String, String>();
		StringBuffer wholeQuestion = new StringBuffer();
		wholeQuestion.append("$");
		QuestionFormBean questionFormBean = new QuestionFormBean();
		QuestionsDTO questionsDTO = questionDAO.getThisQuestion(questionOrder);
		questionFormBean.setQuestionOrder(questionsDTO.getQuestionOrder());
		questionFormBean.setQuestionId(questionsDTO.getQuestionId());
		questionFormBean.setQuestionType(questionsDTO.getQuestionType());
		questionFormBean.setQuestionImage(questionsDTO.getQuestionImage());
		questionFormBean.setStartTime(new Date());
		questionFormBean.setStatement(questionsDTO.getStatement());
		wholeQuestion
				.append("$ \\documentclass[20pt]{report} $ $\\begin{document} $ "
						+ questionsDTO.getStatement());
		wholeQuestion.append(" $ \\\\ $");
		optionList.put("a", questionsDTO.getOption1());
		wholeQuestion.append("a. " + questionsDTO.getOption1() + " \\newline ");
		optionList.put("b", questionsDTO.getOption2());
		wholeQuestion.append("b. " + questionsDTO.getOption2() + " \\newline ");
		optionList.put("c", questionsDTO.getOption3());
		wholeQuestion.append("c. " + questionsDTO.getOption3() + " \\newline ");
		optionList.put("d", questionsDTO.getOption4());
		wholeQuestion.append("d. " + questionsDTO.getOption4() + " \\newline ");
		optionList.put("e", questionsDTO.getOption5());
		wholeQuestion.append("e. " + questionsDTO.getOption5() + " \\newline ");
		wholeQuestion.append(" $\\end{document} $");
		questionFormBean.setOptionList(optionList);
		questionFormBean.setWholeQuestion(wholeQuestion.toString());
		questionFormBean.setSelectedOption(optionList.get("a"));
		return questionFormBean;

	}

	@Transactional
	@Override
	public String getFeedbackVideoLink(QuestionFormBean question)
			throws Exception {
		if (question.getQuestionType() == "b")
			return null;
		String feedbackVideoLink = questionDAO.getFeedbackVideoLink(question
				.getQuestionOrder());
		return feedbackVideoLink;
	}

	@Transactional
	@Override
	public String getFeedbackImage(QuestionFormBean question) throws Exception {
		if (question.getQuestionType() == "b")
			return null;
		String feedbackImageName = questionDAO.getFeedbackImageName(question
				.getQuestionOrder());
		return feedbackImageName;
	}
}