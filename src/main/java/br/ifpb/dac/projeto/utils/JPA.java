package br.ifpb.dac.projeto.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPA {
	
	private EntityManager em;

	@Produces
	@ApplicationScoped
	public EntityManagerFactory criarEMF() {
		
		EntityManagerFactory emf = null;
		
		try {
			emf = Persistence.createEntityManagerFactory("locadoradecarros");
		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}
		return emf;
	}

	@Produces
	@RequestScoped
	public EntityManager criarEM(EntityManagerFactory factory) {
		return factory.createEntityManager();
	}

	public void fecharEM(@Disposes EntityManager em) {
		this.em = em;
		em.close();
	}

	public void fecharEMF(@Disposes EntityManagerFactory emf) {
		emf.close();
	}
}
