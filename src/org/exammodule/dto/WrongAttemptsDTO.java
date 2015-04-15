package org.exammodule.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(catalog = "test", schema = "", name="WrongAttempts")
@XmlRootElement
public class WrongAttemptsDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="regular_question_id", referencedColumnName="question_id")
	private RegularQuestionDTO questionRegular;
	
	@ManyToOne
	@JoinColumn(name="additional_question_id", referencedColumnName="question_id")
	private AdditionalQuestionDTO questionAdditional;
	
	@Id
	@ManyToOne
	@JoinColumn(name="userName", referencedColumnName="userName", nullable = false)
	private UserDTO user;
	
	@Id
	@Column(name="attempt_time")
	@Temporal(TemporalType.TIMESTAMP) 
	public Date attemptTime;
	
	@Column(name="selected_answer", nullable = false)
	private String selectedAnswer;
	
	@ManyToOne
    @JoinColumn(name="type_id", referencedColumnName="type_id")
    private QuestionTypeDTO type;
	
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
	public QuestionTypeDTO getType() {
		return type;
	}
	public void setType(QuestionTypeDTO type) {
		this.type = type;
	}
	
	public RegularQuestionDTO getQuestionRegular() {
		return questionRegular;
	}
	public void setQuestionRegular(RegularQuestionDTO questionRegular) {
		this.questionRegular = questionRegular;
	}
	public AdditionalQuestionDTO getQuestionAdditional() {
		return questionAdditional;
	}
	public void setQuestionAdditional(AdditionalQuestionDTO questionAdditional) {
		this.questionAdditional = questionAdditional;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
}
