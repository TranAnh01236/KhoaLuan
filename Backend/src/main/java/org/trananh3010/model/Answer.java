package org.trananh3010.model;

import java.io.Serializable;

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
@Table(name = "answers")
public class Answer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7850491875320085160L;
	
	@Id
	@GeneratedValue(generator = "answer_id_generator")
	@GenericGenerator(name = "answer_id_generator", strategy = "org.trananh3010.ultilities.idGenerator.AnswerIdGenerator")
	private String id;
	
	@Column(name="content", columnDefinition = "nvarchar(max)")
	private String content;
	
	private int loai;
	
	@Column(name="image", columnDefinition = "nchar(255)")
	private String image;
	
	@Column(name="audio", columnDefinition = "nchar(255)")
	private String audio;
	
	private int rightt;
	
	@ManyToOne
	@JoinColumn(name = "questionId")
	private Question question;
	
	public int getLoai() {
		return loai;
	}

	public void setLoai(int loai) {
		this.loai = loai;
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

	public int getRightt() {
		return rightt;
	}

	public void setRightt(int rightt) {
		this.rightt = rightt;
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

	public int gettype() {
		return loai;
	}

	public void settype(int type) {
		this.loai = type;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	

	public Answer(String id, String content, int type, String image, String audio, int rightt, Question question) {
		super();
		this.id = id;
		this.content = content;
		this.loai = type;
		this.image = image;
		this.audio = audio;
		this.rightt = rightt;
		this.question = question;
	}

	public Answer(String id) {
		super();
		this.id = id;
	}

	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", content=" + content + ", loai=" + loai + ", image=" + image + ", audio=" + audio
				+ ", rightt=" + rightt + ", question=" + question + "]";
	}
}
