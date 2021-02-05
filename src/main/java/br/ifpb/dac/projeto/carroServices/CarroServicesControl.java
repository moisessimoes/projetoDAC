package br.ifpb.dac.projeto.carroServices;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.ifpb.dac.projeto.dao.CarroDAO;
import br.ifpb.dac.projeto.entidades.Carro;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.CarroFiltro;
import br.ifpb.dac.projeto.utils.TransacionalCdi;

public class CarroServicesControl implements Serializable, CarroService {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarroDAO carroDAO;

	
	@Override
	@TransacionalCdi
	public void salvarCarro(Carro carro) throws DACExceptions {
		
		try {
			carroDAO.save(carro);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	
	
	@Override
	@TransacionalCdi
	public void atualizarCarro(Carro carro) throws DACExceptions {
		
		try {
			carroDAO.update(carro);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	
	
	@Override
	@TransacionalCdi
	public void deletarCarro(Carro carro) throws DACExceptions {
		
		try {
			carroDAO.delete(carro);;
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	
	
	@Override
	public Carro getCarroPeloID(int id) throws DACExceptions {
		
		try {
			return carroDAO.getCarroPeloID(id);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	
	
	@Override
	public Carro getCarroPelaPlaca(String placa) throws DACExceptions {
		
		try {
			return carroDAO.getByPlaca(placa);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	
	
	@Override
	public List<Carro> getTodosOsCarros() throws DACExceptions {
		
		try {
			return carroDAO.getAllCars();
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	
	
	@Override
	public List<Carro> encontrarPor(CarroFiltro carroFiltro) throws DACExceptions {
		
		try {
			return carroDAO.findBy(carroFiltro);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}



	@Override
	public List<Carro> getCarrosPelaMarca(String marca) throws DACExceptions {
		
		try {
			return carroDAO.getCarsByBrand(marca);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}
}
