package br.ifpb.dac.projeto.calculosServices;

import java.util.Date;

import org.joda.time.DateTime;

import br.ifpb.dac.projeto.logicaNegocio.CalculosDoAluguelDoCarro;

public interface CalculosService {
	
	public CalculosDoAluguelDoCarro calculos();
	
	public int calcularQtdDeDiarias(Date dataRetirada, Date dataDevolucao);
	
	public int gerarCodigoDaReserva();
	
	public double valorDeCoberturaDoCarro(int qtdDiarias);
	
	public double valorDeCoberturaParaTerceiros(int qtdDiarias);
	
	public double valorDeGPS(int qtdDiarias);
	
	public double calcularValorTaxa(double valorPrevisto);
	
	public double calcularValorPrevisto(double valorDiaria, Date dataRetirada, Date dataDevolucao);
	
	public double somarValorPrevistoComAsTarifas(double valorDiaria, Date dataRetirada, Date dataDevolucao, double[] tarifas);
	
	public double somarTotalPrevistoComTaxa(double valorPrevistoComTarifas);
	
	public String mostrarValorCoberturaCarroFormatado();
	
	public String mostrarValorCoberturaTerceirosFormatado();
	
	public String mostrarValorGPSFormatado();
	
	public String mostrarValorCoberturaCarroAlterado(int diarias);
	
	public String mostrarValorCoberturaTerceirosAlterado(int diarias);
	
	public String mostrarValorGPSAlterado(int diarias);
	
	public boolean carroIndisponivel(DateTime dataRetirada, DateTime dataDevolucao, Date dataRetiradaCarroAlugado, Date dataDevolucaoCarroAlugado);
	
}
