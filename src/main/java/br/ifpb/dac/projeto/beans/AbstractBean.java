package br.ifpb.dac.projeto.beans;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import br.ifpb.dac.projeto.contasServices.ContaService;
import br.ifpb.dac.projeto.entidades.Booleanos;
import br.ifpb.dac.projeto.entidades.ContaNoSistema;
import br.ifpb.dac.projeto.entidades.Cores;
import br.ifpb.dac.projeto.entidades.DiasDaSemana;
import br.ifpb.dac.projeto.entidades.Grupos;
import br.ifpb.dac.projeto.entidades.GruposDeCarro;
import br.ifpb.dac.projeto.entidades.Marcas;
import br.ifpb.dac.projeto.entidades.Sexo;
import br.ifpb.dac.projeto.entidades.TransmissaoDoCarro;
import br.ifpb.dac.projeto.filtros.ContasFiltro;
import br.ifpb.dac.projeto.utils.Formatadores;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ContaService contaService;
	
	@Inject
	private Formatadores format;
	
	
	public Sexo[] getGenero() {
		return Sexo.values();
	}
	
	public Sexo getGeneroM() {
		return Sexo.Masculino;
	}
	
	public Sexo getGeneroF() {
		return Sexo.Feminino;
	}
	
	public Grupos[] getGrupo() {
		return Grupos.values();
	}
	
	public GruposDeCarro[] getGruposDeCarro() {
		return GruposDeCarro.values();
	}
	
	public Marcas[] getMarcas() {
		return Marcas.values();
	}
	
	public Cores[] getCores() {
		return Cores.values();
	}
	
	public Booleanos[] getBooleanos() {
		return Booleanos.values();
	}
	
	public TransmissaoDoCarro[] getTransmissaoDoCarro() {
		return TransmissaoDoCarro.values();
	}
	
	public DiasDaSemana[] getDiasDaSemana() {
		return DiasDaSemana.values();
	}
	
	protected void reportarMensagemDeErro(String detalhe) {
		reportarMensagem(true, detalhe, false);
	}

	protected void reportarMensagemDeSucesso(String detalhe) {
		reportarMensagem(false, detalhe, true);
	}
	
	
	private void reportarMensagem(boolean isErro, String detalhe, boolean keepMessages) {
		
		String sumario = "Sucesso!";
		Severity severity = FacesMessage.SEVERITY_INFO;
		
		if (isErro) {
			sumario = "Erro!";
			severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();
		} 

		FacesMessage msg = new FacesMessage(severity, sumario, detalhe);

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(keepMessages);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	
	public void addCheckMessage(boolean tarifa) { // https://www.primefaces.org/showcase/ui/input/booleanCheckbox.xhtml

		String summary = null;
		Severity severity = null;
		
		if(tarifa == true) {
			
			summary = "Tarifa Adicionada";
			severity = FacesMessage.SEVERITY_INFO;
			
			FacesMessage msg = new FacesMessage(severity, summary, null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} else {
			
			summary = "Tarifa Removida";
			severity = FacesMessage.SEVERITY_WARN;
			
			FacesMessage msg = new FacesMessage(severity, summary, null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	
	
	public void checkAnswer(boolean resposta) { // https://www.primefaces.org/showcase/ui/input/booleanCheckbox.xhtml
		
		String summary = resposta ? "Resposta Correta." : "Resposta Errada.";
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
	}
	
	
	public boolean isUserInRole(String role) { //Papel do usuario
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		ExternalContext externalContext = facesContext.getExternalContext();
		
		return externalContext.isUserInRole(role);
	}
	
	
	public String getUserEmail() { //pegar email do usuario
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		ExternalContext externalContext = facesContext.getExternalContext();
		
		Principal userPrincipal = externalContext.getUserPrincipal();
		
		if (userPrincipal == null) {
			return "";
		}
		return userPrincipal.getName();
	}
	
	
	public ContaNoSistema getUsuarioLogado() { //Obter usuário que está logado no sistema.
		
		ContasFiltro filter = new ContasFiltro();
		filter.setEmail(getUserEmail());
		
		List<ContaNoSistema> contas = null;
		
		try {
			contas = contaService.encontrarPor(filter);
			
		} catch (Exception e) {
			e.printStackTrace();
			reportarMensagemDeErro("Erro ao recuperar usuário logado!");
		}
		
		if(!contas.isEmpty()) {
			return contas.get(0);
		}
		return null;
	}
	
	
	public String formatarCPF(String cpf) {  
		
		 return format.cpfFormatter(cpf);
    }
	
	public String formatarCNPJ(String cnpj) {  
		
		return format.cnpjFormatter(cnpj);
	}
	
	
	public String formatarTelefone(String numero) {
		
		return format.telephoneFormatter(numero);
	}
	
	
	public String formatarData(Date data) {
		
		return format.dateFormatter(data);
	}
	
	
	public String boasVindas() {
		
		return format.boasVindas();
	}
	
	
	public String mostrarHoras() {
		
		return format.mostrarAsHoras();
	}
}
