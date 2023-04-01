package org.trananh3010.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "exams")
public class Exam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9026877259329825074L;

	@Id
	@GeneratedValue(generator = "exam_id_generator")
	@GenericGenerator(name = "exam_id_generator", strategy = "org.trananh3010.ultilities.idGenerator.ExamIdGenerator")
	private String id;

	@Column(name = "name", columnDefinition = "nvarchar(255)")
	private String name;

	@Column(name = "description", columnDefinition = "nvarchar(max)")
	private String description;

	private int type;

	private double duration;
	
	private String image;

	@CreatedDate
    @Column(name = "created_at", updatable = false)
    private Date createdAt = new Date(System.currentTimeMillis());

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt = new Date(System.currentTimeMillis());

	@OneToMany(mappedBy = "exam")
	private List<ExamDetail> examDetails;

	@OneToMany(mappedBy = "exam")
	private List<Result> results;

	@OneToMany(mappedBy = "exam")
	private List<Comment> comments;

	public Exam() {
		super();
		this.examDetails = new ArrayList<>();
		this.results = new ArrayList<>();
		this.comments = new ArrayList<>();
	}

	public Exam(String id) {
		super();
		this.id = id;
		this.examDetails = new ArrayList<>();
		this.results = new ArrayList<>();
		this.comments = new ArrayList<>();
	}

	public Exam(String id, String name, String description, int type, double duration, String image) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.duration = duration;
		this.image = image;
		this.examDetails = new ArrayList<>();
		this.results = new ArrayList<>();
		this.comments = new ArrayList<>();
	}

	public Exam(String id, String name, String description, int type, double duration, String image, List<ExamDetail> examDetails) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.duration = duration;
		this.image = image;
		this.examDetails = examDetails;
		this.results = new ArrayList<>();
		this.comments = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public void addExamDetail(ExamDetail examDetail) {
		this.examDetails.add(examDetail);
	}
	
	public void addResult(Result result) {
		this.results.add(result);
	}

	public void setExamDetails(List<ExamDetail> examDetails) {
		this.examDetails = examDetails;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Exam [id=" + id + ", name=" + name + ", description=" + description + ", type=" + type + ", duration="
				+ duration + ", image=" + image + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
