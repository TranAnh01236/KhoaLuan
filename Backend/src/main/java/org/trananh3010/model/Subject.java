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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "subjects")
public class Subject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6238298072427836393L;

	@Id
	@GeneratedValue(generator = "subject_id_generator")
	@GenericGenerator(name = "subject_id_generator", strategy = "org.trananh3010.ultilities.idGenerator.SubjectIdGenerator")
	@Column(name = "id")
	private String id;
	
	@Column(name="name", columnDefinition = "nvarchar(255)")
	private String name;
	
	@Column(name="description", columnDefinition = "nvarchar(max)")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "gradeId")
	private Grade grade;
	
	@OneToMany(mappedBy = "subject")
	private List<Chapter> chapters;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Subject(String id, String name, String description, Grade grade) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.grade = grade;
	}

	public Subject(String id) {
		super();
		this.id = id;
		this.chapters = new ArrayList<>();
	}

	public Subject() {
		super();
		this.chapters = new ArrayList<>();
	}

	@Override
	public String toString() {
		return name;
	}

//	@Override
//	public String toString() {
//		return "Subject [id=" + id + ", name=" + name + ", grade=" + grade + "]";
//	}
	
	
	
}
