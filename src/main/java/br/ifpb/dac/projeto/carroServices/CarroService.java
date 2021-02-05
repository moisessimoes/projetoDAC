package br.ifpb.dac.projeto.carroServices;

import java.util.List;

import br.ifpb.dac.projeto.entidades.Carro;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.CarroFiltro;

public interface CarroService {
	
	public void salvarCarro(Carro carro) throws DACExceptions;
	
	public void atualizarCarro(Carro carro) throws DACExceptions;
	
	public void deletarCarro(Carro carro) throws DACExceptions;
	
	public Carro getCarroPeloID(int id) throws DACExceptions;
	
	public Carro getCarroPelaPlaca(String placa) throws DACExceptions;
	
	public List<Carro> getTodosOsCarros() throws DACExceptions;
	
	public List<Carro> getCarrosPelaMarca(String marca) throws DACExceptions;
	
	public List<Carro> encontrarPor(CarroFiltro carroFiltro) throws DACExceptions;

}
