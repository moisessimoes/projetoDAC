package br.ifppb.dac.projeto.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.ifpb.dac.projeto.dao.AgenciaDAO;
import br.ifpb.dac.projeto.entidades.Agencia;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.AgenciaFiltro;

public class AgenciaInDataBase extends DAOJPA implements Serializable, AgenciaDAO {

	private static final long serialVersionUID = 1L;
	

	@Override
	public void save(Agencia agency) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			
			manager.persist(agency);
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar salvar a agencia.", pe);
		} 
	}

	@Override
	public Agencia update(Agencia agency) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		Agencia result = agency;
		try {
			
			result = manager.merge(agency);
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();	
			throw new DACExceptions("Ocorreu algum erro ao tentar atualizar a agencia.", pe);
		} 
		return result;
	}

	@Override
	public void delete(Agencia agency) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			agency = manager.find(Agencia.class, agency.getId());
				
			manager.remove(agency);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar deletar a agencia.", e);
		} 
		
	}
	
	
	@Override
	public Agencia getAgencyByID(int id) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			Agencia agencia = manager.find(Agencia.class, id);
			
			if(agencia != null) {
				return agencia;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar recuperar a agencia com base no ID.", e);
		} 
		return null;
	}
	
	
	@Override
	public Agencia getAgencyByName(String name) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			Agencia agencia = manager.find(Agencia.class, name);
			
			if(agencia != null) {
				return agencia;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar recuperar a agencia com base no nome.", e);
		} 
		return null;
	}
	
	

	@Override
	public List<Agencia> getAllAgencies() throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			String consulta = "select a from Agencia a";
			TypedQuery<Agencia> query = manager.createQuery(consulta, Agencia.class);
			
			List<Agencia> todasAsAgencias = query.getResultList();
			
			if(todasAsAgencias.size() != 0) {
				return todasAsAgencias;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		} 	
		return null;	
	}

	@Override
	public List<Agencia> findBy(AgenciaFiltro agencyFilter) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		List<Agencia> result = new ArrayList<>();
		
		try {
			String jpql = "SELECT a FROM Agencia a WHERE 1 = 1 ";
			
			//nome
			if(notEmpty(agencyFilter.getNome())) {
				
				jpql += "AND a.nome LIKE :nome ";
			}
			
			//telefone
			if(notEmpty(agencyFilter.getTelefone())) {
				
				jpql += "AND a.telefone LIKE :telefone ";
			}
			
			//cidade
			if(notEmpty(agencyFilter.getEndereco().getCidade())) {
				
				jpql += "AND a.endereco.cidade LIKE :cidade ";
			}
			
			TypedQuery<Agencia> query = manager.createQuery(jpql, Agencia.class);
			
			//Nome
			if(notEmpty(agencyFilter.getNome())) {
				query.setParameter("nome", "%" + agencyFilter.getNome() + "%");
			}
			
			//CPF
			if(notEmpty(agencyFilter.getTelefone())) {
				query.setParameter("telefone", "%" + agencyFilter.getTelefone() + "%");
			}
			
			//Sexo
			if(notEmpty(agencyFilter.getEndereco().getCidade())) {
				query.setParameter("cidade", "%" + agencyFilter.getEndereco().getCidade() + "%");
			}
			
			result = query.getResultList();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
		return result;
	}
}
