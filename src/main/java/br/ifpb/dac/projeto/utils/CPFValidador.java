package br.ifpb.dac.projeto.utils;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;


@FacesValidator(managed = true, value = "cpfValidator")
public class CPFValidador implements Validator<String> {
	
	
	public boolean validarCPF(String cpf) { //https://www.alura.com.br/artigos/validando-cpf-com-java-atraves-do-stella
		
		CPFValidator validadorCPF = new CPFValidator(); //Classe da API Caelum Stella para validar CPF.
		
		List<ValidationMessage> erros = validadorCPF.invalidMessagesFor(cpf);
		
		if(erros.size() > 0) return false;
		
		else
			return true;
	}
	
	
	@Override
	public void validate(FacesContext context, UIComponent component, String cpf) throws ValidatorException {
		
		boolean cpfValido = validarCPF(cpf);
		
		if(cpfValido == false) {
			
			String sumario = "Erro!";
			Severity severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();

			FacesMessage msg = new FacesMessage(severity, sumario, "O CPF inserido é INVÁLIDO!");

			throw new ValidatorException(msg);
		}
	}
}
