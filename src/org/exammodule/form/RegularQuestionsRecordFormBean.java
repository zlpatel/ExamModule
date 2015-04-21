package org.exammodule.form;

import java.util.Date;

public class RegularQuestionsRecordFormBean implements Comparable<RegularQuestionsRecordFormBean>{
	private String questionName;
	private String markedAnswer;
	private Date startTime;
	private Date attemptTime;
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getAttemptTime() {
		return attemptTime;
	}
	public void setAttemptTime(Date attemptTime) {
		this.attemptTime = attemptTime;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	@Override
	public int compareTo(RegularQuestionsRecordFormBean o1) {
		return this.startTime.compareTo(o1.startTime);
    }
	
}
