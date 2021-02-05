package br.ifpb.dac.projeto.beans.aluguel;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.dac.projeto.aluguelServices.AluguelService;
import br.ifpb.dac.projeto.beans.AbstractBean;
import br.ifpb.dac.projeto.beans.EnderecoDasPaginasWeb;
import br.ifpb.dac.projeto.entidades.Reserva;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.AluguelCarroFiltro;

@Named
@ViewScoped
public class CancelarReservaBean extends AbstractBean implements Serializable {
	
	//Classe bean responsavel cancelar uma reserva já feita pelo cliente

	private static final long serialVersionUID = 1L;
	
	private AluguelCarroFiltro aluguelFiltro;
	
	private Reserva reserva;
	
	private List<Reserva> reservas;
	
	@Inject
	private AluguelService aluguelService;
	
	@PostConstruct
	public void init() {
		
		limpar();
		filtrar();
	}
	
	
	public String cancelarReserva(Reserva reserva) {
		
		try {
			aluguelService.deletarReserva(reserva);
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
		reportarMensagemDeSucesso("Sua reserva foi cancelada com sucesso!");
		
		return EnderecoDasPaginasWeb.PAGINA_PRINCIPAL_CLIENTE;
	}
	
	
	public String filtrar() {
		
		try {
			reservas = aluguelService.encontrarPor(aluguelFiltro);
		} catch (DACExceptions e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}
	
	
	public String limpar() {
		
		this.aluguelFiltro = new AluguelCarroFiltro();
		return null;
	}

	
	public AluguelCarroFiltro getAluguelFiltro() {
		return aluguelFiltro;
	}
	public void setAluguelFiltro(AluguelCarroFiltro aluguelFiltro) {
		this.aluguelFiltro = aluguelFiltro;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	public AluguelService getAluguelService() {
		return aluguelService;
	}
	public void setAluguelService(AluguelService aluguelService) {
		this.aluguelService = aluguelService;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
}
