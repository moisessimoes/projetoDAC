package br.ifpb.dac.projeto.beans.errors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.ifpb.dac.projeto.beans.AbstractBean;
import br.ifpb.dac.projeto.exceptions.DACExceptions;

@Named
@RequestScoped
public class ErrorAction extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void action() throws DACExceptions {
		throw new DACExceptions("Simulação de erro no método chamado por um 'action'");
	}
}
