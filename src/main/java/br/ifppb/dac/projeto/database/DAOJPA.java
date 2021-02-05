package br.ifppb.dac.projeto.database;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class DAOJPA extends DAOUtils implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
