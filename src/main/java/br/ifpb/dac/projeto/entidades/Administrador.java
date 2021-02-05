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
public class Administrador implements Indentificavel {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String nome;
	
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@OneToOne (mappedBy = "administrador", cascade = CascadeType.ALL)
	private ContaNoSistema conta;
	
	private String telefone1;

	
	public Administrador() {}
	
	
	public Administrador(String name, String cpf, Date nascimento, Sexo sexo, String fone1) {
		
		this.nome = name;
		this.cpf = cpf;
		this.dataNascimento = nascimento;
		this.sexo = sexo;
		this.telefone1 = fone1;
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
	
	
	/* Para evitar o erro do projeto passado, nos m�todos de hashCode e equals,
	 * o atributo 'conta' n�o � considerado. N�o lembro em espec�fico qual erro foi,
	 * mas lembro que deu erro relacionado a isso. Portanto, o hashCode  e equals s�o
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
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((telefone1 == null) ? 0 : telefone1.hashCode());
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
		Administrador other = (Administrador) obj;
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
		if (sexo != other.sexo)
			return false;
		if (telefone1 == null) {
			if (other.telefone1 != null)
				return false;
		} else if (!telefone1.equals(other.telefone1))
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "Administrador [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento
				+ ", sexo=" + sexo + ", conta=" + conta + ", telefone1=" + telefone1 + "]";
	}


	public Administrador clone() {
		
		Administrador clone = new Administrador();
		
		clone.setId(id);
		clone.setNome(nome);
		clone.setCpf(cpf);
		clone.setDataNascimento(dataNascimento);
		clone.setSexo(sexo);
		clone.setConta(conta);
		clone.setTelefone1(telefone1);
		
		return clone;
	}
}
