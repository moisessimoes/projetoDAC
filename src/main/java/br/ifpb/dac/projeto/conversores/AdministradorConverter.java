package br.ifpb.dac.projeto.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.ifpb.dac.projeto.adminServices.AdministradorService;
import br.ifpb.dac.projeto.entidades.Administrador;
import br.ifpb.dac.projeto.exceptions.DACExceptions;

@FacesConverter(forClass = Administrador.class)
public class AdministradorConverter implements Converter<Administrador> {
	
	@Inject
	private AdministradorService admin;

	@Override
	public Administrador getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			return admin.getAdminPeloID(id);
			
		} catch (DACExceptions | NumberFormatException e) {
			String msgErroStr = String.format("Erro de conversão! Não foi possível realizar a convesão da string '%s' para o tipo esperado.", value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Administrador value) {
		
		if (!(value instanceof Administrador)) {
			return null;
		}
		
		Integer id = ((Administrador) value).getId();
		return (id != null) ? id.toString() : null;
	}
}
