package br.ifpb.dac.projeto.clienteServices;

import java.util.List;

import br.ifpb.dac.projeto.entidades.Cliente;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.ClienteFiltro;

public interface ClienteService {
	
	public void salvarCliente(Cliente cliente) throws DACExceptions;
	
	public Cliente atualizarCliente(Cliente client, boolean passwordChanged) throws DACExceptions;
	
	public void atualizarEmail(String emailAntigo) throws DACExceptions;
	
	public void deletarCliente(Cliente client) throws DACExceptions;
	
	public Cliente getClientePeloID(int id) throws DACExceptions;
	
	public Cliente getClientePeloCPF(String cpf) throws DACExceptions;
	
	public List<Cliente> getTodosClientes() throws DACExceptions;
	
	public List<Cliente> encontrarPor(ClienteFiltro clientFilter) throws DACExceptions;
	
	public boolean conferirSenha(Cliente cliente, String supostaSenha) throws DACExceptions;
	
	public boolean conferirEmail(Cliente cliente, String supostoEmail) throws DACExceptions;

}
