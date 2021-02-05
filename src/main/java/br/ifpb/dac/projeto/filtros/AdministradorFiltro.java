package br.ifpb.dac.projeto.filtros;

import java.util.Date;

import br.ifpb.dac.projeto.entidades.ContaNoSistema;
import br.ifpb.dac.projeto.entidades.Sexo;

public class AdministradorFiltro implements Filtro {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String cpf;
	
	private Date dataNascimento;
	
	private Sexo sexo;
	
	private ContaNoSistema conta;
	
	private String telefone1;
	
	public AdministradorFiltro() {
		// TODO Auto-generated constructor stub
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public ContaNoSistema getConta() {
		return conta;
	}
	public void setConta(ContaNoSistema conta) {
		this.conta = conta;
	}
	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	
	public boolean isEmpty() {
		
		if(this.nome.trim().isEmpty()) {
			return false;
		} 
		if(this.cpf.trim().isEmpty()) {
			return false;
		}
		if(this.conta.getEmail().trim().isEmpty()) {
			return false;
		}
		if(this.conta.getSenha().trim().isEmpty()) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "AdministradorFiltro [nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", sexo="
				+ sexo + ", conta=" + conta + ", telefone1=" + telefone1 + "]";
	}
}
