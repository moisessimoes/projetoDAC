package br.ifpb.dac.projeto.beans.aluguel;

import java.io.Serializable;
import java.text.NumberFormat;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.dac.projeto.agenciaServices.AgenciaService;
import br.ifpb.dac.projeto.aluguelServices.AluguelService;
import br.ifpb.dac.projeto.beans.AbstractBean;
import br.ifpb.dac.projeto.entidades.Agencia;
import br.ifpb.dac.projeto.entidades.Reserva;
import br.ifpb.dac.projeto.exceptions.DACExceptions;

@Named
@ViewScoped
public class ReservasDoClienteBean extends AbstractBean implements Serializable {
	
	//Classe bean responsavel buscar uma reserva já feita pelo cliente

	private static final long serialVersionUID = 1L;
	
	private String codigoReserva;
	
	private Reserva reserva;
	
	@Inject
	private AluguelService aluguelService;
	
	@Inject
	private AgenciaService agencyService;
	
	
	public Reserva buscarReservaPeloCodigo() {
		
		try {
			
			if(validarCodigo()) {
				
				return aluguelService.getReservaPeloCodigo(Integer.parseInt(getCodigoReserva()));
			}
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}
	
	
	public boolean reservaExiste() {
		
		return buscarReservaPeloCodigo() != null;
	}
	
	
	public Agencia buscarAgencia(int id) {
		
		try {
			return agencyService.getAgenciaPeloID(id);
			
		} catch (DACExceptions e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
	}
	
	
	public boolean validarCodigo() {
		
		if(aluguelService.validarCodigoDeBusca(getCodigoReserva())) return true;
			
		
		reportarMensagemDeErro("Código " + "'"+getCodigoReserva()+"' " + " Inválido!");
		return false;
	}
	

	
	public String formatarValoresMonetarios(double valor) {
		
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		
		return String.valueOf(nf.format(valor));
	}
	
	
//	public void reenviarEmail() { NÃO FUNCIONA...
//	
//	EnviarEmail sendEmail = new EnviarEmail();
//	
//	boolean enviou = sendEmail.enviarEmail("Sua Reserva - " + buscarAgencia(buscarReservaPeloCodigo().getEnderecoReserva().getIdAgenciaRetirada()).getNome(), 
//			buscarReservaPeloCodigo().getCliente().getConta().getEmail(), mensagemDoEmail());
//	
//	if(enviou) {
//		
//		reportarMensagemDeSucesso("E-mail enviado com sucesso.");
//		
//	} else {
//		
//		reportarMensagemDeErro("Não foi possível enviar o e-mail!");
//	}
//	
//	reportarMensagemDeErro("Não foi possível enviar o e-mail!");
//}
	
	
//	public String mensagemDoEmail() {
//		
//		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
//	
//		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
//		
//		AluguelDoCarro reserva = buscarReservaPeloCodigo();
//		
//		String nomeCliente = reserva.getCliente().getNome();
//		String codigo = String.valueOf(reserva.getCodigoDaReserva());
//		String dataRetirada = String.valueOf(dataFormat.format(reserva.getEnderecoReserva().getDataRetirada()));
//		String horaRetirada = reserva.getEnderecoReserva().getHoraRetirada();
//		String dataDevolucao = String.valueOf(dataFormat.format(reserva.getEnderecoReserva().getDataDevolucao()));
//		String horaDevolucao = reserva.getEnderecoReserva().getHoraDevolucao();
//		String carro = reserva.getCarro().getNome();
//		String valorDiaria = String.valueOf(numberFormat.format(reserva.getCarro().getValorDaDiaria()));
//		String qtdDiarias = String.valueOf(reserva.getQuantidadeDeDiarias());
//		String tarifaCoberturaCarro = String.valueOf(numberFormat.format(reserva.getTarifaCoberturaCarro()));
//		String tarifaCoberturaTerceiros = String.valueOf(numberFormat.format(reserva.getTarifaCoberturaTerceiros()));
//		String tarifaGPS = String.valueOf(numberFormat.format(reserva.getTarifaGPS()));
//		String taxa = String.valueOf(numberFormat.format(reserva.getTaxa()));
//		String total = String.valueOf(numberFormat.format(reserva.getValorTotalPrevisto()));
		
//		String mensagem = null;
		
//		try {
			
//			 StringBuffer texto = new StringBuffer();
//			 
//			 String agengiaRetirada = agencyService.getAgenciaPeloID(reserva.getEnderecoReserva().getIdAgenciaRetirada()).getNome();
//			 String agenciaDevolucao = agencyService.getAgenciaPeloID(reserva.getEnderecoReserva().getIdAgenciaDevolucao()).getNome();
//			 String cidadeRetirada = agencyService.getAgenciaPeloID(reserva.getEnderecoReserva().getIdAgenciaRetirada()).getEndereco().getCidade();
//			 String cidadeDevolucao = agencyService.getAgenciaPeloID(reserva.getEnderecoReserva().getIdAgenciaDevolucao()).getEndereco().getCidade();
//			 
//			 String corpoMensagem = String.join(System.getProperty("line.separator"),
//					 "<h2>Olá, " + nomeCliente+"</h2>",
//					 "Informações da Reserva:<br/>",
//					 "CÓDIGO DA RESERVA: " + codigo+"<br/>",
//					 "RETIRADA: " + dataRetirada + " na agência " + agengiaRetirada + " às " + horaRetirada + " em " + cidadeRetirada+"<br/>",
//					 "DEVOLUÇÃO: " + dataDevolucao + " na agência " + agenciaDevolucao + " às " + horaDevolucao + " em " + cidadeDevolucao+"<br/>",
//					 "Carro: " + carro+"<br/>",
//					 "Valor da Diária: " + valorDiaria+"<br/>",
//					 "Número de Diárias: " + qtdDiarias+"<br/>",
//					 "Tarifa de Cobertura de Carro: " + tarifaCoberturaCarro+"<br/>",
//					 "Tarifa de Cobertura para Terceiros: " + tarifaCoberturaTerceiros+"<br/>",
//					 "Tarifa GPS: " + tarifaGPS+"<br/>",
//					 "Taxa (12%): " + taxa+"<br/>",
//					 "Total: " + total);
			 
			 
//				mensagem = "Olá, " + nomeCliente +"\n"
//				+ "\n"
//				+ "Sua reserva foi realizada com sucesso!\n"
//				+ "\n"
//				+ "\n"
//				+ "CÓDIGO DA RESERVA: " + codigo+"\n"
//				+ "\n"
//				+ "RETIRADA: " + dataRetirada + " na agência " + agengiaRetirada + " às " + horaRetirada + " em " + cidadeRetirada+".\n"
//				+ "\n"
//				+ "DEVOLUÇÃO: " + dataDevolucao + " na agência " + agenciaDevolucao + " às " + horaDevolucao + " em " + cidadeDevolucao+".\n"
//				+ "\n"
//				+ "Carro: " + carro+"\n"
//				+ "Valor da Diária: " + valorDiaria+"\n"
//				+ "\n"
//				+ "Número de Diárias: " + qtdDiarias+"\n"
//				+ "Tarifa de Cobertura de Carro: " + tarifaCoberturaCarro+"\n"
//				+ "Tarifa Cobertura para Terceiros: " + tarifaCoberturaTerceiros+"\n"
//				+ "Tarifa GPS: " + tarifaGPS+"\n"
//				+ "Taxa (12%): " + taxa+"\n"
//				+ "Total: " + total;
			 
			 
//			    texto.append("<h2>"+"Olá, " +nomeCliente+"</h2>");
//			    texto.append("Informações da sua Reserva:<br/>");
//			    texto.append("CÓDIGO DA RESERVA:");
//			    texto.append(codigo);
//			    texto.append("<br/>");
//			    texto.append("RETIRADA: ");
//			    texto.append(dataRetirada + " na agência " + agengiaRetirada + " às " + horaRetirada + " em " + cidadeRetirada);
//			    texto.append("<br/>");
//			    texto.append("DEVOLUÇÃO: ");
//			    texto.append(dataDevolucao + " na agência " + agenciaDevolucao + " às " + horaDevolucao + " em " + cidadeDevolucao);
//			    texto.append("<br/>");
//			    texto.append("Carro:");
//			    texto.append(carro);
//			    texto.append("<br/>");
//			    texto.append("Valor da Diária:");
//			    texto.append(valorDiaria);
//			    texto.append("<br/>");
//			    texto.append("Número de Diárias:");
//			    texto.append(qtdDiarias);
//			    texto.append("<br/>");
//			    texto.append("Tarifa de Cobertura de Carro:");
//			    texto.append(tarifaCoberturaCarro);
//			    texto.append("<br/>");
//			    texto.append("Tarifa Cobertura para Terceiros:");
//			    texto.append(tarifaCoberturaTerceiros);
//			    texto.append("<br/>");
//			    texto.append("Tarifa GPS:");
//			    texto.append(tarifaGPS);
//			    texto.append("<br/>");
//			    texto.append("Taxa (12%):");
//			    texto.append(taxa);
//			    texto.append("<br/>");
//			    texto.append("Total:");
//			    texto.append(total);
			    
//			return corpoMensagem;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			reportarMensagemDeErro("Houve um erro ao criar a mensagem do email!");
//			return null;
//		}
//	}
	
	
	public String getCodigoReserva() {
		return codigoReserva;
	}
	public void setCodigoReserva(String codigoReserva) {
		this.codigoReserva = codigoReserva;
	}
	public AluguelService getAluguelService() {
		return aluguelService;
	}
	public void setAluguelService(AluguelService aluguelService) {
		this.aluguelService = aluguelService;
	}
	public AgenciaService getAgencyService() {
		return agencyService;
	}
	public void setAgencyService(AgenciaService agencyService) {
		this.agencyService = agencyService;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
}
