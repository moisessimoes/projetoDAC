package br.ifpb.dac.projeto.dao;

import java.util.List;

import br.ifpb.dac.projeto.entidades.Reserva;
import br.ifpb.dac.projeto.entidades.Carro;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.AluguelCarroFiltro;

public interface AluguelCarroDAO {
	
	public void save(Reserva aluguel) throws DACExceptions;
	
	public Reserva update(Reserva aluguel) throws DACExceptions;
	
	public void delete(Reserva aluguel) throws DACExceptions;
	
	public Reserva getRentByID(int id) throws DACExceptions;
	
	public Reserva getRentByCode(int codigo) throws DACExceptions;
	
	public List<Reserva> getAllRents() throws DACExceptions;
	
	public List<Reserva> findBy(AluguelCarroFiltro aluguelFiltro) throws DACExceptions;
	
	public boolean validateSearchCode(String codigo);
	
	public Reserva isCarRented(Carro carro) throws DACExceptions;
	
}
