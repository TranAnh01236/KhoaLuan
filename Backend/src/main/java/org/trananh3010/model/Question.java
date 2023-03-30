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
@Table(name = "questions")
public class Question implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4579899631061980399L;
	
	@Id
	@GeneratedValue(generator = "question_id_generator")
	@GenericGenerator(name = "question_id_generator", strategy = "org.trananh3010.ultilities.idGenerator.QuestionIdGenerator")
	@Column(name = "id")
	private String id;
	
	@Column(name="content", columnDefinition = "nvarchar(max)")
	private String content;
	
	@Column(name="type", columnDefinition = "int")
	private int type;
	
	@OneToMany(mappedBy = "question")
	private List<Answer> answers;
	
	@Column(name="explain", columnDefinition = "nvarchar(max)")
	private String explain;
	
	@Column(name="image", columnDefinition = "nchar(255)")
	private String image;
	
	@Column(name="audio", columnDefinition = "nchar(255)")
	private String audio;
	
	@ManyToOne
	@JoinColumn(name = "lessonId")
	private Lesson lesson;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToMany(mappedBy = "question")
	private List<ExamDetail> examDetails;
	
	public List<ExamDetail> getExamDetails(){
		return this.examDetails;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public List<Answer> getAnswers() {
		return answers;
	}
	
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public void addAnswer(Answer answer) {
		answers.add(answer);
	}

	public Question(String id, String content, int type, String explain, String image, String audio,
			Lesson lesson, User user) {
		super();
		this.id = id;
		this.content = content;
		this.type = type;
		this.explain = explain;
		this.image = image;
		this.audio = audio;
		this.lesson = lesson;
		this.answers = new ArrayList<>();
		this.user = user;
		this.examDetails = new ArrayList<>();
	}

	public Question(String id) {
		super();
		this.id = id;
		this.answers = new ArrayList<>();
		this.examDetails = new ArrayList<>();
	}
	
	public Question() {
		this.answers = new ArrayList<>();
		this.examDetails = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", content=" + content + ", type=" + type + ", description=" + explain
				+ ", image=" + image + ", audio=" + audio + ", lesson=" + lesson + ", user=" + user + "]";
	}
	
}
