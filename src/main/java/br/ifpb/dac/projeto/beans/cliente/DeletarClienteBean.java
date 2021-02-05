package br.ifpb.dac.projeto.beans.cliente;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.dac.projeto.beans.AbstractBean;
import br.ifpb.dac.projeto.clienteServices.ClienteService;
import br.ifpb.dac.projeto.entidades.Cliente;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.ClienteFiltro;

@Named
@ViewScoped
public class DeletarClienteBean extends AbstractBean implements Serializable { //Classe bean responsavel por deletar e filtrar os clientes.

	private static final long serialVersionUID = 1L;
	
	private ClienteFiltro clienteFiltro;
	
	private List<Cliente> clientes;
	
	@Inject
	private ClienteService clienteService;
	
	@PostConstruct
	public void init() {
		
		limpar();
		filtrar();
	}
	
	
	public void deletar(Cliente cliente) {
		
		try {
			clienteService.deletarCliente(cliente);
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
		reportarMensagemDeSucesso("O cliente " + cliente.getNome() + " foi excluído!");
	}
	
	public String filtrar() {
		
		try {
			clientes = clienteService.encontrarPor(getClienteFiltro());
			
		} catch (DACExceptions e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}
	
	
	public String limpar() {
		
		this.clienteFiltro = new ClienteFiltro();
		return null;
	}

	
	public ClienteFiltro getClienteFiltro() {
		return clienteFiltro;
	}
	public void setClienteFiltro(ClienteFiltro clienteFiltro) {
		this.clienteFiltro = clienteFiltro;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public ClienteService getClienteService() {
		return clienteService;
	}
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
}
