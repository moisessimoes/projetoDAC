package br.ifpb.dac.projeto.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ErroPaginaLogin extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public void dispararMensagemLoginErro(boolean error){
		if(error){
			reportarMensagemDeErro("Login e/ou senha errada.");
		}
	}
}
