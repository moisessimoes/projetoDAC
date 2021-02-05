package br.ifpb.dac.projeto.beans.errors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.ifpb.dac.projeto.beans.AbstractBean;
import br.ifpb.dac.projeto.exceptions.DACRunTimeException;

@Named
@RequestScoped
public class ErrorPostConstruct extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		throw new DACRunTimeException("Simulação de erro num método anotado com @PostConstruct");
	}
	
	public void fazerNada() {
		
	}
}
