package br.ifpb.dac.projeto.dao;

import java.util.List;

import br.ifpb.dac.projeto.entidades.ContaNoSistema;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.ContasFiltro;

public interface ContasDAO {
	
	public List<ContaNoSistema> getAllAccounts() throws DACExceptions;
	
	public List<ContaNoSistema> getAllClientsAccounts() throws DACExceptions;
	
	public List<ContaNoSistema> getAllEmployeeAccounts() throws DACExceptions;
	
	public List<ContaNoSistema> findBy(ContasFiltro filter) throws DACExceptions;
	
	public ContaNoSistema getAnAccount(String email) throws DACExceptions;
	
	public boolean validarExistenciaDeEmail(String email) throws DACExceptions;

}
