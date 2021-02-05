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
import javax.inject.Inject;

import br.ifpb.dac.projeto.contasServices.ContaService;
import br.ifpb.dac.projeto.exceptions.DACExceptions;

@FacesValidator(managed = true, value = "emailValidator")
public class EmailValidator implements Validator<String> { //Validador personalizado para o campo email.
	
	@Inject
	private ContaService conta;
	
	
	public boolean emailJaExiste(String email) {
		
		try {
			return conta.validarExistenciaDeEmail(email);
			
		} catch (DACExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

	@Override
	public void validate(FacesContext context, UIComponent component, String email) throws ValidatorException {
		
		if (email != null && email.length() > 0) {

			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);

			Matcher matcher = pattern.matcher(email);

			if (!matcher.matches()) {
				
				String sumario = "Erro!";
				Severity severity = FacesMessage.SEVERITY_ERROR;
				FacesContext.getCurrentInstance().validationFailed();

				FacesMessage msg = new FacesMessage(severity, sumario, "O e-mail inserido é INVÁLIDO!");

				throw new ValidatorException(msg);
			}
		}
		
		
		if(emailJaExiste(email) == true) {
			
			String sumario = "Erro!";
			Severity severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();

			FacesMessage msg = new FacesMessage(severity, sumario, "O e-mail inserido já existe em nossos registros! Escolha outro e-mail.");

			throw new ValidatorException(msg);
		}
	}
}
