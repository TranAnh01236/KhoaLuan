package org.trananh3010.model;

import java.io.Serializable;
import java.sql.Date;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "comments")
public class Comment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -845794798931058912L;

	@Id
	@GeneratedValue(generator = "comment_id_generator")
	@GenericGenerator(name = "comment_id_generator", strategy = "org.trananh3010.ultilities.idGenerator.CommentIdGenerator")
	@Column(name = "id")
	private String id;
	
	@Column(name = "content", nullable = false, columnDefinition = "nvarchar(max)")
	private String content;
	
	private Date createAt = new Date(System.currentTimeMillis());

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "examId")
	private Exam exam;
	
	public Comment() {
		super();
	}

	public Comment(String id) {
		super();
		this.id = id;
	}

	public Comment(String id, String content, Date createAt, User user, Exam exam) {
		super();
		this.id = id;
		this.content = content;
		this.createAt = createAt;
		this.user = user;
		this.exam = exam;
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

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", createAt=" + createAt + ", user=" + user + ", exam="
				+ exam + "]";
	}
	
}
