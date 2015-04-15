package org.exammodule.form;

public class StudentsRecordFormBean {
	private String studentName;
	private String userName;
	private long rightAttemptCount;
	private long wrongAttemptCount;
	
	public String getStudentName() {
		return studentName;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	public void setWrongAttemptCount(int wrongAttemptCount) {
		this.wrongAttemptCount = wrongAttemptCount;
	}
	
}
