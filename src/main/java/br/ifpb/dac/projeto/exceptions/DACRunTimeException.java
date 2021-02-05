package br.ifpb.dac.projeto.exceptions;

public class DACRunTimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DACRunTimeException(String mensagem) {
		super(mensagem);
	}
	
	
	public DACRunTimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
