package br.ifpb.dac.projeto.beans.aluguel;

import java.io.Serializable;
import java.text.NumberFormat;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.dac.projeto.agenciaServices.AgenciaService;
import br.ifpb.dac.projeto.aluguelServices.AluguelService;
import br.ifpb.dac.projeto.beans.AbstractBean;
import br.ifpb.dac.projeto.entidades.Agencia;
import br.ifpb.dac.projeto.entidades.Reserva;
import br.ifpb.dac.projeto.exceptions.DACExceptions;

@Named
@ViewScoped
public class ReservasDoClienteBean extends AbstractBean implements Serializable {
	
	//Classe bean responsavel buscar uma reserva já feita pelo cliente

	private static final long serialVersionUID = 1L;
	
	private String codigoReserva;
	
	private Reserva reserva;
	
	@Inject
	private AluguelService aluguelService;
	
	@Inject
	private AgenciaService agencyService;
	
	
	public Reserva buscarReservaPeloCodigo() {
		
		try {
			
			if(validarCodigo()) {
				
				return aluguelService.getReservaPeloCodigo(Integer.parseInt(getCodigoReserva()));
			}
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}
	
	
	public boolean reservaExiste() {
		
		return buscarReservaPeloCodigo() != null;
	}
	
	
	public Agencia buscarAgencia(int id) {
		
		try {
			return agencyService.getAgenciaPeloID(id);
			
		} catch (DACExceptions e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
	}
	
	
	public boolean validarCodigo() {
		
		if(aluguelService.validarCodigoDeBusca(getCodigoReserva())) return true;
			
		
		reportarMensagemDeErro("Código " + "'"+getCodigoReserva()+"' " + " Inválido!");
		return false;
	}
	

	
	public String formatarValoresMonetarios(double valor) {
		
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		
		return String.valueOf(nf.format(valor));
	}
	
	
	
	public String getCodigoReserva() {
		return codigoReserva;
	}
	public void setCodigoReserva(String codigoReserva) {
		this.codigoReserva = codigoReserva;
	}
	public AluguelService getAluguelService() {
		return aluguelService;
	}
	public void setAluguelService(AluguelService aluguelService) {
		this.aluguelService = aluguelService;
	}
	public AgenciaService getAgencyService() {
		return agencyService;
	}
	public void setAgencyService(AgenciaService agencyService) {
		this.agencyService = agencyService;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
}
