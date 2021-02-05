package br.ifpb.dac.projeto.filtros;

import br.ifpb.dac.projeto.entidades.CaracteristicasDoCarro;
import br.ifpb.dac.projeto.entidades.GruposDeCarro;
import br.ifpb.dac.projeto.entidades.Marcas;

public class CarroFiltro implements Filtro {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private GruposDeCarro categoria;
	
	private Marcas marca;
	
	private int ano;
	
	private double valorDaDiaria;
	
	private String placa;
	
	private CaracteristicasDoCarro caracteristicas;
	
	public CarroFiltro() {
		// TODO Auto-generated constructor stub
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
	
	public boolean isEmpty() {
		
		if(this.nome.trim().isEmpty()) {
			return false;
		} 
		if(this.categoria.toString().trim().isEmpty()) {
			return false;
		}
		if(this.marca.toString().trim().isEmpty()) {
			return false;
		}
		if(this.placa.trim().isEmpty()) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "CarroFiltro [nome=" + nome + ", categoria=" + categoria + ", marca=" + marca + ", ano=" + ano
				+ ", valorDaDiaria=" + valorDaDiaria + ", placa=" + placa + ", caracteristicas=" + caracteristicas
				+ "]";
	}
}
