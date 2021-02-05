package br.ifppb.dac.projeto.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.ifpb.dac.projeto.dao.AdministradorDAO;
import br.ifpb.dac.projeto.entidades.Administrador;
import br.ifpb.dac.projeto.entidades.ContaNoSistema;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.AdministradorFiltro;

public class AdministradorInDataBase extends DAOJPA implements Serializable, AdministradorDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public void save(Administrador admin) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			
			manager.persist(admin);
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar salvar o administrador.", pe);
		} 
	}

	@Override
	public Administrador update(Administrador admin) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		Administrador result = admin;
		try {
			
			result = manager.merge(admin);
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();	
			throw new DACExceptions("Ocorreu algum erro ao tentar atualizar o administrador.", pe);
		} 
		return result;
	}

	@Override
	public void delete(Administrador admin) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			admin = manager.find(Administrador.class, admin.getId());
				
			manager.remove(admin);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar deletar o administrador.", e);
		} 
	}

	@Override
	public Administrador getAdminPeloID(int id) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			Administrador admin = manager.find(Administrador.class, id);
			
			if(admin != null) {
				return admin;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar recuperar o administrador com base no ID.", e);
		} 
		return null;
	}

	@Override
	public Administrador getByCPF(String cpf) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			Administrador admin = manager.find(Administrador.class, cpf);
			
			if(admin != null) {
				return admin;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar recuperar o administrador com base no CPF.", e);
		} 
		return null;
	}

	@Override
	public List<Administrador> getAllAdmins() throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			String consulta = "select a from Administrador a";
			TypedQuery<Administrador> query = manager.createQuery(consulta, Administrador.class);
			
			List<Administrador> todosOsAdmins = query.getResultList();
			
			if(todosOsAdmins.size() != 0) {
				return todosOsAdmins;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		} 	
		return null;	
	}

	@Override
	public List<Administrador> findBy(AdministradorFiltro adminFilter) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		List<Administrador> result = new ArrayList<>();
		
		try {
			String jpql = "SELECT a FROM Administrador a WHERE 1 = 1 ";
			
			//nome
			if(notEmpty(adminFilter.getNome())) {
				
				jpql += "AND a.nomeCompleto LIKE :nome ";
			}
			
			//cpf
			if(notEmpty(adminFilter.getCpf())) {
				
				jpql += "AND a.cpf LIKE :cpf ";
			}
			
			//sexo
			if(notEmpty(adminFilter.getSexo())) {
				
				jpql += "AND a.sexo LIKE :sexo ";
			}
			
			TypedQuery<Administrador> query = manager.createQuery(jpql, Administrador.class);
			
			//Nome
			if(notEmpty(adminFilter.getNome())) {
				query.setParameter("nomeCompleto", "%" + adminFilter.getNome() + "%");
			}
			
			//CPF
			if(notEmpty(adminFilter.getCpf())) {
				query.setParameter("cpf", "%" + adminFilter.getCpf() + "%");
			}
			
			//Sexo
			if(notEmpty(adminFilter.getSexo())) {
				query.setParameter("sexo", "%" + adminFilter.getSexo() + "%");
			}
			
			result = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
		
		return result;
	}

	@Override
	public boolean validarCPF(String cpf) throws DACExceptions {
		
		CPFValidator cpfValidator = new CPFValidator();
		
		List<ValidationMessage> erros = cpfValidator.invalidMessagesFor(cpf);
		
		if(erros.size() > 0)
			return false;
		
		else
			return true;
	}

	@Override
	public void updateEmail(String oldEmail) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			
			ContaNoSistema conta = manager.find(ContaNoSistema.class, oldEmail);
			
			manager.remove(conta);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
		
	}
}
