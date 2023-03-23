package org.trananh3010.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Exams")
public class Exam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9026877259329825074L;

	@Id
	@GeneratedValue(generator = "exam_id_generator")
	@GenericGenerator(name = "exam_id_generator", strategy = "org.trananh3010.ultilities.idGenerator.ExamIdGenerator")
	private String id;
	
	@Column(name="name", columnDefinition = "nvarchar(255)")
	private String name;
	
	@Column(name="description", columnDefinition = "nvarchar(max)")
	private String description;
	
	private int type;
	
	private int duration;
	
	@OneToMany(mappedBy = "exam")
	private List<ExamDetail> examDetails;

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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Exam(String id, String name, String description, int type, int duration) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.duration = duration;
		this.examDetails = new ArrayList<>();
	}

	public Exam(String id) {
		super();
		this.id = id;
		this.examDetails = new ArrayList<>();
	}

	public Exam() {
		super();
		this.examDetails = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Exam [id=" + id + ", name=" + name + ", description=" + description + ", type=" + type + ", duration="
				+ duration + "]";
	}

}
