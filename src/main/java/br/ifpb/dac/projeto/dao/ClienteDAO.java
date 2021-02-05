package br.ifpb.dac.projeto.dao;

import java.util.List;

import br.ifpb.dac.projeto.entidades.Cliente;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.ClienteFiltro;

public interface ClienteDAO {
	
	public void save(Cliente client) throws DACExceptions;
	
	public Cliente update(Cliente client) throws DACExceptions;
	
	public void updateEmail(String oldEmail) throws DACExceptions;
	
	public void delete(Cliente client) throws DACExceptions;
	
	public Cliente getClientePeloID(int id) throws DACExceptions;
	
	public Cliente getByCPF(String cpf) throws DACExceptions;
	
	public List<Cliente> getAllClients() throws DACExceptions;
	
	public List<Cliente> findBy(ClienteFiltro clientFilter) throws DACExceptions;
	
}
