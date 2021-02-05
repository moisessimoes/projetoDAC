package br.ifpb.dac.projeto.agenciaServices;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.ifpb.dac.projeto.dao.AgenciaDAO;
import br.ifpb.dac.projeto.entidades.Agencia;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.AgenciaFiltro;
import br.ifpb.dac.projeto.utils.TransacionalCdi;

public class AgenciaServiceControl implements Serializable, AgenciaService {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AgenciaDAO agencyDAO;

	@Override
	@TransacionalCdi
	public void salvarAgencia(Agencia agency) throws DACExceptions {
		
		try {
			agencyDAO.save(agency);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public Agencia atualizarAgencia(Agencia agency) throws DACExceptions {
		
		try {
			return agencyDAO.update(agency);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void deletarAgencia(Agencia agency) throws DACExceptions {
		
		try {
			agencyDAO.delete(agency);
			
		} catch (DACExceptions pe) {
			pe.printStackTrace();
			throw new DACExceptions(pe.getMessage(), pe);
		}
	}
	
	@Override
	public Agencia getAgenciaPeloID(int id) throws DACExceptions {
		
		try {
			return agencyDAO.getAgencyByID(id);
			
		} catch (DACExceptions pe) {
			pe.printStackTrace();
			throw new DACExceptions(pe.getMessage(), pe);
		}
	}
	
	
	@Override
	public Agencia getAgenciaPeloNome(String nome) throws DACExceptions {
		
		try {
			return agencyDAO.getAgencyByName(nome);
			
		} catch (DACExceptions pe) {
			pe.printStackTrace();
			throw new DACExceptions(pe.getMessage(), pe);
		}
	}
	

	@Override
	public List<Agencia> getTodasAsAgencias() throws DACExceptions {
		
		try {
			return agencyDAO.getAllAgencies();
			
		} catch (DACExceptions pe) {
			pe.printStackTrace();
			throw new DACExceptions(pe.getMessage(), pe);
		}
	}

	@Override
	public List<Agencia> encontrarPor(AgenciaFiltro agencyFilter) throws DACExceptions {
		
		try {
			return agencyDAO.findBy(agencyFilter);
			
		} catch (DACExceptions pe) {
			pe.printStackTrace();
			throw new DACExceptions(pe.getMessage(), pe);
		}
	}
}
