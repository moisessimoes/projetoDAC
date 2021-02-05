package br.ifpb.dac.projeto.exceptions;

public class DACExceptions extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DACExceptions(String mensagem) {
		super(mensagem);
	}

	public DACExceptions(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
