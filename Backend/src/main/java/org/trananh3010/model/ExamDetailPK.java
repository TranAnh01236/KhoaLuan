package org.trananh3010.model;

import java.io.Serializable;

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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exam == null) ? 0 : exam.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		return result;
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
		if (exam == null) {
			if (other.exam != null)
				return false;
		} else if (!exam.equals(other.exam))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}	
	
	
	
}
