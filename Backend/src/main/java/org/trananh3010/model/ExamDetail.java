package org.trananh3010.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "exam_details")
@IdClass(ExamDetailPK.class)
public class ExamDetail {
	
	@Id
	@ManyToOne
	@JoinColumn(name="questionId")
	private Question question;
	
	@Id
	@ManyToOne
	@JoinColumn(name="examId")
	private Exam exam;
	
	private double score;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "ExamDetail [question=" + question + ", exam=" + exam + ", score=" + score + "]";
	}

	public ExamDetail(Question question, Exam exam, double score) {
		super();
		this.question = question;
		this.exam = exam;
		this.score = score;
	}

	public ExamDetail() {
		super();
	}

	public ExamDetail(Question question, double score) {
		super();
		this.question = question;
		this.score = score;
	}
	
}
