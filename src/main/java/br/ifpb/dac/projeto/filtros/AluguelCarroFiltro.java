package br.ifpb.dac.projeto.filtros;

import java.util.Date;

import br.ifpb.dac.projeto.exceptions.DACExceptions;

public class AluguelCarroFiltro implements Filtro {

	private static final long serialVersionUID = 1L;
	
	private Date dataRetirada;
	
	private String horaRetirada;
	
	private Date dataDevolucao;
	
	private String horaDevolucao;
	
	private int quantidadeDeDiarias;
	
	private double tarifaCoberturaCarro;
	
	private double tarifaCoberturaTerceiros;
	
	private double tarifaGPS;
	
	private double valorTotalPrevisto;
	
	private int codigoDaReserva;
	
	
	public AluguelCarroFiltro() {
		// TODO Auto-generated constructor stub
	}


	public Date getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public String getHoraRetirada() {
		return horaRetirada;
	}
	public void setHoraRetirada(String horaRetirada) {
		this.horaRetirada = horaRetirada;
	}
	public Date getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public String getHoraDevolucao() {
		return horaDevolucao;
	}
	public void setHoraDevolucao(String horaDevolucao) {
		this.horaDevolucao = horaDevolucao;
	}
	public int getQuantidadeDeDiarias() {
		return quantidadeDeDiarias;
	}
	public void setQuantidadeDeDiarias(int quantidadeDeDiarias) {
		this.quantidadeDeDiarias = quantidadeDeDiarias;
	}
	public double getValorTotalPrevisto() {
		return valorTotalPrevisto;
	}
	public void setValorTotalPrevisto(double valorTotalPrevisto) {
		this.valorTotalPrevisto = valorTotalPrevisto;
	}
	public double getTarifaCoberturaCarro() {
		return tarifaCoberturaCarro;
	}
	public void setTarifaCoberturaCarro(double tarifaCoberturaCarro) {
		this.tarifaCoberturaCarro = tarifaCoberturaCarro;
	}
	public double getTarifaCoberturaTerceiros() {
		return tarifaCoberturaTerceiros;
	}
	public void setTarifaCoberturaTerceiros(double tarifaCoberturaTerceiros) {
		this.tarifaCoberturaTerceiros = tarifaCoberturaTerceiros;
	}
	public double getTarifaGPS() {
		return tarifaGPS;
	}
	public void setTarifaGPS(double tarifaGPS) {
		this.tarifaGPS = tarifaGPS;
	}
	public int getCodigoDaReserva() {
		return codigoDaReserva;
	}
	public void setCodigoDaReserva(int codigoDaReserva) {
		this.codigoDaReserva = codigoDaReserva;
	}


	public boolean isEmpty() {
		
		if (this.dataRetirada != null) {
			return false;
		}
		if (this.dataDevolucao != null) {
			return false;
		}

		return true;
	}
	
	
	@Override
	public String toString() {
		return "AluguelCarroFiltro [dataRetirada=" + dataRetirada + ", horaRetirada=" + horaRetirada
				+ ", dataDevolucao=" + dataDevolucao + ", horaDevolucao=" + horaDevolucao + ", quantidadeDeDiarias="
				+ quantidadeDeDiarias + ", tarifaCoberturaCarro=" + tarifaCoberturaCarro + ", tarifaCoberturaTerceiros="
				+ tarifaCoberturaTerceiros + ", tarifaGPS=" + tarifaGPS + ", valorTotalPrevisto=" + valorTotalPrevisto
				+ ", codigoDaReserva=" + codigoDaReserva + "]";
	}


	public void validate() throws DACExceptions {
		if (this.dataRetirada != null && this.dataDevolucao != null) {
			if (this.dataRetirada.after(this.dataDevolucao)) {
				throw new DACExceptions("'A data de retirada' deve ser antes da 'data de devolução'!");
			}
		}
	}
}
