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
@Table(name = "lessons")
public class Lesson implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3365995336096805003L;

	@Id
	@GeneratedValue(generator = "lesson_id_generator")
	@GenericGenerator(name = "lesson_id_generator", strategy = "org.trananh3010.ultilities.idGenerator.LessonIdGenerator")
	@Column(name = "id")
	private String id;
	
	@Column(name="name", columnDefinition = "nvarchar(255)")
	private String name;
	
	@Column(name="description", columnDefinition = "nvarchar(max)")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "chapterId")
	private Chapter chapter;
	
	@OneToMany(mappedBy = "lesson")
	private List<Question> questions;

	
	
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

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public Lesson(String id, String name, String description, Chapter chapter) {
		super();
		this.id = id;
		this.name = name;
		this.chapter = chapter;
		this.description = description;
		this.questions = new ArrayList<>();
	}

	public Lesson(String id) {
		super();
		this.id = id;
		this.questions = new ArrayList<>();
	}

	public Lesson() {
		super();
		this.questions = new ArrayList<>();
	}

//	@Override
//	public String toString() {
//		return "Lesson [id=" + id + ", name=" + name + ", description=" + description + ", chapter=" + chapter + "]";
//	}
	
	@Override
	public String toString() {
		return name;
	}

}
