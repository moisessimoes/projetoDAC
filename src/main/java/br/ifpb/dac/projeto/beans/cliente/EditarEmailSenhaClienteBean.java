package br.ifpb.dac.projeto.beans.cliente;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.dac.projeto.beans.AbstractBean;
import br.ifpb.dac.projeto.clienteServices.ClienteService;
import br.ifpb.dac.projeto.entidades.Cliente;
import br.ifpb.dac.projeto.exceptions.DACExceptions;

@Named
@ViewScoped
public class EditarEmailSenhaClienteBean extends AbstractBean implements Serializable {
	
	//Classe bean responsavel pela edicao de e-mail e senha do cliente.

	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	
	private String emailAtual;
	
	private String newEmail;
	
	private String confirmacaoPasswordAtual;

	private String newPassword;
	
	@Inject
	private ClienteService clientService;
	
	
	public void changeEmail() {
		
		/**
		 *Por algum motivo, ele não substitui o email antigo pelo novo, ele apenas adiciona o novo email no banco e como problema temos:
		 *
		 *Uma tabela com dois emails diferentes, mas com as mesmas senhas impendido a autenticação futura.
		 *
		 *O que estou fazendo é o seguinte, assim que adiciono o novo email, em seguida apago o antigo, impedindo assim o problema acima.
		 */
		
		try {
			
			if(!emailAtualConfere()) {
				
				reportarMensagemDeErro("O email atual inserido está faltando ou está incorreto!");
			}
			
			cliente.getConta().setEmail(getNewEmail());
			clientService.atualizarCliente(cliente, false); //Adiciona o novo email
			clientService.atualizarEmail(getEmailAtual()); //Apaga o email antigo
			
		} catch (DACExceptions e) {
			reportarMensagemDeErro(e.getMessage());
		}
		
		reportarMensagemDeSucesso("Email alterado com sucesso! Para continuar, faça logout clicando em 'Sair' e realize o login novamente.");
	}
	
	public void changePassword() {
		
		try {
			// Confirmar que senha atual equivale a armazenada
			if (!senhaAtualConfere()) {
				
				reportarMensagemDeErro("Sua senha atual está faltando ou está incorreta!");
			}
			
			cliente.getConta().setSenha(getNewPassword());
			clientService.atualizarCliente(cliente, true);
			
		} catch (DACExceptions e) {
			reportarMensagemDeErro(e.getMessage());
		}
		
		reportarMensagemDeSucesso("Senha alterada com sucesso para o cliente " + cliente.getNome());
	}
	
	
	private boolean senhaAtualConfere() throws DACExceptions {
		
		return clientService.conferirSenha(this.cliente, getConfirmacaoPasswordAtual()); 
	}
	
	private boolean emailAtualConfere() throws DACExceptions {
		
		return clientService.conferirEmail(this.cliente, getEmailAtual());
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getConfirmacaoPasswordAtual() {
		return confirmacaoPasswordAtual;
	}
	public void setConfirmacaoPasswordAtual(String confirmacaoPasswordAtual) {
		this.confirmacaoPasswordAtual = confirmacaoPasswordAtual;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public ClienteService getClientService() {
		return clientService;
	}
	public void setClientService(ClienteService clientService) {
		this.clientService = clientService;
	}
	public String getEmailAtual() {
		return emailAtual;
	}
	public void setEmailAtual(String emailAtual) {
		this.emailAtual = emailAtual;
	}
	public String getNewEmail() {
		return newEmail;
	}
	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}
}
