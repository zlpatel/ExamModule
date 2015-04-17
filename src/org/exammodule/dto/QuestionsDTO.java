/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.exammodule.dto;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(catalog = "ExamModuleDB", schema = "", name="questions")
@XmlRootElement
public class QuestionsDTO implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "question_order", nullable = false)
    private Integer questionOrder;
    
    @Id
    @Basic(optional = false)
    @Column(name = "question_id", nullable = false)
    private Integer questionId;
    
    @Id
    @Basic(optional = false)
    @Column(name = "question_type", nullable = false)
    private String questionType;
    
    @Basic(optional = false)
    @Column(name = "statement", length = 10, nullable = false)
    private String statement;
    
    @Basic(optional = false)
    @Column(name = "option1",nullable = false)
    private String option1;
    
    @Basic(optional = false)
    @Column(name = "option2",nullable = false)
    private String option2;
    
    @Basic(optional = false)
    @Column(name = "option3",nullable = false)
    private String option3;
    
    @Basic(optional = false)
    @Column(name = "option4",nullable = false)
    private String option4;
    
    @Basic(optional = false)
    @Column(name = "option5",nullable = false)
    private String option5;
    
    @Basic(optional = false)
    @Column(name = "answer",nullable = false)
    private String answer;
    
    @Column(name = "question_image", nullable = false)
    private String questionImage;
    
    @Column(name = "feedback_video_link", nullable = true)
    private String videoLink;
    
    @Column(name = "feedback_image", nullable = false)
    private String feedbackImage;

    
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

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getOption5() {
		return option5;
	}

	public void setOption5(String option5) {
		this.option5 = option5;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestionImage() {
		return questionImage;
	}

	public void setQuestionImage(String questionImage) {
		this.questionImage = questionImage;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public String getFeedbackImage() {
		return feedbackImage;
	}

	public void setFeedbackImage(String feedbackImage) {
		this.feedbackImage = feedbackImage;
	}
    
        
}
