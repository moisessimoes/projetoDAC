package br.ifpb.dac.projeto.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.ifpb.dac.projeto.agenciaServices.AgenciaService;
import br.ifpb.dac.projeto.entidades.Agencia;
import br.ifpb.dac.projeto.exceptions.DACExceptions;

@FacesConverter(forClass = Agencia.class)
public class AgenciaConverter implements Converter<Agencia> {
	
	@Inject
	private AgenciaService agency;

	@Override
	public Agencia getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			return agency.getAgenciaPeloID(id);
			
		} catch (DACExceptions | NumberFormatException e) {
			String msgErroStr = String.format("Erro de conversão! Não foi possível realizar a converão da string '%s' para o tipo esperado.", value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Agencia value) {
		
		if (!(value instanceof Agencia)) {
			return null;
		}
		
		Integer id = ((Agencia) value).getId();
		return (id != null) ? id.toString() : null;
	}
}
