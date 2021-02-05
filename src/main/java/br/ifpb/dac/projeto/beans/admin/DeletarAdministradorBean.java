package br.ifpb.dac.projeto.beans.admin;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.dac.projeto.adminServices.AdministradorService;
import br.ifpb.dac.projeto.beans.AbstractBean;
import br.ifpb.dac.projeto.entidades.Administrador;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.AdministradorFiltro;

@Named
@ViewScoped
public class DeletarAdministradorBean extends AbstractBean implements Serializable { //Classe bean responsavel por deletar e filtrar os administradores.

	private static final long serialVersionUID = 1L;
	
	private AdministradorFiltro adminFiltro;
	
	private List<Administrador> admins;
	
	@Inject
	private AdministradorService adminService;
	
	@PostConstruct
	public void init() {
		
		limpar();
		filtrar();
	}
	
	
	
	public void deletar(Administrador admin) {
		
		try {
			adminService.deletarAdmin(admin);
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
		
		reportarMensagemDeSucesso("O administrador" + admin.getNome() + " foi excluído.");
		
	}
	
	
	public String filtrar() {
		
		try {
			admins = adminService.encontrarPor(getAdminFiltro());
			
		} catch (DACExceptions e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}
	
	
	public String limpar() {
		
		this.adminFiltro = new AdministradorFiltro();
		return null;
	}
	

	public AdministradorFiltro getAdminFiltro() {
		return adminFiltro;
	}
	public void setAdminFiltro(AdministradorFiltro adminFiltro) {
		this.adminFiltro = adminFiltro;
	}
	public List<Administrador> getAdmins() {
		return admins;
	}
	public void setAdmins(List<Administrador> admins) {
		this.admins = admins;
	}
	public AdministradorService getAdminService() {
		return adminService;
	}
	public void setAdminService(AdministradorService adminService) {
		this.adminService = adminService;
	}
}
