package br.ifpb.dac.projeto.agenciaServices;

import java.util.List;

import br.ifpb.dac.projeto.entidades.Agencia;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.AgenciaFiltro;

public interface AgenciaService {
	
	public void salvarAgencia(Agencia agency) throws DACExceptions;
	
	public Agencia atualizarAgencia(Agencia agency) throws DACExceptions;
	
	public void deletarAgencia(Agencia agency) throws DACExceptions;
	
	public Agencia getAgenciaPeloID(int id) throws DACExceptions;
	
	public Agencia getAgenciaPeloNome(String nome) throws DACExceptions;
	
	public List<Agencia> getTodasAsAgencias() throws DACExceptions;
	
	public List<Agencia> encontrarPor(AgenciaFiltro agencyFilter) throws DACExceptions;

}
