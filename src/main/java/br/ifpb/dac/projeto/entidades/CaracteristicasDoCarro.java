package br.ifpb.dac.projeto.entidades;

import javax.persistence.Embeddable;

@Embeddable
public class CaracteristicasDoCarro {
	
	private int numeroDePortas;
	
	private int quantidadeDePessoas;
	
	private TransmissaoDoCarro transmissao;
	
	private Booleanos vidroEletrico;
	
	private Booleanos travaEletrica;
	
	private Booleanos arCondicionado;
	
	private Booleanos direcaoHidraulica;
	
	private Booleanos abs;
	
	private Booleanos airBag;
	
	private int malaGrande;
	
	private int malaPequena;
	
	public CaracteristicasDoCarro() {}

	
	public int getNumeroDePortas() {
		return numeroDePortas;
	}
	public void setNumeroDePortas(int numeroDePortas) {
		this.numeroDePortas = numeroDePortas;
	}
	public TransmissaoDoCarro getTransmissao() {
		return transmissao;
	}
	public void setTransmissao(TransmissaoDoCarro transmissao) {
		this.transmissao = transmissao;
	}
	public Booleanos getVidroEletrico() {
		return vidroEletrico;
	}
	public void setVidroEletrico(Booleanos vidroEletrico) {
		this.vidroEletrico = vidroEletrico;
	}
	public Booleanos getTravaEletrica() {
		return travaEletrica;
	}
	public void setTravaEletrica(Booleanos travaEletrica) {
		this.travaEletrica = travaEletrica;
	}
	public Booleanos getArCondicionado() {
		return arCondicionado;
	}
	public void setArCondicionado(Booleanos arCondicionado) {
		this.arCondicionado = arCondicionado;
	}
	public Booleanos getDirecaoHidraulica() {
		return direcaoHidraulica;
	}
	public void setDirecaoHidraulica(Booleanos direcaoHidraulica) {
		this.direcaoHidraulica = direcaoHidraulica;
	}
	public Booleanos getAbs() {
		return abs;
	}
	public void setAbs(Booleanos abs) {
		this.abs = abs;
	}
	public Booleanos getAirBag() {
		return airBag;
	}
	public void setAirBag(Booleanos airBag) {
		this.airBag = airBag;
	}
	public int getQuantidadeDePessoas() {
		return quantidadeDePessoas;
	}
	public void setQuantidadeDePessoas(int quantidadeDePessoas) {
		this.quantidadeDePessoas = quantidadeDePessoas;
	}
	public int getMalaGrande() {
		return malaGrande;
	}
	public void setMalaGrande(int malaGrande) {
		this.malaGrande = malaGrande;
	}
	public int getMalaPequena() {
		return malaPequena;
	}
	public void setMalaPequena(int malaPequena) {
		this.malaPequena = malaPequena;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abs == null) ? 0 : abs.hashCode());
		result = prime * result + ((airBag == null) ? 0 : airBag.hashCode());
		result = prime * result + ((arCondicionado == null) ? 0 : arCondicionado.hashCode());
		result = prime * result + ((direcaoHidraulica == null) ? 0 : direcaoHidraulica.hashCode());
		result = prime * result + malaGrande;
		result = prime * result + malaPequena;
		result = prime * result + numeroDePortas;
		result = prime * result + quantidadeDePessoas;
		result = prime * result + ((transmissao == null) ? 0 : transmissao.hashCode());
		result = prime * result + ((travaEletrica == null) ? 0 : travaEletrica.hashCode());
		result = prime * result + ((vidroEletrico == null) ? 0 : vidroEletrico.hashCode());
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
		CaracteristicasDoCarro other = (CaracteristicasDoCarro) obj;
		if (abs != other.abs)
			return false;
		if (airBag != other.airBag)
			return false;
		if (arCondicionado != other.arCondicionado)
			return false;
		if (direcaoHidraulica != other.direcaoHidraulica)
			return false;
		if (malaGrande != other.malaGrande)
			return false;
		if (malaPequena != other.malaPequena)
			return false;
		if (numeroDePortas != other.numeroDePortas)
			return false;
		if (quantidadeDePessoas != other.quantidadeDePessoas)
			return false;
		if (transmissao != other.transmissao)
			return false;
		if (travaEletrica != other.travaEletrica)
			return false;
		if (vidroEletrico != other.vidroEletrico)
			return false;
		return true;
	}
}