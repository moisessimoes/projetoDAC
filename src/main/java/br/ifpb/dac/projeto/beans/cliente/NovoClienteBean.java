package br.ifpb.dac.projeto.beans.cliente;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.dac.projeto.beans.AbstractBean;
import br.ifpb.dac.projeto.beans.EnderecoDasPaginasWeb;
import br.ifpb.dac.projeto.clienteServices.ClienteService;
import br.ifpb.dac.projeto.entidades.Cliente;
import br.ifpb.dac.projeto.entidades.ContaNoSistema;
import br.ifpb.dac.projeto.entidades.Grupos;
import br.ifpb.dac.projeto.entidades.Sexo;
import br.ifpb.dac.projeto.exceptions.DACExceptions;

@Named
@ViewScoped
public class NovoClienteBean extends AbstractBean implements Serializable {
	
	/*Esta classe bean é usada na pagina do cliente para salvar e editar os dados cliente*/

	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	
	private Cliente selectedClient;
	
	private ContaNoSistema conta;
	
	private Sexo sexo;
	
	@Inject
	private ClienteService clienteService;
	
	
	
	public void iniciar() {
		
		try {
			if(cliente == null) {
				
				//criando um novo cliente
				
				cliente = new Cliente();
				
				conta = new ContaNoSistema();
				
				cliente.setConta(conta);
				
				conta.setCliente(cliente);
				
				conta.setGrupo(Grupos.Cliente);
				
			} else {
				//caso seja edicao dos dados
					
				selectedClient = clienteService.getClientePeloID(cliente.getId());
			}
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	
	public String salvarCliente() throws DACExceptions {
		
		try {
			
			if(isEdicaoCliente()) {
				
				clienteService.atualizarCliente(cliente, false);
				
				reportarMensagemDeSucesso("Seus dados foram atualizados com sucesso!");
				
				return null;
				
			} else {
				
				clienteService.salvarCliente(cliente);
			}			
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Olá '" + cliente.getNome() + "', seu cadastro foi realizado com sucesso!");
		
		return EnderecoDasPaginasWeb.PAGINA_PRINCIPAL;
	}
	
	
	public List<Cliente> listarClientes() {
		
		try {
			return clienteService.getTodosClientes();
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
	}
	
	
	
	public boolean isEdicaoCliente() {
		
		return cliente != null && cliente.getId() != null;
	}
	
	

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Cliente getSelectedClient() {
		return selectedClient;
	}
	public void setSelectedClient(Cliente selectedClient) {
		this.selectedClient = selectedClient;
	}
	public ContaNoSistema getConta() {
		return conta;
	}
	public void setConta(ContaNoSistema conta) {
		this.conta = conta;
	}
	public ClienteService getClienteService() {
		return clienteService;
	}
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
}
