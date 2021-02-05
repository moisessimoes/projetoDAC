package br.ifpb.dac.projeto.dao;

import java.util.List;

import br.ifpb.dac.projeto.entidades.Agencia;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.AgenciaFiltro;

public interface AgenciaDAO {
	
	public void save(Agencia agency) throws DACExceptions;
	
	public Agencia update(Agencia agency) throws DACExceptions;
	
	public void delete(Agencia agency) throws DACExceptions;
	
	public Agencia getAgencyByID(int id) throws DACExceptions;
	
	public Agencia getAgencyByName(String name) throws DACExceptions;
	
	public List<Agencia> getAllAgencies() throws DACExceptions;
	
	public List<Agencia> findBy(AgenciaFiltro agencyFilter) throws DACExceptions;

}
