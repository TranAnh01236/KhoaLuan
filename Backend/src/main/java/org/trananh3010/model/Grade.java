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
@Table(name = "grades")
public class Grade implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3988817700937713740L;
	
	@Id
	@GeneratedValue(generator = "grade_id_generator")
	@GenericGenerator(name = "grade_id_generator", strategy = "org.trananh3010.ultilities.idGenerator.GradeIdGenerator")
	@Column(name = "id")
	private String id;
	
	@Column(name="name", columnDefinition = "nvarchar(255)")
	private String name;
	
	@Column(name="description", columnDefinition = "nvarchar(max)")
	private String description;
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(mappedBy = "grade")
	private List<Subject> subjects;

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

	public Grade(String id, String name, String desciption) {
		super();
		this.id = id;
		this.name = name;
		this.subjects = new ArrayList<>();
		this.description = desciption;
	}

	public Grade(String id) {
		super();
		this.id = id;
		this.subjects = new ArrayList<>();
	}

	public Grade() {
		super();
		this.subjects = new ArrayList<>();
	}

	@Override
	public String toString() {
		return name;
	}

//	@Override
//	public String toString() {
//		return "Grade [id=" + id + ", name=" + name + ", description=" + description + "]";
//	}
}
