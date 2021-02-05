package br.ifppb.dac.projeto.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ifpb.dac.projeto.dao.ContasDAO;
import br.ifpb.dac.projeto.entidades.ContaNoSistema;
import br.ifpb.dac.projeto.entidades.Grupos;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.ContasFiltro;

public class ContasInDataBase extends DAOJPA implements Serializable, ContasDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public List<ContaNoSistema> getAllAccounts() throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			String consulta = "SELECT c FROM tb_contas c";
			TypedQuery<ContaNoSistema> query = manager.createQuery(consulta, ContaNoSistema.class);
			
			List<ContaNoSistema> todasAsContas = query.getResultList();
			
			if(todasAsContas.size() != 0) {
				return todasAsContas;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Erro ao recuperar todas as contas.", e);
		} 	
		return null;
	}

	@Override
	public List<ContaNoSistema> getAllClientsAccounts() throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			String consulta = "SELECT c FROM tb_contas c WHERE c.grupo = " + Grupos.Cliente +";";
			TypedQuery<ContaNoSistema> query = manager.createQuery(consulta, ContaNoSistema.class);
			
			List<ContaNoSistema> todasAsContasDeClientes = query.getResultList();
			
			if(todasAsContasDeClientes.size() != 0) {
				return todasAsContasDeClientes;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Erro ao recuperar todas a contas dos clientes.", e);
		} 	
		return null;
	}

	@Override
	public List<ContaNoSistema> getAllEmployeeAccounts() throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			String consulta = "SELECT c FROM tb_contas c WHERE c.grupo = " + Grupos.Administrador + ";";
			TypedQuery<ContaNoSistema> query = manager.createQuery(consulta, ContaNoSistema.class);
			
			List<ContaNoSistema> todasAsContasDeFuncionarios = query.getResultList();
			
			if(todasAsContasDeFuncionarios.size() != 0) {
				return todasAsContasDeFuncionarios;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Erro ao recuperar todas a contas dos administradores.", e);
		} 	
		return null;
	}

	@Override
	public List<ContaNoSistema> findBy(ContasFiltro filter) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		List<ContaNoSistema> result = new ArrayList<ContaNoSistema>();
		
		try {
			String jpql = "SELECT c FROM ContaNoSistema c WHERE 1 = 1 "; //tb_contas
			
			//Grupo
			if(notEmpty(filter.getGrupo())) {
				
				jpql += "AND c.user_group LIKE :user_group ";
			}
			
			//Email
			if(notEmpty(filter.getEmail())) {
				
				jpql += "AND c.email LIKE :email ";
			}
			
			//Senha
			if(notEmpty(filter.getSenha())) {
				
				jpql += "AND c.senha LIKE :senha ";
			}
			
			
			TypedQuery<ContaNoSistema> query = manager.createQuery(jpql, ContaNoSistema.class);
			
			//Grupo
			if(notEmpty(filter.getGrupo())) {
				query.setParameter("user_group", "%" + filter.getGrupo() + "%");
			}
			
			//Email
			if(notEmpty(filter.getEmail())) {
				query.setParameter("email", "%" + filter.getEmail() + "%");
			}
			
			//Senha
			if(notEmpty(filter.getSenha())) {
				query.setParameter("senha", "%" + filter.getSenha() + "%");
			}
			
			result = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public ContaNoSistema getAnAccount(String email) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			String consulta = "SELECT c FROM tb_contas c WHERE c.email = " + email +";";
			TypedQuery<ContaNoSistema> query = manager.createQuery(consulta, ContaNoSistema.class);
			
			ContaNoSistema conta = query.getSingleResult();
			
			if(conta != null) {
				return conta;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Erro ao recuperar a conta desejada.", e);
		} 	
		return null;
	}

	@Override
	public boolean validarExistenciaDeEmail(String email) throws DACExceptions {
		
		if(email == null) return false;
		
		try {
			
			for (ContaNoSistema account : getAllAccounts()) {
				
				if(account.getEmail().equals(email)) {
					
					return true; //Se retornar true, e pq o cliente esta tentando se cadastrar com um email que ja esta exite no sistema.
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
