package org.exammodule.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(catalog = "test", schema = "", name="Additional_questions_lookup")
@XmlRootElement
public class AdditionalQuestionLookupDTO implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne
	@JoinColumn(name="userName", referencedColumnName="userName", nullable = false)
	private UserDTO user;
	
	@ManyToOne
	@JoinColumn(name="last_question_id", referencedColumnName="question_id", nullable = false)
	private AdditionalQuestionDTO question;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public AdditionalQuestionDTO getQuestion() {
		return question;
	}

	public void setQuestion(AdditionalQuestionDTO question) {
		this.question = question;
	}
	
}
