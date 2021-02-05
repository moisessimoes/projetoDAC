package br.ifpb.dac.projeto.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_contas", uniqueConstraints = {@UniqueConstraint(name = "uk__tb_contas__email", columnNames = { "email" }) })
public class ContaNoSistema implements Indentificavel {
	
	//Essa entidade servira de ponte para realizaçao de login no sistema atraves do JAAS, guardando emails e senhas tanto de clientes, quanto de administradores. 

	
	@Column(name = "user_group")
	@Enumerated(EnumType.STRING)
	private Grupos grupo;
	
	@Id
	private String email;
	
	private String senha;
	
	@OneToOne
	private Cliente cliente;
	
	@OneToOne
	private Administrador administrador;
	
	public ContaNoSistema() {}
	
	public ContaNoSistema(Grupos group, String email, String password) {
		
		this.grupo = group;
		this.email = email;
		this.senha = password;
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
	public Administrador getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((administrador == null) ? 0 : administrador.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaNoSistema other = (ContaNoSistema) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (administrador == null) {
			if (other.administrador != null)
				return false;
		} else if (!administrador.equals(other.administrador))
			return false;
		if (grupo != other.grupo)
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "ContasNoSistema [grupo=" + grupo + ", email=" + email + ", senha=" + senha +  "]";
	}

	@Override
	public ContaNoSistema clone() {
		
		ContaNoSistema clone = new ContaNoSistema();
				
		clone.setGrupo(grupo);
		clone.setEmail(email);
		clone.setSenha(senha);
		clone.setCliente(cliente);
		clone.setAdministrador(administrador);
		
		return clone;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
