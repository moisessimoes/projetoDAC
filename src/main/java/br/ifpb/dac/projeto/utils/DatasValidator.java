package br.ifpb.dac.projeto.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.joda.time.DateTime;

@FacesValidator(managed = true, value = "datasValidator")
public class DatasValidator implements Validator<Date> { //Classe responsavel ppr validar as datas inseridas pelo usuario.
	

	@Override
	public void validate(FacesContext context, UIComponent component, Date value) throws ValidatorException {
		
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
		
		Date dataAtualExibir = new Date();
		
		DateTime data = new DateTime(value); //Convertendo a data inserida pelo usuario
		
		DateTime dataHoje = DateTime.now(); //Pegando a data atual do sistema
		
		if(data.isBefore(dataHoje)) { //Verifica se a data inserida pelo usuario é do dia anterior a data atual do sistema.
			
			String sumario = "Erro!";
			Severity severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();

			FacesMessage msg = new FacesMessage(severity, sumario, "A data inserida é INVÁLIDA! Informe uma data igual ou superior a " + formatar.format(dataAtualExibir)+".");

			throw new ValidatorException(msg);
		}
	}
}
