package br.ifpb.dac.projeto.beans.aluguel;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import br.ifpb.dac.projeto.agenciaServices.AgenciaService;
import br.ifpb.dac.projeto.aluguelServices.AluguelService;
import br.ifpb.dac.projeto.beans.AbstractBean;
import br.ifpb.dac.projeto.beans.EnderecoDasPaginasWeb;
import br.ifpb.dac.projeto.calculosServices.CalculosService;
import br.ifpb.dac.projeto.carroServices.CarroService;
import br.ifpb.dac.projeto.clienteServices.ClienteService;
import br.ifpb.dac.projeto.entidades.Agencia;
import br.ifpb.dac.projeto.entidades.Reserva;
import br.ifpb.dac.projeto.entidades.Carro;
import br.ifpb.dac.projeto.entidades.Cliente;
import br.ifpb.dac.projeto.entidades.EnderecoReserva;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.logicaNegocio.Horarios;

@Named
@ViewScoped
public class NovaReservaBean extends AbstractBean implements Serializable {
	
	//Classe bean responsavel salvar e editar uma reserva feita pelo cliente

	private static final long serialVersionUID = 1L;
	
	private Reserva aluguel;
	
	private Reserva selectedRent; 
	
	private Cliente cliente;
	
	private Carro carro;
	
	private EnderecoReserva enderecoReserva;
	
	private List<Agencia> agencias; 
	
	private String idCarroEscolhido;
	
	private String idClienteLogado;
	
	private String idReserva;
	
	private int codigoAux;
	
	private boolean addCoberturaCarro;
	
	private boolean addCoberturaTerceiros;
	
	private boolean addGPS;
	
	private boolean receberEmail;
		
	@Inject
	private ClienteService clientService;
	
	@Inject
	private AluguelService aluguelService;
	
	@Inject
	private CarroService carService;
	
	@Inject
	private AgenciaService agencyService;
	
	@Inject
	private CalculosService calculosService;
	
