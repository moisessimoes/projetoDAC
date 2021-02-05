package br.ifpb.dac.projeto.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.format.Formatter;

public class Formatadores implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/*Esta classe serve basicamente para formatar a saída de alguns valores, por exemplo.
	 * 
	 * - Telefone
	 * - CPF
	 * - CNPJ
	 * - Datas
	 * 
	 * */
	
	
	private SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	private Formatter formatadorCPF = new CPFFormatter(); //Pertence a API da Stella Caelum para trabalhar com CPF.
	
	private Formatter formatadorCNPJ = new CNPJFormatter(); //Pertence a API da Stella Caelum para trabalhar com CNPJ.
	
	
	public String cpfFormatter(String cpf) { //https://faroljava.wordpress.com/2010/05/01/stella-caelum/
		
		return formatadorCPF.format(cpf);
	}
	
	
	public String cnpjFormatter(String cnpj) {
		
		return formatadorCNPJ.format(cnpj);
	}
	
	
	public String telephoneFormatter(String numero) {
		
		try {
			
			numero = numero.replaceAll("\\D", ""); //Removendo todos os caracteres não-numéricos
				
			MaskFormatter mask = new MaskFormatter("(##) #####-####");

			JFormattedTextField text = new JFormattedTextField(mask);

			text.setText(numero);

			return text.getText();
			
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String dateFormatter(Date data) {
		
		return dataFormat.format(data);
	}
	
	
	public String boasVindas() {
		
		DateTime tempo = new DateTime();
		
		String boasVindas = "";
		
		int horaAtual = tempo.getHourOfDay();
		
		if(horaAtual >= 0 && horaAtual <= 12) {
			
			boasVindas = "Bom Dia,";
		}
		
		if(horaAtual >= 12 && horaAtual < 18) {
			
			boasVindas = "Boa Tarde,";
		}
		
		if(horaAtual >= 18 && horaAtual < 24) {
			
			boasVindas = "Boa Noite,";
		}
		
		return boasVindas;
	}
	
	
	public String mostrarAsHoras() {
		
		LocalTime hora = new LocalTime();
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
		
		String horaAtualFormatada = hora.toString(formatter);
		
		return horaAtualFormatada;
	}
}
