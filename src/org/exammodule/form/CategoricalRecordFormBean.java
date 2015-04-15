package org.exammodule.form;

public class CategoricalRecordFormBean {
	private String categoryName;
	private long rightAttemptCount;
	private long wrongAttemptCount;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public long getRightAttemptCount() {
		return rightAttemptCount;
	}
	public void setRightAttemptCount(long rightAttemptCount) {
		this.rightAttemptCount = rightAttemptCount;
	}
	public long getWrongAttemptCount() {
		return wrongAttemptCount;
	}
	public void setWrongAttemptCount(long wrongAttemptCount) {
		this.wrongAttemptCount = wrongAttemptCount;
	}
	
}
