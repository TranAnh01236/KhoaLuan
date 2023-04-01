package org.trananh3010.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4528118482687375718L;

	@Id
	@GeneratedValue(generator = "user_id_generator")
	@GenericGenerator(name = "user_id_generator", strategy = "org.trananh3010.ultilities.idGenerator.UserIdGenerator")
	@Column(name = "id")
	private String id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
	
	@Embedded
	private Account account;
	
	@Column(name = "avatar", nullable = true)
	private String avatar;
	
	@OneToMany(mappedBy = "user")
	private List<Question> questions;
	
	@OneToMany(mappedBy = "user")
	private List<Result> results;
	
	@OneToMany(mappedBy = "user")
	private List<Comment> comments;

	public User(String id, String firstName, String lastName, String email, String phoneNumber, Account account,
			String avatar) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.account = account;
		this.avatar = avatar;
		this.questions = new ArrayList<>();
		this.results = new ArrayList<>();
		this.comments = new ArrayList<>();
	}

	public User(String id) {
		super();
		this.id = id;
		this.questions = new ArrayList<>();
		this.results = new ArrayList<>();
		this.comments = new ArrayList<>();
	}

	public User() {
		super();
		this.questions = new ArrayList<>();
		this.results = new ArrayList<>();
		this.comments = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", account=" + account + ", avatar=" + avatar + "]";
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
