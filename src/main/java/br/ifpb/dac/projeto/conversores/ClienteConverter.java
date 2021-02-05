package br.ifpb.dac.projeto.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.ifpb.dac.projeto.clienteServices.ClienteService;
import br.ifpb.dac.projeto.entidades.Cliente;
import br.ifpb.dac.projeto.exceptions.DACExceptions;

@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter<Cliente> {
	
	@Inject
	private ClienteService cliente;

	@Override
	public Cliente getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			return cliente.getClientePeloID(id);
			
		} catch (DACExceptions | NumberFormatException e) {
			String msgErroStr = String.format("Erro de converão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.", value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Cliente value) {
		
		if (!(value instanceof Cliente)) {
			return null;
		}
		
		Integer id = ((Cliente) value).getId();
		return (id != null) ? id.toString() : null;
	}
}
