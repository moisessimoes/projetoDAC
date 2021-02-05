package br.ifpb.dac.projeto.filtros;

import br.ifpb.dac.projeto.entidades.Endereco;

public class AgenciaFiltro implements Filtro {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String telefone;
	
	private Endereco endereco;
	
	
	public AgenciaFiltro() {}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	public boolean isEmpty() {
		
		if(this.nome.trim().isEmpty()) {
			return false;
		} 
		if(this.telefone.trim().isEmpty()) {
			return false;
		}
		if(this.endereco.getCidade().trim().isEmpty()) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "AgenciaFiltro [nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco + "]";
	}
}
