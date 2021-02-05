package br.ifppb.dac.projeto.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.ifpb.dac.projeto.dao.AluguelCarroDAO;
import br.ifpb.dac.projeto.entidades.Reserva;
import br.ifpb.dac.projeto.entidades.Carro;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.AluguelCarroFiltro;

public class AluguelCarroInDataBase extends DAOJPA implements Serializable, AluguelCarroDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public void save(Reserva aluguel) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			
			manager.persist(aluguel);
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar salvar a reserva.", pe);
		} 
	}

	
	@Override
	public Reserva update(Reserva aluguel) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		Reserva rent = aluguel;
		
		try {
			
			rent = manager.merge(aluguel);
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();	
			throw new DACExceptions("Ocorreu algum erro ao tentar atualizar a reserva.", pe);
		} 
		return rent;
	}

	
	@Override
	public void delete(Reserva aluguel) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			
			aluguel = manager.find(Reserva.class, aluguel.getId());
				
			manager.remove(aluguel);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar deletar a reserva.", e);
		} 
		
	}

	@Override
	public Reserva getRentByID(int id) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			Reserva rent = manager.find(Reserva.class, id);
			
			if(rent != null) {
				return rent;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar recuperar a reserva com base no ID.", e);
		} 
		return null;
	}
	
	
	@Override
	public Reserva getRentByCode(int codigo) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			
			if(validateSearchCode(String.valueOf(codigo))) {
				
				String consulta = "SELECT r FROM AluguelDoCarro r WHERE r.codigoDaReserva = :code";
				
				TypedQuery<Reserva> query = manager.createQuery(consulta, Reserva.class);
				
				query.setParameter("code", codigo);
				
				Reserva reserva = query.getSingleResult();
				
				if(reserva != null) {
					return reserva;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Não foi encontrado nenhuma reserva com este código. Verifique se o código está correto e tente novamente!", e);
		} 
		return null;
	}

	
	@Override
	public List<Reserva> getAllRents() throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			String consulta = "select a from AluguelDoCarro a";
			TypedQuery<Reserva> query = manager.createQuery(consulta, Reserva.class);
			
			List<Reserva> todasAsReservas = query.getResultList();
			
			if(!todasAsReservas.isEmpty()) {
				return todasAsReservas;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar recuperar as reservas.", e);
		} 	
		return null;	
	}

	
	@Override
	public List<Reserva> findBy(AluguelCarroFiltro aluguelFiltro) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		List<Reserva> result = new ArrayList<Reserva>();
		
		try {
			
			String jpql = "SELECT a FROM AluguelDoCarro a WHERE 1 = 1 ";
			
			// data retirada
			if (notEmpty(aluguelFiltro.getDataRetirada())) {
				jpql += "AND a.retirada >= :dataRetirada ";
			}

			// data devolucao
			if (notEmpty(aluguelFiltro.getDataDevolucao())) {
				jpql += "AND a.devolucao <= :dataDevolucao ";
			}
			
			TypedQuery<Reserva> query = manager.createQuery(jpql, Reserva.class);
			
			// data retirada
			if (notEmpty(aluguelFiltro.getDataRetirada())) {
				query.setParameter("dataRetirada", aluguelFiltro.getDataRetirada());
			}

			// data retirada
			if (notEmpty(aluguelFiltro.getDataDevolucao())) {
				query.setParameter("dataDevolucao", aluguelFiltro.getDataDevolucao());
			}
			
			result = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar recuperar a reserva desejada.", e);
		}
		return result;
	}


	@Override
	public boolean validateSearchCode(String codigo) {
		
		for (int i = 0; i < codigo.length(); i++) {
			
			if(!Character.isDigit(codigo.charAt(i))) {
				
				return false;
			}
		}
		return true;
	}


	@Override
	public Reserva isCarRented(Carro carro) throws DACExceptions {
		
		List<Reserva> alugueis = getAllRents();
		
		for (Reserva aluguel : alugueis) {
			
			if(aluguel.getCarro().getId() == carro.getId()) {
				
				return aluguel;
			}
		}
		return null;
	}
}
