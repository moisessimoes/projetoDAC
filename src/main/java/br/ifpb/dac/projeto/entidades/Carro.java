package br.ifpb.dac.projeto.entidades;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Carro implements Indentificavel {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String nome;
	
	@Column
	@Enumerated(EnumType.STRING)
	private GruposDeCarro categoria;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Marcas marca;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Cores cor;
	
	private int ano;
	
	private double valorDaDiaria;
	
	private String placa;
	
	@Embedded
	private CaracteristicasDoCarro caracteristicas;
	
	
	public Carro() {}


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
	public GruposDeCarro getCategoria() {
		return categoria;
	}
	public void setCategoria(GruposDeCarro categoria) {
		this.categoria = categoria;
	}
	public Marcas getMarca() {
		return marca;
	}
	public void setMarca(Marcas marca) {
		this.marca = marca;
	}
	public Cores getCor() {
		return cor;
	}
	public void setCor(Cores cor) {
		this.cor = cor;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public double getValorDaDiaria() {
		return valorDaDiaria;
	}
	public void setValorDaDiaria(double valorDaDiaria) {
		this.valorDaDiaria = valorDaDiaria;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public CaracteristicasDoCarro getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(CaracteristicasDoCarro caracteristicas) {
		this.caracteristicas = caracteristicas;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + ((caracteristicas == null) ? 0 : caracteristicas.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorDaDiaria);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Carro other = (Carro) obj;
		if (ano != other.ano)
			return false;
		if (caracteristicas == null) {
			if (other.caracteristicas != null)
				return false;
		} else if (!caracteristicas.equals(other.caracteristicas))
			return false;
		if (categoria != other.categoria)
			return false;
		if (cor != other.cor)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (marca != other.marca)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		if (Double.doubleToLongBits(valorDaDiaria) != Double.doubleToLongBits(other.valorDaDiaria))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Carro [id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", marca=" + marca + ", cor=" + cor
				+ ", ano=" + ano + ", valorDaDiaria=" + valorDaDiaria + ", placa=" + placa + ", caracteristicas="
				+ caracteristicas + "]";
	}


	public Carro clone() {
		
		Carro clone = new Carro();
		
		clone.setId(id);
		clone.setNome(nome);
		clone.setCategoria(categoria);
		clone.setMarca(marca);
		clone.setCor(cor);
		clone.setAno(ano);
		clone.setValorDaDiaria(valorDaDiaria);
		clone.setPlaca(placa);
		clone.setCaracteristicas(caracteristicas);
		
		return clone;
	}
}
