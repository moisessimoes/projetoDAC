package br.ifpb.dac.projeto.beans.admin;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.dac.projeto.adminServices.AdministradorService;
import br.ifpb.dac.projeto.beans.AbstractBean;
import br.ifpb.dac.projeto.beans.EnderecoDasPaginasWeb;
import br.ifpb.dac.projeto.entidades.Administrador;
import br.ifpb.dac.projeto.entidades.ContaNoSistema;
import br.ifpb.dac.projeto.entidades.Grupos;

@Named
@ViewScoped
public class NovoAdministradorBean extends AbstractBean implements Serializable { //Classe bean responsavel por salvar e editar os administradores.

	private static final long serialVersionUID = 1L;
	
	private Administrador admin;
	
	private Administrador selectedAdmin;
	
	private ContaNoSistema conta;
	
	@Inject
	private AdministradorService adminService;
	
	
	public void iniciar() {
		
		try {
			if(admin == null) {
				
				//criando novo admin
				
				admin = new Administrador();
				
				conta = new ContaNoSistema();
				
				admin.setConta(conta);
				
				conta.setAdministrador(admin);
				
				conta.setGrupo(Grupos.Administrador);
				
			} else {
				
				//caso seja edicao de dados
				
				selectedAdmin = adminService.getAdminPeloID(admin.getId());
			}
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	
	public String salvarAdmin() {
		
		try {
			
			if(isEdicaoAdmin()) {
				
				adminService.atualizarAdmin(admin, false);
				
				reportarMensagemDeSucesso("Seus dados foram atualizados com sucesso!");
				
				return null;
				
			} else {
				
				adminService.salvarAdmin(admin);
			}
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
		
		reportarMensagemDeSucesso("Olá '" + admin.getNome() + "', seu cadastro foi realizado com sucesso!");
		
		return EnderecoDasPaginasWeb.PAGINA_LISTAR_ADMINS;
	}
	
	
	public List<Administrador> listarAdministradores() {
		
		try {
			return adminService.getTodosOsAdmins();
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
	}

	
	public boolean isEdicaoAdmin() {
		
		return admin != null && admin.getId() != null;
	}


	public Administrador getAdmin() {
		return admin;
	}
	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}
	public Administrador getSelectedAdmin() {
		return selectedAdmin;
	}
	public void setSelectedAdmin(Administrador selectedAdmin) {
		this.selectedAdmin = selectedAdmin;
	}
	public ContaNoSistema getConta() {
		return conta;
	}
	public void setConta(ContaNoSistema conta) {
		this.conta = conta;
	}
	public AdministradorService getAdminService() {
		return adminService;
	}
	public void setAdminService(AdministradorService adminService) {
		this.adminService = adminService;
	}
}
