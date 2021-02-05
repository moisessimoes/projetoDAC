package br.ifpb.dac.projeto.calculosServices;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;

import org.joda.time.DateTime;

import br.ifpb.dac.projeto.logicaNegocio.CalculosDoAluguelDoCarro;
import br.ifpb.dac.projeto.logicaNegocio.VerificarDisponibilidade;

public class ControleDosCalculosService implements Serializable, CalculosService {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private CalculosDoAluguelDoCarro calcular;
	
	@EJB
	private VerificarDisponibilidade check;

	@Override
	public int calcularQtdDeDiarias(Date dataRetirada, Date dataDevolucao) {
	
		return calcular.calcularQtdDeDiarias(dataRetirada, dataDevolucao);
	}

	@Override
	public int gerarCodigoDaReserva() {
		
		return calcular.gerarCodigoDaReserva();
	}

	@Override
	public double valorDeCoberturaDoCarro(int qtdDiarias) {
		
		return calcular.valorDeCoberturaDoCarro(qtdDiarias);
	}

	@Override
	public double valorDeCoberturaParaTerceiros(int qtdDiarias) {
		
		return calcular.valorDeCoberturaParaTerceiros(qtdDiarias);
	}

	@Override
	public double valorDeGPS(int qtdDiarias) {
		
		return calcular.valorDoGPS(qtdDiarias);
	}
	

	@Override
	public double calcularValorTaxa(double valorPrevisto) {
		
		return calcular.calcularValorTaxa(valorPrevisto);
	}

	@Override
	public double calcularValorPrevisto(double valorDiaria, Date dataRetirada, Date dataDevolucao) {
		
		return calcular.calcularValorPrevisto(valorDiaria, dataRetirada, dataDevolucao);
	}


	@Override
	public CalculosDoAluguelDoCarro calculos() {
		
		return calcular;
	}

	@Override
	public double somarValorPrevistoComAsTarifas(double valorDiaria, Date dataRetirada, Date dataDevolucao, double[] tarifas) {
		
		return calcular.somarValorPrevistoComAsTarifas(valorDiaria, dataRetirada, dataDevolucao, tarifas);
	}
	
	@Override
	public double somarTotalPrevistoComTaxa(double valorPrevistoComTarifas) {
		
		return calcular.somarTotalValorPrevistoComTaxa(valorPrevistoComTarifas);
	}

	@Override
	public String mostrarValorCoberturaCarroFormatado() {
		
		return calcular.valorCoberturaCarroFormatado();
	}

	@Override
	public String mostrarValorCoberturaTerceirosFormatado() {
		
		return calcular.valorCoberturaTerceirosFormatado();
	}

	@Override
	public String mostrarValorGPSFormatado() {
		
		return calcular.valorGPSFormatado();
	}

	@Override
	public String mostrarValorCoberturaCarroAlterado(int diarias) {
		
		return calcular.valorCoberturaCarroAlteradoFormatado(diarias);
	}

	@Override
	public String mostrarValorCoberturaTerceirosAlterado(int diarias) {
		
		return calcular.valorCoberturaTerceirosAlteradoFormatado(diarias);
	}

	@Override
	public String mostrarValorGPSAlterado(int diarias) {
		
		return calcular.valorGPSAlteradoFormatado(diarias);
	}

	@Override
	public boolean carroIndisponivel(DateTime dataRetirada, DateTime dataDevolucao, Date dataRetiradaCarroAlugado, Date dataDevolucaoCarroAlugado) {
		
		return check.carroIndisponivel(dataRetirada, dataDevolucao, dataRetiradaCarroAlugado, dataDevolucaoCarroAlugado);
	}
}
