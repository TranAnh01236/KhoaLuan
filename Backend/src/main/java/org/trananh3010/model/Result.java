package org.trananh3010.model;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "results")
@IdClass(ResultPK.class)
public class Result implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3767677256929576403L;

	@Id
	@ManyToOne
	@JoinColumn(name="examId")
	private Exam exam;
	
	@Id
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@Column(name="start_time")
	private Date startTime;
	
	@Column(name="end_time")
	private Date endTime;
	
	@Column(name="duration")
	private double duration;
	
	@Column(name="score")
	private int score;

	public Result(Exam exam, User user, Date startTime, Date endTime, double duration, int score) {
		super();
		this.exam = exam;
		this.user = user;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.score = score;
	}

	public Result() {
		super();
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Result [exam=" + exam + ", user=" + user + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", duration=" + duration + ", score=" + score + "]";
	}
	
}
