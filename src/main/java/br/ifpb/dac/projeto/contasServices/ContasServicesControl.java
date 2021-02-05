package br.ifpb.dac.projeto.contasServices;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.ifpb.dac.projeto.dao.ContasDAO;
import br.ifpb.dac.projeto.entidades.ContaNoSistema;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.ContasFiltro;

public class ContasServicesControl implements Serializable, ContaService {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ContasDAO contaDAO;

	@Override
	public List<ContaNoSistema> getTodasAsContas() throws DACExceptions {
		
		try {
			return contaDAO.getAllAccounts();
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	@Override
	public List<ContaNoSistema> getTodasAsContasDeClientes() throws DACExceptions {
		
		try {
			return contaDAO.getAllClientsAccounts();
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	@Override
	public List<ContaNoSistema> getTodasAsContasDeAdmins() throws DACExceptions {
		
		try {
			return contaDAO.getAllEmployeeAccounts();
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	@Override
	public List<ContaNoSistema> encontrarPor(ContasFiltro filter) throws DACExceptions {
		
		try {
			return contaDAO.findBy(filter);
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	@Override
	public ContaNoSistema getConta(String email) throws DACExceptions {
		
		try {
			return contaDAO.getAnAccount(email);
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	@Override
	public boolean validarExistenciaDeEmail(String email) throws DACExceptions {
		
		try {
			return contaDAO.validarExistenciaDeEmail(email);
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}
}
