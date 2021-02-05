package br.ifpb.dac.projeto.beans.admin;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.dac.projeto.adminServices.AdministradorService;
import br.ifpb.dac.projeto.beans.AbstractBean;
import br.ifpb.dac.projeto.entidades.Administrador;
import br.ifpb.dac.projeto.exceptions.DACExceptions;

@Named
@ViewScoped
public class EditarEmailSenhaAdministradorBean extends AbstractBean implements Serializable {
	
	//Classe bean responsavel editar o e-mail e senha de um administrador.

	private static final long serialVersionUID = 1L;
	
	private Administrador admin;
	
	private String emailAtual;
	
	private String newEmail;
	
	private String confirmacaoPasswordAtual;

	private String newPassword;
	
	@Inject
	private AdministradorService adminService;
	
	
	public void changeEmail() {
		
		/**
		 *Pelo mesmo motivo do cliente, ele não substitui o email antigo pelo novo, ele apenas adiciona o novo email no banco e como problema temos:
		 *
		 *Uma tabela com dois emails diferentes, mas com as mesmas senhas impendido a autenticação futura.
		 *
		 *O que estou fazendo é o seguinte, assim que adiciono o novo email, em seguida apago o antigo, impedindo assim o problema acima.
		 */
		
		try {
			
			if(!emailAtualConfere()) {
				
				reportarMensagemDeErro("O email atual inserido está faltando ou está incorreto!");
			}
			
			admin.getConta().setEmail(getNewEmail());
			adminService.atualizarAdmin(admin, false);
			adminService.atualizarEmail(getEmailAtual());
			
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
			
			admin.getConta().setSenha(getNewPassword());
			adminService.atualizarAdmin(admin, true);
			
		} catch (DACExceptions e) {
			reportarMensagemDeErro(e.getMessage());
		}
		
		reportarMensagemDeSucesso("Senha alterada com sucesso para o administrador " + admin.getNome());
	}
	
	
	private boolean senhaAtualConfere() throws DACExceptions {
		
		return adminService.conferirSenha(this.admin, getConfirmacaoPasswordAtual()); 
	}
	
	private boolean emailAtualConfere() throws DACExceptions {
		
		return adminService.conferirEmail(this.admin, getEmailAtual());
	}
	
	
	public Administrador getAdmin() {
		return admin;
	}
	public void setAdmin(Administrador admin) {
		this.admin = admin;
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
	public AdministradorService getAdminService() {
		return adminService;
	}
	public void setAdminService(AdministradorService adminService) {
		this.adminService = adminService;
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
