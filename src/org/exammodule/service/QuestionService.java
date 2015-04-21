package org.exammodule.service;

import org.exammodule.form.QuestionFormBean;


public interface QuestionService 
{
	QuestionFormBean getAQuestion(Integer questionOrder) throws Exception;
	public boolean checkAnswer(QuestionFormBean question,String userName) throws Exception;
	String getFeedbackVideoLink(QuestionFormBean question) throws Exception;
	String getFeedbackImage(QuestionFormBean question) throws Exception;
}
