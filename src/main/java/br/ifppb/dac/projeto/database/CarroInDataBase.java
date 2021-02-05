package br.ifppb.dac.projeto.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.ifpb.dac.projeto.dao.CarroDAO;
import br.ifpb.dac.projeto.entidades.Carro;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.CarroFiltro;

public class CarroInDataBase extends DAOJPA implements Serializable, CarroDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public void save(Carro car) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			
			manager.persist(car);
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar salvar o carro.", pe);
		} 
	}

	@Override
	public Carro update(Carro car) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		Carro result = car;
		try {
			
			result = manager.merge(car);
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();	
			throw new DACExceptions("Ocorreu algum erro ao tentar atualizar o carro.", pe);
		} 
		return result;
	}

	@Override
	public void delete(Carro car) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			car = manager.find(Carro.class, car.getId());
				
			manager.remove(car);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar deletar o carro.", e);
		} 
	}

	@Override
	public Carro getCarroPeloID(int id) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			Carro car = manager.find(Carro.class, id);
			
			if(car != null) {
				return car;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar recuperar o carro com base no ID.", e);
		} 
		return null;
	}

	@Override
	public Carro getByPlaca(String placa) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			Carro car = manager.find(Carro.class, placa);
			
			if(car != null) {
				return car;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions("Ocorreu algum erro ao tentar recuperar o carro com base na placa.", e);
		} 
		return null;
	}

	@Override
	public List<Carro> getAllCars() throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		try {
			String consulta = "select c from Carro c";
			TypedQuery<Carro> query = manager.createQuery(consulta, Carro.class);
			
			List<Carro> todosOsCarros = query.getResultList();
			
			if(todosOsCarros.size() != 0) {
				return todosOsCarros;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		} 	
		return null;	
	}

	@Override
	public List<Carro> findBy(CarroFiltro carFilter) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		List<Carro> result = new ArrayList<>();
		
		try {
			String jpql = "SELECT c FROM Carro c WHERE 1 = 1 ";
			
			//nome
			if(notEmpty(carFilter.getNome())) {
				
				jpql += "AND c.nomeCompleto LIKE :nome ";
			}
			
			//categoria
			if(notEmpty(carFilter.getCategoria())) {
				
				jpql += "AND c.categoria LIKE :categoria ";
			}
			
			//marca
			if(notEmpty(carFilter.getMarca())) {
				
				jpql += "AND c.marca LIKE :marca ";
			}
			
			//placa
			if(notEmpty(carFilter.getPlaca())) {
				
				jpql += "AND c.placa LIKE :placa ";
			}
			
			TypedQuery<Carro> query = manager.createQuery(jpql, Carro.class);
			
			//Nome
			if(notEmpty(carFilter.getNome())) {
				query.setParameter("nomeCompleto", "%" + carFilter.getNome() + "%");
			}
			
			//Categoria
			if(notEmpty(carFilter.getCategoria())) {
				query.setParameter("categoria", "%" + carFilter.getCategoria() + "%");
			}
			
			//Marca
			if(notEmpty(carFilter.getMarca())) {
				query.setParameter("marca", "%" + carFilter.getMarca() + "%");
			}
			
			//Placa
			if(notEmpty(carFilter.getPlaca())) {
				query.setParameter("placa", "%" + carFilter.getPlaca() + "%");
			}
			
			result = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
		
		return result;
	}

	@Override
	public List<Carro> getCarsByBrand(String brand) throws DACExceptions {
		
		EntityManager manager = getEntityManager();
		
		List<Carro> result = new ArrayList<>();
		
		try {
			String jpql = "SELECT c from Carro c WHERE c.marca = " + brand +";";
			
			TypedQuery<Carro> query = manager.createQuery(jpql, Carro.class);
			
			result = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
		return result;
	}
}
