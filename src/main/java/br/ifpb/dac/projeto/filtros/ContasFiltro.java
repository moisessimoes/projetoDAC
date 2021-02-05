package br.ifpb.dac.projeto.filtros;

import br.ifpb.dac.projeto.entidades.Administrador;
import br.ifpb.dac.projeto.entidades.Cliente;
import br.ifpb.dac.projeto.entidades.Grupos;

public class ContasFiltro implements Filtro {

	private static final long serialVersionUID = 1L;
	
	private Grupos grupo;
	
	private String email;
	
	private String senha;
	
	private Cliente cliente;
	
	private Administrador admin;
	
	public ContasFiltro() {
		// TODO Auto-generated constructor stub
	}
	
	public Grupos getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupos grupo) {
		this.grupo = grupo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Administrador getAdmin() {
		return admin;
	}
	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}
	
	public boolean isEmpty() {
		
		if(this.email.trim().isEmpty()) {
			return false;
		}
		if(this.senha.trim().isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ContasFiltro [grupo=" + grupo + ", email=" + email + ", senha=" + senha + ", cliente=" + cliente
				+ ", admin=" + admin + "]";
	}
}
