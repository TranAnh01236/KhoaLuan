package org.trananh3010.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ResultPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7025642146560073356L;
	
	private String exam;
	private String user;
	
	public ResultPK() {
	}

	@Override
	public int hashCode() {
		return Objects.hash(exam, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultPK other = (ResultPK) obj;
		return Objects.equals(exam, other.exam) && Objects.equals(user, other.user);
	}
	
}
