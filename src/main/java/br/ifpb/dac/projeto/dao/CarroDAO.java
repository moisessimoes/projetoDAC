package br.ifpb.dac.projeto.dao;

import java.util.List;

import br.ifpb.dac.projeto.entidades.Carro;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.CarroFiltro;

public interface CarroDAO {
	
	public void save(Carro car) throws DACExceptions;
	
	public Carro update(Carro car) throws DACExceptions;
	
	public void delete(Carro car) throws DACExceptions;
	
	public Carro getCarroPeloID(int id) throws DACExceptions;
	
	public Carro getByPlaca(String placa) throws DACExceptions;
	
	public List<Carro> getAllCars() throws DACExceptions;
	
	public List<Carro> getCarsByBrand(String brand) throws DACExceptions;
	
	public List<Carro> findBy(CarroFiltro carFilter) throws DACExceptions;
	
}
