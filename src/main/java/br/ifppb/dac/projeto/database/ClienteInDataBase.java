package br.ifppb.dac.projeto.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.ifpb.dac.projeto.dao.ClienteDAO;
import br.ifpb.dac.projeto.entidades.Cliente;
import br.ifpb.dac.projeto.entidades.ContaNoSistema;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.ClienteFiltro;

public class ClienteInDataBase extends DAOJPA implements Serializable, ClienteDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public void save(Cliente client) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			
			manager.persist(client);
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar salvar o cliente.", pe);
		} 
	}

	@Override
	public Cliente update(Cliente client) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		Cliente result = client;
		try {
			
			result = manager.merge(client);
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();	
			throw new DACExceptions("Ocorreu algum erro ao tentar atualizar o cliente.", pe);
		} 
		return result;
	}

	@Override
	public void delete(Cliente client) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			client = manager.find(Cliente.class, client.getId());
				
			manager.remove(client);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar deletar o cliente.", e);
		} 
	}

	@Override
	public Cliente getClientePeloID(int id) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			Cliente client = manager.find(Cliente.class, id);
			
			if(client != null) {
				return client;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar recuperar o cliente com base no ID.", e);
		} 
		return null;
	}

	@Override
	public Cliente getByCPF(String cpf) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			Cliente client = manager.find(Cliente.class, cpf);
			
			if(client != null) {		
				return client;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar recuperar o cliente com base no CPF.", e);
		} 	
		return null;
	}

	@Override
	public List<Cliente> getAllClients() throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			String consulta = "select c from Cliente c";
			TypedQuery<Cliente> query = manager.createQuery(consulta, Cliente.class);
			
			List<Cliente> todosOsClientes = query.getResultList();
			
			if(todosOsClientes.size() != 0) {
				return todosOsClientes;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		} 	
		return null;	
	}

	@Override
	public List<Cliente> findBy(ClienteFiltro clientFilter) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		List<Cliente> result = new ArrayList<>();
		
		try {
			String jpql = "SELECT c FROM Cliente c WHERE 1 = 1 ";
			
			//nome
			if(notEmpty(clientFilter.getNome())) {
				
				jpql += "AND c.nomeCompleto LIKE :nome ";
			}
			
			//cpf
			if(notEmpty(clientFilter.getCpf())) {
				
				jpql += "AND c.cpf LIKE :cpf ";
			}
			
			//sexo
			if(notEmpty(clientFilter.getSexo())) {
				
				jpql += "AND c.sexo LIKE :sexo ";
			}
			
			TypedQuery<Cliente> query = manager.createQuery(jpql, Cliente.class);
			
			//Nome
			if(notEmpty(clientFilter.getNome())) {
				query.setParameter("nomeCompleto", "%" + clientFilter.getNome() + "%");
			}
			
			//CPF
			if(notEmpty(clientFilter.getCpf())) {
				query.setParameter("cpf", "%" + clientFilter.getCpf() + "%");
			}
			
			//Sexo
			if(notEmpty(clientFilter.getSexo())) {
				query.setParameter("sexo", "%" + clientFilter.getSexo() + "%");
			}
			
			result = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
		
		return result;
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
