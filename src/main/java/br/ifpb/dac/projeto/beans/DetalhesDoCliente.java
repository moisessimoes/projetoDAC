package br.ifpb.dac.projeto.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.ifpb.dac.projeto.entidades.ContaNoSistema;

@Named
@RequestScoped
public class DetalhesDoCliente extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Essa classe retorna o usuario que esta logado no sistema.
	
	private ContaNoSistema clienteInfo;
	
	@PostConstruct
	public void init() {
		
		this.clienteInfo = getUsuarioLogado();
	}

	
	public ContaNoSistema getContaCliente() {
		return clienteInfo;
	}
	public void setContaCliente(ContaNoSistema contaClientee) {
		this.clienteInfo = contaClientee;
	}
}
