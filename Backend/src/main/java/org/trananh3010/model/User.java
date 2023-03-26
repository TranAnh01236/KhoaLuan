package org.trananh3010.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	@Column(nullable = false)
	private String account;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;

	@OneToMany(mappedBy = "user")
	private List<Question> questions;

	public User() {
		this.questions = new ArrayList<>();
	}

	public User(String id) {
		this.id = id;
		this.questions = new ArrayList<>();
	}

	public User(String id, String firstName, String lastName, String account, String email, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.account = account;
		this.email = email;
		this.password = password;
		this.questions = new ArrayList<>();
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmal() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", account=" + account
				+ ", phoneNumber=" + email + ", password=" + password + "]";
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
}
