package br.ifpb.dac.projeto.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class SessionTimeout extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9054621918685056572L;
	
	public void init() {
		
		reportarMensagemDeErro("Sua sessão expirou. Faça autenticação novamente no sistema!");
	}
}
