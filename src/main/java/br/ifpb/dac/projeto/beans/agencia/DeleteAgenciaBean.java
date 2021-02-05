package br.ifpb.dac.projeto.beans.agencia;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.dac.projeto.agenciaServices.AgenciaService;
import br.ifpb.dac.projeto.beans.AbstractBean;
import br.ifpb.dac.projeto.entidades.Agencia;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.AgenciaFiltro;

@Named
@ViewScoped
public class DeleteAgenciaBean extends AbstractBean implements Serializable { //Classe bean respons�vel por deletar e filtrar as agencias.

	private static final long serialVersionUID = 1L;
	
	private AgenciaFiltro agenciaFiltro;
	
	private List<Agencia> agencias;
	
	@Inject
	private AgenciaService agenciaService;
	
	@PostConstruct
	public void init() {
		
		limpar();
		filtrar();
	}
	
	
	public void deletar(Agencia agency) {
		
		try {
			agenciaService.deletarAgencia(agency);
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
		reportarMensagemDeSucesso("A agência " + agency.getNome() + " foi excluída.");
	}
	
	
	public String filtrar() {
		
		try {
			agencias = agenciaService.encontrarPor(agenciaFiltro);
			
		} catch (DACExceptions e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}
	
	
	public String limpar() {
		
		this.agenciaFiltro = new AgenciaFiltro();
		return null;
	}


	public AgenciaFiltro getAgenciaFiltro() {
		return agenciaFiltro;
	}
	public void setAgenciaFiltro(AgenciaFiltro agenciaFiltro) {
		this.agenciaFiltro = agenciaFiltro;
	}
	public List<Agencia> getAgencias() {
		return agencias;
	}
	public void setAgencias(List<Agencia> agencias) {
		this.agencias = agencias;
	}
	public AgenciaService getAgenciaService() {
		return agenciaService;
	}
	public void setAgenciaService(AgenciaService agenciaService) {
		this.agenciaService = agenciaService;
	}
}
