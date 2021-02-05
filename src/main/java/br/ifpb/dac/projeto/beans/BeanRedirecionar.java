package br.ifpb.dac.projeto.beans;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.ifpb.dac.projeto.entidades.Grupos;

@Named
@ViewScoped
public class BeanRedirecionar extends AbstractBean implements Serializable {
	
	//Na hora de fazer login, essa classe faz o redirecionamento do usuario dependendo do seu grupo: Cliente ou Administrador.
	
	//A consulta do login esta sendo feita no wildfly.
	
	private static final long serialVersionUID = 1L;
	
	
	public String redirecionarUsuario() {
		
		if (getUsuarioLogado() != null) {

			if (getUsuarioLogado().getCliente() != null && getUsuarioLogado().getCliente().getConta().getGrupo().equals(Grupos.Cliente)) {

				return EnderecoDasPaginasWeb.PAGINA_PRINCIPAL_CLIENTE;
			}

			else if (getUsuarioLogado().getAdministrador() != null && getUsuarioLogado().getAdministrador().getConta().getGrupo().equals(Grupos.Administrador)) {

				return EnderecoDasPaginasWeb.PAGINA_PRINCIPAL_ADMIN;
			}
		}
		return null;
	}
}
