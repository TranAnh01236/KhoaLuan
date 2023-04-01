package org.trananh3010.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Account {
	
	@Column(name = "account", nullable = false, columnDefinition = "nchar(50)")
	private String account;
	
	@Column(name = "password", nullable = false, columnDefinition = "nchar(50)")
	private String password;
	
	private int type = 0;

	public Account(String account, String password, int type) {
		super();
		this.account = account;
		this.password = password;
		this.type = type;
	}

	public Account() {
		super();
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Account [account=" + account + ", password=" + password + ", type=" + type + "]";
	}

}
