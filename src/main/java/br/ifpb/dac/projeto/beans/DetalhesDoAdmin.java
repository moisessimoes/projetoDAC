package br.ifpb.dac.projeto.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.ifpb.dac.projeto.entidades.ContaNoSistema;

@Named
@RequestScoped
public class DetalhesDoAdmin extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ContaNoSistema adminInfo;
	
	@PostConstruct
	public void init() {
		
		this.adminInfo = getUsuarioLogado();
	}

	
	public ContaNoSistema getContaAdmin() {
		return adminInfo;
	}
	public void setContaAdmin(ContaNoSistema contaAdmin) {
		this.adminInfo = contaAdmin;
	}

}
