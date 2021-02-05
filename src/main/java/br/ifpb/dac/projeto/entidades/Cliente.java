package br.ifpb.dac.projeto.entidades;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cliente implements Indentificavel {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String nome;
	
	private String cpf;
	
	private String rg;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@OneToOne (mappedBy = "cliente", cascade = CascadeType.ALL)
	private ContaNoSistema conta;
	
	private String telefone;
	
	
	public Cliente() {}
	
	
	public Cliente(String name, String cpf, String rg, Date nascimento, Sexo sexo, String telephone) {
		
		this.nome = name;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = nascimento;
		this.sexo = sexo;
		this.telefone = telephone;
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
	/* Para evitar o erro do projeto passado, nos metodos de hashCode e equals,
	 * o atributo 'conta' nao é considerado. Nao lembro em especifico qual erro foi,
	 * mas lembro que deu erro relacionado a isso. Portanto, o hashCode  e equals sao
	 * de todos os atributos, menos de 'conta'.
	 * */
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
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
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (sexo != other.sexo)
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
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", dataNascimento="
				+ dataNascimento + ", sexo=" + sexo + ", conta=" + conta + ", telefone=" + telefone + "]";
	}


	@Override
	public Cliente clone() {
		
		Cliente clone = new Cliente();
		
		clone.setId(id);
		clone.setNome(nome);
		clone.setCpf(cpf);
		clone.setRg(rg);
		clone.setDataNascimento(dataNascimento);
		clone.setSexo(sexo);
		clone.setConta(conta);
		clone.setTelefone(telefone);
		
		return clone;
	}
}
