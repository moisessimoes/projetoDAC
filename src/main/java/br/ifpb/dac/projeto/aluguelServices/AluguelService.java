package br.ifpb.dac.projeto.aluguelServices;

import java.util.List;

import br.ifpb.dac.projeto.entidades.Reserva;
import br.ifpb.dac.projeto.entidades.Carro;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.AluguelCarroFiltro;

public interface AluguelService {
	
	public void salvarReserva(Reserva reserva) throws DACExceptions;
	
	public Reserva atualizarReserva(Reserva reserva) throws DACExceptions;
	
	public void deletarReserva(Reserva reserva) throws DACExceptions;
	
	public Reserva getReservaPeloID(int id) throws DACExceptions;
	
	public Reserva getReservaPeloCodigo(int codigo) throws DACExceptions;
	
	public List<Reserva> getTodasAsReservas() throws DACExceptions;
	
	public List<Reserva> encontrarPor(AluguelCarroFiltro reservaFiltro) throws DACExceptions;
	
	public boolean validarCodigoDeBusca(String codigo);
	
	public Reserva carroAlugado(Carro carro) throws DACExceptions;

}
