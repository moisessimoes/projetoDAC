package br.ifpb.dac.projeto.entidades;

public interface Indentificavel extends Cloneable {
	
	public Indentificavel clone();
	
	public Integer getId();

	public void setId(Integer id);

}
