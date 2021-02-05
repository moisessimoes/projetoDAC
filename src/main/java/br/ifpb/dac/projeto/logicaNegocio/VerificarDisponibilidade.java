package br.ifpb.dac.projeto.logicaNegocio;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.Stateless;

import org.joda.time.DateTime;

@Stateless
public class VerificarDisponibilidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/*
	 * O que essa classe faz é verificar se o carro está disponível nas datas escolhidas pelo cliente. Por exemplo:
	 * 
	 *  Se um carro X estiver alugado entre as datas 18/08 a 20/08, o proximo cliente não pode alugar esse carro nessa data específica, ele
	 *  
	 *  terá que escolher outra data.
	 *  
	 * Esse metodo recebe como parametro as datas de retirada e devolucao inseridas pelo cliente que quer alugar o carro, e as outras datas são
	 * 
	 * obtidas do banco para saber se será possível alugar o carro nas datas passadas. É importante frizar que antes disso, é verificado se já
	 * 
	 * existe algum registro de aluguel no banco associado ao carro escolhido pelo cliente, se houver, aí sim esse metodo entra em ação.
	 * 
	 */
	
	
	public boolean carroIndisponivel(DateTime dataRetirada, DateTime dataDevolucao, Date dataRetiradaCarroAlugado, Date dataDevolucaoCarroAlugado) {
		
		//DateTime pertence a API Joda Time
		
		DateTime dataR = new DateTime(dataRetirada); //Data que o cliente insere (Data de Retirada)
		DateTime dataD = new DateTime(dataDevolucao); //Data que o cliente insere (Data de Devolucao)
		
		DateTime dataRCarroAlugado = new DateTime(dataRetiradaCarroAlugado); //Data que é verificada no banco. (Data de Retirada)
		DateTime dataDCarroAlugado = new DateTime(dataDevolucaoCarroAlugado); //Data que é verificada no banco. (Data de Devolucao)
		
		
		if(dataR.equals(dataRCarroAlugado)) {
			
			return true;
			
		} else if(dataR.isAfter(dataRCarroAlugado) && dataR.isBefore(dataDCarroAlugado)) {
			
			return true;
			
		} else if(dataR.equals(dataDCarroAlugado)) {
			
			return true;
			
		} else if(dataD.isAfter(dataRCarroAlugado) && dataD.isBefore(dataDCarroAlugado)) {
			
			return true;
			
		} else if(dataD.equals(dataDCarroAlugado)) {
			
			return true;
		}
		
		return false;
	}
}
