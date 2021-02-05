package br.ifpb.dac.projeto.entidades;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Agencia implements Indentificavel {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String nome;
	
	private String cnpj;
	
	private String telefone;
	
	@Embedded
	private Endereco endereco;
	
	@Embedded
	private HorariosDeFuncionamento horarios;
	
	
	public Agencia() {
		
	}
	
	public Agencia(String nome, String telefone, Endereco address) {
		
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = address;
	}
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
	public HorariosDeFuncionamento getHorarios() {
		return horarios;
	}
	public void setHorarios(HorariosDeFuncionamento horarios) {
		this.horarios = horarios;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((horarios == null) ? 0 : horarios.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
		Agencia other = (Agencia) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (horarios == null) {
			if (other.horarios != null)
				return false;
		} else if (!horarios.equals(other.horarios))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Agencia [id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + ", telefone=" + telefone + ", endereco="
				+ endereco + ", horarios=" + horarios + "]";
	}

	@Override
	public Agencia clone() {
		
		Agencia clone = new Agencia();
		
		clone.setId(id);
		clone.setNome(nome);
		clone.setCnpj(cnpj);
		clone.setTelefone(telefone);
		clone.setEndereco(endereco);
		clone.setHorarios(horarios);;
		
		return clone;
	}
}
