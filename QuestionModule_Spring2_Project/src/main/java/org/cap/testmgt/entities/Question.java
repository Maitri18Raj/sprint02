package org.cap.testmgt.entities;

import java.math.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {
	
	@Id
	@GeneratedValue
	private BigInteger questionId;
	private BigInteger testId;
	private String questionTitle;
	private int questionAnswer;
	private BigDecimal questionMarks;
	private int chosenAnswer;
	private BigDecimal marksScored;
	private String[] questionOptions = new String[4];
	
	public BigInteger getTestId() {
		return testId;
	}
	public void setTestId(BigInteger testId) {
		this.testId = testId;
	}
	public BigInteger getQuestionId() {
		return questionId;
	}	
	public void setQuestionId(BigInteger questionId) {
		this.questionId = questionId;
	}	
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public int getQuestionAnswer() {
		return questionAnswer;
	}	
	public void setQuestionAnswer(int questionAnswer) {
		this.questionAnswer = questionAnswer;
	}	
	public BigDecimal getQuestionMarks() {
		return questionMarks;
	}	
	public void setQuestionMarks(BigDecimal questionMarks) {
		this.questionMarks = questionMarks;
	}	
	public int getChosenAnswer() {
		return chosenAnswer;
	}	
	public void setChosenAnswer(int chosenAnswer) {
		this.chosenAnswer = chosenAnswer;
	}	
	public BigDecimal getMarksScored() {
		return marksScored;
	}	
	public void setMarksScored(BigDecimal marksScored) {
		this.marksScored = marksScored;
	}	
	public String[] getQuestionOptions() {
		return questionOptions;
	}
	public void setQuestionOptions(String[] questionOptions) {
		this.questionOptions = questionOptions;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		return true;
	}
}
