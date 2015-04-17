package org.exammodule.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(catalog = "ExamModuleDB", schema = "", name="Attempts")
@XmlRootElement
public class AttemptsDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
		
	@Id
	@ManyToOne
	@JoinColumn(name="userName", referencedColumnName="userName", nullable = false)
	private UserDTO user;
	
	@Id
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="question_id", referencedColumnName="question_id", nullable = false),
		@JoinColumn(name="question_order", referencedColumnName="question_order", nullable = false),
		@JoinColumn(name="question_type", referencedColumnName="question_type", nullable = false)
    })
	private QuestionsDTO question;
	
	@Column(name="start_time")
	@Temporal(TemporalType.TIMESTAMP) 
	private Date startTime;
	
	@Column(name="attempt_time")
	@Temporal(TemporalType.TIMESTAMP) 
	private Date attemptTime;
	
	@Column(name="selected_answer", nullable = false)
	private String selectedAnswer;
	
	@Column(name="right_attempt")
	private boolean rightAttempt;
	
	@Column(name="wrong_attempt")
	private boolean wrongAttempt;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public QuestionsDTO getQuestion() {
		return question;
	}

	public void setQuestion(QuestionsDTO question) {
		this.question = question;
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

	public String getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public boolean isRightAttempt() {
		return rightAttempt;
	}

	public void setRightAttempt(boolean rightAttempt) {
		this.rightAttempt = rightAttempt;
	}

	public boolean isWrongAttempt() {
		return wrongAttempt;
	}

	public void setWrongAttempt(boolean wrongAttempt) {
		this.wrongAttempt = wrongAttempt;
	}
	
}
