package org.exammodule.form;

import java.util.TreeMap;

public class QuestionFormBean 
{
	private String questionId;
	private String statement;
	private int typeId;
	private TreeMap<String,String> optionList;
	private String selectedOption;
	private String message;
	private String wholeQuestion;
	private boolean correct;
	private String videoLink;
	
	public String getVideoLink() {
		return videoLink;
	}
	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int type) {
		this.typeId = type;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public TreeMap<String,String> getOptionList() {
		return optionList;
	}
	public void setOptionList(TreeMap<String,String> optionList) {
		this.optionList = optionList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getWholeQuestion() {
		return wholeQuestion;
	}
	public void setWholeQuestion(String wholeQuestion) {
		this.wholeQuestion = wholeQuestion;
	}
	
}