	@EJB
	private Horarios horarios;
	
	
	@PostConstruct
	public void postConstruct() {
		
		//Nessas três linhas abaixo, estou pegando o id do cliente e do carro escolhido na pagina principal do cliente, que é enviada pela URL para a página finalizar reserva.
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();    
		idCarroEscolhido = request.getParameter("car");
		idClienteLogado = request.getParameter("client");
		
		inicializarAgencias();
	}
	
	
	private void inicializarAgencias() {
		
		try {
			agencias = agencyService.getTodasAsAgencias();
			
		} catch (DACExceptions e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	
	public void iniciar() {
		
		try {
			
			if(aluguel == null) {
				
				enderecoReserva = new EnderecoReserva();
				
				aluguel = new Reserva();
				
				aluguel.setEnderecoReserva(enderecoReserva);
				
				carro = carService.getCarroPeloID(Integer.parseInt(getIdCarroEscolhido()));
				
				cliente = clientService.getClientePeloID(Integer.parseInt(getIdClienteLogado()));
				
				aluguel.setCliente(cliente);
				
				aluguel.setCarro(carro);
				
				codigoAux = getCode();
				
				aluguel.setCodigoDaReserva(codigoAux);
				
			} else {
				
				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				idReserva = request.getParameter("aluguel");
				
				selectedRent = aluguelService.getReservaPeloID(Integer.parseInt(idReserva));
				
				aluguel.setCarro(selectedRent.getCarro());
			}
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public String salvarReserva() throws DACExceptions {
		
		try {
			
			if(isEdicaoReserva()) {
				
				aluguelService.atualizarReserva(aluguel);
				
				reportarMensagemDeSucesso("Os dados da reserva foram atualizados!");
				
				return EnderecoDasPaginasWeb.PAGINA_PRINCIPAL_CLIENTE;
				
			} else {
				
				if(verificarDisponibilidadeSegundaEtapa()) {
					
					aluguelService.salvarReserva(aluguel);
					
					reportarMensagemDeSucesso("A reserva foi registrada com sucesso!");
					
					return EnderecoDasPaginasWeb.PAGINA_PRINCIPAL_CLIENTE;
				} 
			}
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
			e.printStackTrace();
			return null;
		}
		
		reportarMensagemDeErro("Desculpe! Não foi possível concluir esta reserva, pois o carro em questão já está alugado entre as datas escolhidas.");
		
		return EnderecoDasPaginasWeb.PAGINA_PRINCIPAL_CLIENTE;
	}
	
	
	
	public boolean verificarDisponibilidadePrimeiraEtapa() { 
		
		//Esse metodo é chamada logo quando o usuario clicar em verificar disponibilidade, será exibida uma mensagem informando sobre a disponibilidade do carro.
		
		
		try {
			
			DateTime dataRetirada = new DateTime(aluguel.getEnderecoReserva().getDataRetirada());
			
			DateTime dataDevolucao = new DateTime(aluguel.getEnderecoReserva().getDataDevolucao());
			
			Reserva rent = aluguelService.carroAlugado(aluguel.getCarro());
			
			if(rent != null) {
				
				if(calculosService.carroIndisponivel(dataRetirada, dataDevolucao, rent.getEnderecoReserva().getDataRetirada(), rent.getEnderecoReserva().getDataDevolucao())) {
					
					reportarMensagemDeErro("Este carro já está reservado no período entre " + formatarData(rent.getEnderecoReserva().getDataRetirada()) + " e " + formatarData(rent.getEnderecoReserva().getDataDevolucao()) +". Escolha outras datas de retirada e devolução!");
					
					return false;
				} 
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		reportarMensagemDeSucesso("Carro Disponível!");
		
		return true;
	}
	
	
	public boolean verificarDisponibilidadeSegundaEtapa() { //Esse metodo é chamado quando o usuario clicar em finalizar a reserva, será feita novamente a verificação de disponibilidade do carro.
		
		try {
			
			DateTime dataRetirada = new DateTime(aluguel.getEnderecoReserva().getDataRetirada());
			
			DateTime dataDevolucao = new DateTime(aluguel.getEnderecoReserva().getDataDevolucao());
			
			Reserva rent = aluguelService.carroAlugado(aluguel.getCarro());
			
			if(rent != null) {
				
				if(calculosService.carroIndisponivel(dataRetirada, dataDevolucao, rent.getEnderecoReserva().getDataRetirada(), rent.getEnderecoReserva().getDataDevolucao())) {
					
					return false;
				} 
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	
	public List<Reserva> listarTodasAsReservas() { //Esse metodo busca por todas as reservas
		
		try {
			return aluguelService.getTodasAsReservas();
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
	}
	
	
	//------------------Retornam uma lista dos horários específicos dependendo da agência escolhida (retirada e devolução).--
	
	public String[] listarHorariosAgenciaRetirada() {
		
		return horarios.listaDeHorariosDasAgencias(getAgenciaRetiradaHorarioAbertura(), getAgenciaRetiradaHorarioFechamento());
	}
	
	public String[] listarHorariosAgenciaDevolucao() {
		
		return horarios.listaDeHorariosDasAgencias(getAgenciaDevolucaoHorarioAbertura(), getAgenciaDevolucaoHorarioFechamento());
	}
	
	
	//-----------------------------------------------------------------------------------------------------------------------
	
	public Agencia buscarAgencia(int id) { //Esse metodo busca uma agencia pelo ID
		
		try {
			return agencyService.getAgenciaPeloID(id);
			
		} catch (DACExceptions e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
	}

	
	public int calcularQuantidadeDeDiarias() { //Calcula e retorna a quantidade de diárias com base nas datas de retirada e devolução.
		
		aluguel.setQuantidadeDeDiarias(calculosService.calcularQtdDeDiarias(aluguel.getEnderecoReserva().getDataRetirada(), aluguel.getEnderecoReserva().getDataDevolucao()));
		
		return calculosService.calcularQtdDeDiarias(aluguel.getEnderecoReserva().getDataRetirada(), aluguel.getEnderecoReserva().getDataDevolucao());
		
	}
	
	
	public double calcularValorPrevisto() { //Esse valor retornado é apenas o valor da diária do carro vezes a quantidade de diárias. Não tem adição de tarifas.
		
		return calculosService.calcularValorPrevisto(aluguel.getCarro().getValorDaDiaria(), aluguel.getEnderecoReserva().getDataRetirada(), aluguel.getEnderecoReserva().getDataDevolucao());
	}
	
	
	public double calcularValorPrevistoComTarifas() { //Esse valor retornado é o do metódo acima mais o valor das tarifas caso o cliente tenha escolhido alguma.
		
		double[] tarifasEscolhidas = new double[3];
		
		if(isAddCoberturaCarro()) {
			
			tarifasEscolhidas[0] = valorCoberturaCarroAtual();
			
		} if(isAddCoberturaTerceiros()) {
			
			tarifasEscolhidas[1] = valorCoberturaTerceirosAtual();
			
		} if(isAddGPS()) {
			
			tarifasEscolhidas[2] = valorGPSAtual();
		}
		
		return calculosService.somarValorPrevistoComAsTarifas(aluguel.getCarro().getValorDaDiaria(), aluguel.getEnderecoReserva().getDataRetirada(), aluguel.getEnderecoReserva().getDataDevolucao(), tarifasEscolhidas);
	}
	
	
	public String calcularValorPrevistoTotalComTaxa() {
		
		aluguel.setValorTotalPrevisto(calculosService.somarTotalPrevistoComTaxa(calcularValorPrevistoComTarifas()));
		
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		
		return String.valueOf(nf.format(calculosService.somarTotalPrevistoComTaxa(calcularValorPrevistoComTarifas())));
	}
	
	
	//Metódos booleanos de controle 
	
	public boolean isEdicaoReserva() {
		
		return aluguel != null && aluguel.getId() != null;
	}
	
	public boolean isAgenciaRetiradaOk() {
		
		return buscarAgencia(aluguel.getEnderecoReserva().getIdAgenciaRetirada()) != null && aluguel.getEnderecoReserva().getIdAgenciaRetirada() != 0;
		
	}
	
	public boolean isAgenciaDevolucaoOk() {
		
		return buscarAgencia(aluguel.getEnderecoReserva().getIdAgenciaDevolucao()) != null && aluguel.getEnderecoReserva().getIdAgenciaDevolucao() != 0;
		
	}
	
	public boolean isDataRetiradaOk() {
		
		return aluguel.getEnderecoReserva().getDataRetirada() != null;
	}
	
	public boolean isDataDevolucaoOk() {
		
		return aluguel.getEnderecoReserva().getDataDevolucao() != null;
	}
	
	public boolean isCoberturaCarroOk() {
		
		return isAddCoberturaCarro() == true;
	}
	
	public boolean isCoberturaTerceirosOk() {
		
		return isAddCoberturaTerceiros() == true;
	}
	
	public boolean isGPSOk() {
		
		return isAddGPS() == true;
	}
	
	
	
	
	//---------------------------------------------------------------------------------
	
	
	
	
	//-----------------Retornam os horários de abetura e fechamento das agências de retirada e devolução.
	
	public String getAgenciaRetiradaHorarioAbertura() {
		
		return buscarAgencia(aluguel.getEnderecoReserva().getIdAgenciaRetirada()).getHorarios().getHorarioDeAbertura();
	}
	
	public String getAgenciaRetiradaHorarioFechamento() {
		
		return buscarAgencia(aluguel.getEnderecoReserva().getIdAgenciaRetirada()).getHorarios().getHorarioDeFechamento();
	}
	
	
	
	public String getAgenciaDevolucaoHorarioAbertura() {
		
		return buscarAgencia(aluguel.getEnderecoReserva().getIdAgenciaDevolucao()).getHorarios().getHorarioDeAbertura();
	}
	
	public String getAgenciaDevolucaoHorarioFechamento() {
		
		return buscarAgencia(aluguel.getEnderecoReserva().getIdAgenciaDevolucao()).getHorarios().getHorarioDeFechamento();
	}
	//--------------------------------------------------------------------------------------------------

	
	
	
	
	//----------Aqui são todos os metodos para adicionar ou remover as tarifas--------------------------
	
	public double valorCoberturaCarroAtual() {
		
		aluguel.setTarifaCoberturaCarro(calculosService.valorDeCoberturaDoCarro(calcularQuantidadeDeDiarias()));
		
		return calculosService.valorDeCoberturaDoCarro(calcularQuantidadeDeDiarias());
	}
	
	public double valorCoberturaTerceirosAtual() {
		
		aluguel.setTarifaCoberturaTerceiros(calculosService.valorDeCoberturaParaTerceiros(calcularQuantidadeDeDiarias()));
		
		return calculosService.valorDeCoberturaParaTerceiros(calcularQuantidadeDeDiarias());
	}
	
	public double valorGPSAtual() {
		
		aluguel.setTarifaGPS(calculosService.valorDeGPS(calcularQuantidadeDeDiarias()));
		
		return calculosService.valorDeGPS(calcularQuantidadeDeDiarias());
	}
	
	
	public String calcularTaxa() {
		
		aluguel.setTaxa(calculosService.calcularValorTaxa(calcularValorPrevistoComTarifas()));
		
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		
		return String.valueOf(nf.format(calculosService.calcularValorTaxa(calcularValorPrevistoComTarifas())));
	}
	
	
	public int getCode() {
		
		return calculosService.gerarCodigoDaReserva();
	}
	
	//----------------------------------------------------------------------------------------------
	
	public String valorCoberturaCarroAtualFormatado() {
		
		return calculosService.mostrarValorCoberturaCarroAlterado(calcularQuantidadeDeDiarias());
	}
	
	public String valorCoberturaTerceirosAtualFormatado() {
		
		return calculosService.mostrarValorCoberturaTerceirosAlterado(calcularQuantidadeDeDiarias());
	}
	
	public String valorGPSAtualFormatado() {
		
		return calculosService.mostrarValorGPSAlterado(calcularQuantidadeDeDiarias());
	}
	
	
	public String formatarValoresMonetarios(double valor) {
		
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		
		return String.valueOf(nf.format(valor));
	}
	
	
	
	//----------------------------------------------------------------------------------------------
	

	public Reserva getAluguel() {
		return aluguel;
	}
	
	public void setAluguel(Reserva aluguel) {
		this.aluguel = aluguel;
	}
	
	public Reserva getSelectedRent() {
		return selectedRent;
	}
	
	public void setSelectedRent(Reserva selectedRent) {
		this.selectedRent = selectedRent;
	}
	
	public AluguelService getAluguelService() {
		return aluguelService;
	}
	
	public void setAluguelService(AluguelService aluguelService) {
		this.aluguelService = aluguelService;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteService getClientService() {
		return clientService;
	}
	
	public void setClientService(ClienteService clientService) {
		this.clientService = clientService;
	}
	
	public Carro getCarro() {
		return carro;
	}
	
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	
	public List<Agencia> getAgencias() {
		return agencias;
	}
	
	public void setAgencias(List<Agencia> agencias) {
		this.agencias = agencias;
	}
	
	public Horarios getHorarios() {
		return horarios;
	}
	
	public void setHorarios(Horarios horarios) {
		this.horarios = horarios;
	}
	
	public CarroService getCarService() {
		return carService;
	}
	
	public void setCarService(CarroService carService) {
		this.carService = carService;
	}
	
	public AgenciaService getAgencyService() {
		return agencyService;
	}
	
	public void setAgencyService(AgenciaService agencyService) {
		this.agencyService = agencyService;
	}
	
	public boolean isAddCoberturaCarro() {
		return addCoberturaCarro;
	}
	
	public void setAddCoberturaCarro(boolean addCoberturaCarro) {
		this.addCoberturaCarro = addCoberturaCarro;
	}
	
	public boolean isAddCoberturaTerceiros() {
		return addCoberturaTerceiros;
	}
	
	public void setAddCoberturaTerceiros(boolean addCoberturaTerceiros) {
		this.addCoberturaTerceiros = addCoberturaTerceiros;
	}
	
	public boolean isAddGPS() {
		return addGPS;
	}
	
	public void setAddGPS(boolean addGPS) {
		this.addGPS = addGPS;
	}
	
	public CalculosService getCalculosService() {
		return calculosService;
	}
	
	public void setCalculosService(CalculosService calculosService) {
		this.calculosService = calculosService;
	}
	
	public String getIdCarroEscolhido() {
		return idCarroEscolhido;
	}
	
	public void setIdCarroEscolhido(String idCarroEscolhido) {
		this.idCarroEscolhido = idCarroEscolhido;
	}
	
	public String getIdClienteLogado() {
		return idClienteLogado;
	}
	
	public void setIdClienteLogado(String idClienteLogado) {
		this.idClienteLogado = idClienteLogado;
	}
	
	public EnderecoReserva getEnderecoReserva() {
		return enderecoReserva;
	}
	
	public void setEnderecoReserva(EnderecoReserva enderecoReserva) {
		this.enderecoReserva = enderecoReserva;
	}
	
	public int getCodigoAux() {
		return codigoAux;
	}
	
	public void setCodigoAux(int codigoAux) {
		this.codigoAux = codigoAux;
	}
	
	public String getIdReserva() {
		return idReserva;
	}
	
	public void setIdReserva(String idReserva) {
		this.idReserva = idReserva;
	}
	
	public boolean isReceberEmail() {
		return receberEmail;
	}
	
	public void setReceberEmail(boolean receberEmail) {
		this.receberEmail = receberEmail;
	}
}
