package br.ifpb.dac.projeto.beans.carro;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.dac.projeto.beans.AbstractBean;
import br.ifpb.dac.projeto.beans.EnderecoDasPaginasWeb;
import br.ifpb.dac.projeto.carroServices.CarroService;
import br.ifpb.dac.projeto.entidades.CaracteristicasDoCarro;
import br.ifpb.dac.projeto.entidades.Carro;

@Named
@ViewScoped
public class NovoCarroBean extends AbstractBean implements Serializable {
	
	//Classe bean responsavel por salvar e editar os dados do carro.

	private static final long serialVersionUID = 1L;
	
	private Carro carro;
	
	private Carro selectedCar;
	
	@Inject
	private CarroService carroService;
	
	
	public void iniciar() {
		
		try {
			if(carro == null) {
				
				//criando novo carro
				
				CaracteristicasDoCarro featuresCar = new CaracteristicasDoCarro();
				
				carro = new Carro();
				
				carro.setCaracteristicas(featuresCar);
				
			} else {
				
				//caso seja edicao
				
				selectedCar = carroService.getCarroPeloID(carro.getId());
			}
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	
	public String salvarCarro() {
		
		try {
			
			if(isEdicaoCarro()) {
				
				carroService.atualizarCarro(carro);
				
				reportarMensagemDeSucesso("Dados do carro foram atualizados com sucesso!");
				
				return EnderecoDasPaginasWeb.PAGINA_LISTAR_CARROS;
				
			} else {
				
				carroService.salvarCarro(carro);
			}
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
		
		reportarMensagemDeSucesso("O carro '" + carro.getNome() + "', foi cadastrado com sucesso!");
		
		return EnderecoDasPaginasWeb.PAGINA_LISTAR_CARROS;
		
	}
	
	
	public List<Carro> listarCarros() {
		
		try {
			return carroService.getTodosOsCarros();
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
	}

	
	public boolean isEdicaoCarro() {
		
		return carro != null && carro.getId() != null;
	}
	
	
	public String diariasCarrosFormatados(double diaria) {
		
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		
		return String.valueOf(nf.format(diaria));
	}


	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	public Carro getSelectedCar() {
		return selectedCar;
	}
	public void setSelectedCar(Carro selectedCar) {
		this.selectedCar = selectedCar;
	}
	public CarroService getCarroService() {
		return carroService;
	}
	public void setCarroService(CarroService carroService) {
		this.carroService = carroService;
	}
}
