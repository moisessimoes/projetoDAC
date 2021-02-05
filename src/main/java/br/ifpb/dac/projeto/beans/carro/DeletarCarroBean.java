package br.ifpb.dac.projeto.beans.carro;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.dac.projeto.beans.AbstractBean;
import br.ifpb.dac.projeto.carroServices.CarroService;
import br.ifpb.dac.projeto.entidades.Carro;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.CarroFiltro;

@Named
@ViewScoped
public class DeletarCarroBean extends AbstractBean implements Serializable {
	
	//Classe bean responsavel por excluir o carro.

	private static final long serialVersionUID = 1L;
	
	private CarroFiltro carroFiltro;
	
	private List<Carro> carros;
	
	@Inject
	private CarroService carroService;
	
	@PostConstruct
	public void init() {
		
		limpar();
		filtrar();
	}
	
	
	public void deletar(Carro carro) {
		
		try {
			carroService.deletarCarro(carro);
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
		reportarMensagemDeSucesso("O carro foi excluído com sucesso.");
	}
	
	
	public String filtrar() {
		
		try {
			carros = carroService.encontrarPor(getCarroFiltro());
			
		} catch (DACExceptions e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}
	
	public String limpar() {
		
		this.carroFiltro = new CarroFiltro();
		return null;
	}

	public CarroFiltro getCarroFiltro() {
		return carroFiltro;
	}
	public void setCarroFiltro(CarroFiltro carroFiltro) {
		this.carroFiltro = carroFiltro;
	}
	public List<Carro> getCarros() {
		return carros;
	}
	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}
	public CarroService getCarroService() {
		return carroService;
	}
	public void setCarroService(CarroService carroService) {
		this.carroService = carroService;
	}
}
