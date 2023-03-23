package org.trananh3010.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ExamDetailPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -746247744425370847L;
	
	private String question;
	private String exam;
	
	public ExamDetailPK() {}

	@Override
	public int hashCode() {
		return Objects.hash(exam, question);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExamDetailPK other = (ExamDetailPK) obj;
		return Objects.equals(exam, other.exam) && Objects.equals(question, other.question);
	}

}
