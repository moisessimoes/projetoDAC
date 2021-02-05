package br.ifpb.dac.projeto.logicaNegocio;

import java.util.ArrayList;

import javax.ejb.Stateless;

@Stateless
public class Horarios {
	
	/* Esta classe trabalha com o horarios de funcionamaento das agencias:
	 * 
	 * Cada metodo gera um array de horarios com base no horario de abertura e fechamento
	 * de cada agencia.
	 * 
	 * */
	
	private String[] horarios = {"00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30",
			"09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", 
			"19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"};
	
	
	public Horarios() {}
	
	
	public String[] listaDeHorariosDasAgencias(String horaInicio, String horaFinal) {
		
		String[] lista = new String[horariosDeRetiradaEDevolucao(horaInicio, horaFinal).size()];
		
		for (int i = 0; i < lista.length; i++) {
			
			lista[i] = horariosDeRetiradaEDevolucao(horaInicio, horaFinal).get(i);
		}
		
		return lista;
	}
	
	/*O metodo abaixo retorna um array com os horarios dentro do intervalo de horaInicio e horaFinal.*/
	
	public ArrayList<String> horariosDeRetiradaEDevolucao(String horaInicio, String horaFinal) {
		
		ArrayList<String> listaDeHorarios = new ArrayList<String>();
		
		for (int i = indiceHoraInicio(horaInicio); i <= indiceHoraFinal(horaInicio, horaFinal); i++) {
			
			listaDeHorarios.add(horarios[i]);
		}
		return listaDeHorarios;
	}
	
	
	public int indiceHoraInicio(String horaInicio) { //Recupera o indice do elemento dentro da lista 'horarios'. Esse indice se refere ao horario de abertura da agencia.
		
		for (int i = 0; i < horarios.length; i++) {
			
			if(horarios[i].equals(horaInicio)) {
				
				return i;
			}
		}
		return 0;
	}
	
	
	public int indiceHoraFinal(String horaInicio, String horaFinal) { //Recupera o indice do elemento dentro da lista 'horarios'.Esse indice se refere ao horario de fechamento da agencia.
		
		for (int i = indiceHoraInicio(horaInicio); i < horarios.length; i++) {
			
			if(horarios[i].equals(horaFinal)) {
				
				return i;
			}
		}
		return 0;
	}
	
	
	public String[] getHorarios() {
		return horarios;
	}

	public void setHorarios(String[] horarios) {
		this.horarios = horarios;
	}
}
