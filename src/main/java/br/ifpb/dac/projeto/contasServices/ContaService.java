package br.ifpb.dac.projeto.contasServices;

import java.util.List;

import br.ifpb.dac.projeto.entidades.ContaNoSistema;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.ContasFiltro;

public interface ContaService {
	
	public List<ContaNoSistema> getTodasAsContas() throws DACExceptions;
	
	public List<ContaNoSistema> getTodasAsContasDeClientes() throws DACExceptions;
	
	public List<ContaNoSistema> getTodasAsContasDeAdmins() throws DACExceptions;
	
	public List<ContaNoSistema> encontrarPor(ContasFiltro filter) throws DACExceptions;
	
	public ContaNoSistema getConta(String email) throws DACExceptions;
	
	public boolean validarExistenciaDeEmail(String email) throws DACExceptions;

}
