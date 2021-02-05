package br.ifpb.dac.projeto.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.ifpb.dac.projeto.carroServices.CarroService;
import br.ifpb.dac.projeto.entidades.Carro;
import br.ifpb.dac.projeto.exceptions.DACExceptions;

@FacesConverter(forClass = Carro.class)
public class CarroConverter implements Converter<Carro> {
	
	@Inject
	private CarroService carro;

	@Override
	public Carro getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			return carro.getCarroPeloID(id);
			
		} catch (DACExceptions | NumberFormatException e) {
			
			String msgErroStr = String.format("Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.", value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Carro value) {
		
		if (!(value instanceof Carro)) {
			return null;
		}
		
		Integer id = ((Carro) value).getId();
		return (id != null) ? id.toString() : null;
	}
}
