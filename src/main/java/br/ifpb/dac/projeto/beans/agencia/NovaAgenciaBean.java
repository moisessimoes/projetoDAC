package br.ifpb.dac.projeto.beans.agencia;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.dac.projeto.agenciaServices.AgenciaService;
import br.ifpb.dac.projeto.beans.AbstractBean;
import br.ifpb.dac.projeto.beans.EnderecoDasPaginasWeb;
import br.ifpb.dac.projeto.entidades.Agencia;
import br.ifpb.dac.projeto.entidades.Endereco;
import br.ifpb.dac.projeto.entidades.HorariosDeFuncionamento;
import br.ifpb.dac.projeto.logicaNegocio.Horarios;

@Named
@ViewScoped
public class NovaAgenciaBean extends AbstractBean implements Serializable { //Classe bean responsavel por salvar e editar as agencias.

	private static final long serialVersionUID = 1L;
	
	private Agencia agencia;
	
	private Agencia selectedAgency;
	
	@Inject
	private AgenciaService agenciaService;
	
	@EJB
	private Horarios horarios;
	
	
	public void iniciar() {
		
		try {
			if(agencia == null) {
				
				//criando nova agencia
				
				Endereco endereco = new Endereco();
				
				agencia = new Agencia();
				
				HorariosDeFuncionamento horarioFuncionamento = new HorariosDeFuncionamento();
				
				agencia.setEndereco(endereco);
				
				agencia.setHorarios(horarioFuncionamento);
				
				
			} else {
				//caso seja edicao
				
				selectedAgency = agenciaService.getAgenciaPeloID(agencia.getId());
			}
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	
	public String salvarAgencia() {
		
		try {
			if(isEdicaoAgencia()) {
				
				agenciaService.atualizarAgencia(agencia);
				
				reportarMensagemDeSucesso("Os dados da agência foram atualizados com sucesso!");
				
				return EnderecoDasPaginasWeb.PAGINA_LISTAR_AGENCIAS;
				
			} else {
				
				agenciaService.salvarAgencia(agencia);
			}
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
		
		reportarMensagemDeSucesso("A agência " + agencia.getNome() + " foi cadastrada com sucesso!");
		
		return EnderecoDasPaginasWeb.PAGINA_LISTAR_AGENCIAS;
	}
	
	
	public List<Agencia> listarAgencias() {
		
		try {
			return agenciaService.getTodasAsAgencias();
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
	}
	
	
	
	public boolean isEdicaoAgencia() {
		
		return agencia != null && agencia.getId() != null;
	}
	
	
	public String[] getHorarios() {
		return horarios.getHorarios();
	}


	public Agencia getAgencia() {
		return agencia;
	}
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	public Agencia getSelectedAgency() {
		return selectedAgency;
	}
	public void setSelectedAgency(Agencia selectedAgency) {
		this.selectedAgency = selectedAgency;
	}
	public AgenciaService getAgenciaService() {
		return agenciaService;
	}
	public void setAgenciaService(AgenciaService agenciaService) {
		this.agenciaService = agenciaService;
	}
}
