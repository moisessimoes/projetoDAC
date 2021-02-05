package br.ifpb.dac.projeto.utils;

public class AcessoRestrito {
	
	/*Para cadastrar um novo administrador, tive uma ideia simples. Consiste basicamente em
	 * 
	 * fazer uma pergunta a quem está tentando cadastrar um novo administrador, que para esse projeto vai ser:
	 * 
	 * Quem é o professor atual de DAC?
	 * 
	 * Dependendo da resposta, o usuario será ou não encaminhado para a pagina de cadastro de administrador.
	 * 
	 * Sei que é simples, mas, pelo menos é uma camada de "seguranca".
	 * */
	
	public AcessoRestrito() {
		// TODO Auto-generated constructor stub
	}
	
	private String professor1 = "João Paulo";
	private String professor2 = "Cleyton Souza";
	private String professor3 = "Renata Pontes";
	private String professor4 = "Jaindson Valetim";
	private String professor5 = "Giuseppe Lima";
	
	public boolean acertou(String resposta) {
		
		switch (resposta) {
		
		case "João Paulo":
			
			return true;
			
		case "Cleyton Souza":
			
			return false;
			
		case "Renata Pontes":
			
			return false;
			
		case "Jaindson Valetim":
			
			return false;
			
		case "Giuseppe Lima":
			
			return false;

		default:
			return false;
		}
	}

	public String getProfessor1() {
		return professor1;
	}

	public void setProfessor1(String professor1) {
		this.professor1 = professor1;
	}

	public String getProfessor2() {
		return professor2;
	}

	public void setProfessor2(String professor2) {
		this.professor2 = professor2;
	}

	public String getProfessor3() {
		return professor3;
	}

	public void setProfessor3(String professor3) {
		this.professor3 = professor3;
	}

	public String getProfessor4() {
		return professor4;
	}

	public void setProfessor4(String professor4) {
		this.professor4 = professor4;
	}

	public String getProfessor5() {
		return professor5;
	}

	public void setProfessor5(String professor5) {
		this.professor5 = professor5;
	}
}
