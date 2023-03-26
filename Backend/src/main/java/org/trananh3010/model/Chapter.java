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
@Table(name = "chapters")
public class Chapter implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2539560358968621707L;

	@Id
	@GeneratedValue(generator = "chapter_id_generator")
	@GenericGenerator(name = "chapter_id_generator", strategy = "org.trananh3010.ultilities.idGenerator.ChapterIdGenerator")
	@Column(name = "id")
	private String id;
	
	@Column(name="name", columnDefinition = "nvarchar(255)")
	private String name;
	
	@Column(name="description", columnDefinition = "nvarchar(max)")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "subjectId")
	private Subject subject;
	
	@OneToMany(mappedBy = "chapter")
	private List<Lesson> lessons;
	
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

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Chapter(String id, String name, String description, Subject subject) {
		super();
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.description = description;
		this.lessons = new ArrayList<>();
	}

	public Chapter(String id) {
		super();
		this.id = id;
		this.lessons = new ArrayList<>();
	}

	public Chapter() {
		super();
		this.lessons = new ArrayList<>();
	}

//	@Override
//	public String toString() {
//		return "Chapter [id=" + id + ", name=" + name + ", description=" + description + ", subject=" + subject + "]";
//	}
	
	@Override
	public String toString() {
		return name;
	}
}
