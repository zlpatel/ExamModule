package org.exammodule.form;

import java.util.Date;

public class AdditionalQuestionsRecordFormBean implements Comparable<AdditionalQuestionsRecordFormBean>{
	private String questionName;
	private String markedAnswer;
	private Date dateTime;
	private boolean result;
	
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public String getMarkedAnswer() {
		return markedAnswer;
	}
	public void setMarkedAnswer(String markedAnswer) {
		this.markedAnswer = markedAnswer;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	@Override
	public int compareTo(AdditionalQuestionsRecordFormBean o1) {
		return this.dateTime.compareTo(o1.dateTime);
    }
	
}
