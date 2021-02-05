package br.ifppb.dac.projeto.database;

import java.io.Serializable;
import java.util.Date;

public abstract class DAOUtils implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected boolean assertDate(Date date, Date dateLimit, boolean atLeast) {
		if (date == null) {
			return true;
		}
		if (atLeast) {
			return date.compareTo(dateLimit) >= 0;
		} else {
			// atMost
			return date.compareTo(dateLimit) <= 0;
		}
	}

	
	protected boolean contains(String s1, String s2) {
		if (s1 == null && s2 == null) {
			return true;
		}
		if (s1 == null || s2 == null) {
			return false;
		}

		return s1.toUpperCase().contains(s2.toUpperCase());
	}

	
	protected boolean notEmpty(Object obj) {
		return obj != null;
	}

	
	protected boolean notEmpty(String obj) {
		return obj != null && !obj.trim().isEmpty();
	}
}
