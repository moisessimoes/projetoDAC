package br.ifpb.dac.projeto.logicaNegocio;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;

import javax.ejb.Stateless;

import org.joda.time.DateTime;
import org.joda.time.Days;

@Stateless
public class CalculosDoAluguelDoCarro implements Serializable {
	
	/*Esta classe, lida com todos os calculos necessarios para a realização de uma reserva pelo cliente.*/
	
	private static final long serialVersionUID = 1L;
	
	//Abaixo, estão os valores das tarifas.

	private double VALOR_COBERTURA_CARRO = 27.0;
	
	private double VALOR_COBERTURA_TERCEIROS = 10.0;
	
	private double VALOR_GPS = 15.0;
	
	private NumberFormat nf = NumberFormat.getCurrencyInstance();
	
	
	public CalculosDoAluguelDoCarro() {}
	
	
	
	public int calcularQtdDeDiarias(Date dataRetirada, Date dataDevolucao) { //Calcula a quantidade de diárias com base nas datas de retirada e devolução do carro.
		
		//DateTime pertence a API JODA Time.
		
		DateTime dataR = new DateTime(dataRetirada); //Convertendo um Date para DateTime.
		
		DateTime dataD = new DateTime(dataDevolucao); //Convertendo um Date para DateTime.
		
		Days dias = Days.daysBetween(dataR, dataD); //Retorna a diferença de dias entre duas datas.
		
		return dias.getDays();
	}
	
	
	
	
	public int gerarCodigoDaReserva() { //Gera um número aleatório que será o código da reserva
		
		return (int) (Math.random() * 1000000) + 100000;
	}
	
	
	
	
	
	//------------------Os metódos abaixo adicionam as tarifas ao valor da reserva caso o cliente queira essas tarifas
	
	public double valorDeCoberturaDoCarro(int qtdDiarias) { //Retorna o valor da cobertura do carro X número de diárias.
		
		return VALOR_COBERTURA_CARRO * qtdDiarias;
	}
	
	
	
	public double valorDeCoberturaParaTerceiros(int qtdDiarias) { //Retorna o valor da cobertura psrs terceiros X número de diárias.
		
		return VALOR_COBERTURA_TERCEIROS * qtdDiarias;
	}
	
	
	public double valorDoGPS(int qtdDiarias) { //Retorna o valor do GPS X número de diárias.
		
		return VALOR_GPS * qtdDiarias;
	}
	
	
	public double calcularValorTaxa(double valorPrevisto) { //Calcula o valor da taxa. (12%)
		
		return (valorPrevisto * 0.12);
	}
	
	
	public double calcularValorPrevisto(double valorDiaria, Date dataRetirada, Date dataDevolucao) { //Retorna o valor da diária do carro X número de diárias.
		
		return valorDiaria * calcularQtdDeDiarias(dataRetirada, dataDevolucao);
	}
	
	
	
	public double somarValorPrevistoComAsTarifas(double valorDiaria, Date dataRetirada, Date dataDevolucao, double[] tarifas) { //Soma o valor da diária do carro X número de diárias + as tarifas.
		
		double valorPrevisto = calcularValorPrevisto(valorDiaria, dataRetirada, dataDevolucao);
		
		for (int i = 0; i < tarifas.length; i++) {
			
			valorPrevisto += tarifas[i];
		}
		return valorPrevisto;
	}
	
	
	public double somarTotalValorPrevistoComTaxa(double valorPrevistoComTarifas) { //Soma o valor da diária do carro X número de diárias + as tarifas + taxa.
		
		double taxa = calcularValorTaxa(valorPrevistoComTarifas);
		
		double totalPrevisto = valorPrevistoComTarifas + taxa;
		
		return totalPrevisto;
	}
	
	
	
	
	//-------------------------------------------------------------------
	
	//Esse metodos formatam os valores originais das tarifas para R$. Fiz de inicio esses metodos, pois nao estava usando os conversores nas paginas JSF.
	
	public String valorCoberturaCarroFormatado() {
		
		return String.valueOf(nf.format(VALOR_COBERTURA_CARRO));
	}
	
	public String valorCoberturaTerceirosFormatado() {
		
		return String.valueOf(nf.format(VALOR_COBERTURA_TERCEIROS));
	}
	
	public String valorGPSFormatado() {
		
		return String.valueOf(nf.format(VALOR_GPS));
	}
	
	
	
	
	//---------------------------------------------------------------------
	
	//Esse metodos formatam os valores alterados das tarifas.Fiz de inicio esses metodos, pois nao estava usando os conversores nas paginas JSF.
	
	public String valorCoberturaCarroAlteradoFormatado(int qtdDiarias) {

		return String.valueOf(nf.format(valorDeCoberturaDoCarro(qtdDiarias)));
	}

	public String valorCoberturaTerceirosAlteradoFormatado(int qtdDiarias) {

		return String.valueOf(nf.format(valorDeCoberturaParaTerceiros(qtdDiarias)));
	}

	public String valorGPSAlteradoFormatado(int qtdDiarias) {

		return String.valueOf(nf.format(valorDoGPS(qtdDiarias)));
	}
	
	
	
	//---------------------------------------------------------------------

	public double getValorCoberturaCarro() {
		return VALOR_COBERTURA_CARRO;
	}
	public double getValorCoberturaTerceiros() {
		return VALOR_COBERTURA_TERCEIROS;
	}
	public double getValorGps() {
		return VALOR_GPS;
	}
}
