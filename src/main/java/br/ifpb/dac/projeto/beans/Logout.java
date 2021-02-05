package br.ifpb.dac.projeto.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class Logout extends AbstractBean implements Serializable {
	
	//Classe responsavel por fazer o logout do usuario da sessao.

	private static final long serialVersionUID = 1L;
	
	public void efetuarLogout() throws IOException, ServletException {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		ExternalContext ec = fc.getExternalContext();
		
		HttpSession session = (HttpSession) ec.getSession(false);
		session.invalidate();
		
		// XXX Chamada #logout() abaixo necessaria, pois: https://stackoverflow.com/a/26421775/4023351
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		
		request.logout();
		
		//ec.redirect(ec.getApplicationContextPath() + EnderecoPaginas.PAGINA_PRINCIPAL);
		ec.redirect(request.getContextPath());
	}
}
