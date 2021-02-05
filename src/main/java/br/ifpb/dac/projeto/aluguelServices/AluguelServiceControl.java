package br.ifpb.dac.projeto.aluguelServices;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.ifpb.dac.projeto.dao.AluguelCarroDAO;
import br.ifpb.dac.projeto.entidades.Reserva;
import br.ifpb.dac.projeto.entidades.Carro;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.AluguelCarroFiltro;
import br.ifpb.dac.projeto.utils.TransacionalCdi;

public class AluguelServiceControl implements Serializable, AluguelService {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AluguelCarroDAO aluguelCarroDAO;

	@Override
	@TransacionalCdi
	public void salvarReserva(Reserva reserva) throws DACExceptions {
		
		try {
			aluguelCarroDAO.save(reserva);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
		
	}

	@Override
	@TransacionalCdi
	public Reserva atualizarReserva(Reserva reserva) throws DACExceptions {
		
		try {
			return aluguelCarroDAO.update(reserva);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void deletarReserva(Reserva reserva) throws DACExceptions {
		
		try {
			aluguelCarroDAO.delete(reserva);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	
	@Override
	public Reserva getReservaPeloID(int id) throws DACExceptions {
		
		try {
			return aluguelCarroDAO.getRentByID(id);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}
	
	
	@Override
	public Reserva getReservaPeloCodigo(int codigo) throws DACExceptions {
		
		try {
			return aluguelCarroDAO.getRentByCode(codigo);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	
	@Override
	public List<Reserva> getTodasAsReservas() throws DACExceptions {
		
		try {
			return aluguelCarroDAO.getAllRents();
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	
	@Override
	public List<Reserva> encontrarPor(AluguelCarroFiltro reservaFiltro) throws DACExceptions {
		
		try {
			return aluguelCarroDAO.findBy(reservaFiltro);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	@Override
	public boolean validarCodigoDeBusca(String codigo) {
		
		return aluguelCarroDAO.validateSearchCode(codigo);
	}

	
	
	@Override
	public Reserva carroAlugado(Carro carro) throws DACExceptions {
		
		return aluguelCarroDAO.isCarRented(carro);
	}
}
