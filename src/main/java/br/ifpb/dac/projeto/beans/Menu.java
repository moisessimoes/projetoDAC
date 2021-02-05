package br.ifpb.dac.projeto.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.dac.projeto.utils.AcessoRestrito;

@Named
@RequestScoped
public class Menu extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AcessoRestrito acesso;
	
	private String resposta;
	
	private boolean acertou;
	
	
	public String verificarResposta() {
		
		if(acesso.acertou(getResposta())) {
			
			reportarMensagemDeSucesso("Resposta Correta!");
			
			return EnderecoDasPaginasWeb.PAGINA_CADASTRAR_ADMIN;
		}
		
		reportarMensagemDeErro("Resposta Errada!");
		
		return null;
	}
	
	
	public boolean desativarLink() {
		
		return getResposta() != "João Paulo";
	}
	
	
	
	

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public boolean isAcertou() {
		return acertou;
	}

	public void setAcertou(boolean acertou) {
		this.acertou = acertou;
	}

	public AcessoRestrito getAcesso() {
		return acesso;
	}

	public void setAcesso(AcessoRestrito acesso) {
		this.acesso = acesso;
	}
}
