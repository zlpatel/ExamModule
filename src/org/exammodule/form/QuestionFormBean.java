package org.exammodule.form;

import java.util.Date;
import java.util.TreeMap;

public class QuestionFormBean 
{
	private Integer questionOrder;
	private Integer questionId;
	private String questionType;
	private Date startTime;
	private String statement;	
	private TreeMap<String,String> optionList;
	private String wholeQuestion;
	private String selectedOption;
	private String message;
	private String questionImage;
		
	public Integer getQuestionOrder() {
		return questionOrder;
	}
	public void setQuestionOrder(Integer questionOrder) {
		this.questionOrder = questionOrder;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public TreeMap<String, String> getOptionList() {
		return optionList;
	}
	public void setOptionList(TreeMap<String, String> optionList) {
		this.optionList = optionList;
	}
	public String getWholeQuestion() {
		return wholeQuestion;
	}
	public void setWholeQuestion(String wholeQuestion) {
		this.wholeQuestion = wholeQuestion;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getQuestionImage() {
		return questionImage;
	}
	public void setQuestionImage(String questionImage) {
		this.questionImage = questionImage;
	}
	
	
}
