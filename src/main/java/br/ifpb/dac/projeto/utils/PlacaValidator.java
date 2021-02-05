package br.ifpb.dac.projeto.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(managed = true, value = "placa")
public class PlacaValidator implements Validator<String> {

	@Override
	public void validate(FacesContext context, UIComponent component, String placa) throws ValidatorException {
		
		if(placa != null && placa.length() > 0) {
			
			String expression = "[A-Z]{3}\\d{4}";
			
			Pattern pattern = Pattern.compile(expression);
			
			Matcher matcher = pattern.matcher(placa);
			
			if(!matcher.matches()) {
				
				String sumario = "Erro!";
				Severity severity = FacesMessage.SEVERITY_ERROR;
				FacesContext.getCurrentInstance().validationFailed();

				FacesMessage msg = new FacesMessage(severity, sumario, "Placa de carro INVÁLIDA!");

				throw new ValidatorException(msg);
			}
		}
	}
}
