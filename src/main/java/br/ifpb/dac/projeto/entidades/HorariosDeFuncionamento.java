package br.ifpb.dac.projeto.entidades;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class HorariosDeFuncionamento {
	
	@Enumerated(EnumType.STRING)
	private DiasDaSemana diasDaSemanaInicio;
	
	@Enumerated(EnumType.STRING)
	private DiasDaSemana diaDaSemanaFim;
	
	private String horarioDeAbertura;
	
	private String horarioDeFechamento;
	
	
	public HorariosDeFuncionamento() {}

	
	
	public DiasDaSemana getDiasDaSemanaInicio() {
		return diasDaSemanaInicio;
	}
	public void setDiasDaSemanaInicio(DiasDaSemana diasDaSemanaInicio) {
		this.diasDaSemanaInicio = diasDaSemanaInicio;
	}
	public DiasDaSemana getDiaDaSemanaFim() {
		return diaDaSemanaFim;
	}
	public void setDiaDaSemanaFim(DiasDaSemana diaDaSemanaFim) {
		this.diaDaSemanaFim = diaDaSemanaFim;
	}
	public String getHorarioDeAbertura() {
		return horarioDeAbertura;
	}
	public void setHorarioDeAbertura(String horarioDeAbertura) {
		this.horarioDeAbertura = horarioDeAbertura;
	}
	public String getHorarioDeFechamento() {
		return horarioDeFechamento;
	}
	public void setHorarioDeFechamento(String horarioDeFechamento) {
		this.horarioDeFechamento = horarioDeFechamento;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diaDaSemanaFim == null) ? 0 : diaDaSemanaFim.hashCode());
		result = prime * result + ((diasDaSemanaInicio == null) ? 0 : diasDaSemanaInicio.hashCode());
		result = prime * result + ((horarioDeAbertura == null) ? 0 : horarioDeAbertura.hashCode());
		result = prime * result + ((horarioDeFechamento == null) ? 0 : horarioDeFechamento.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HorariosDeFuncionamento other = (HorariosDeFuncionamento) obj;
		if (diaDaSemanaFim != other.diaDaSemanaFim)
			return false;
		if (diasDaSemanaInicio != other.diasDaSemanaInicio)
			return false;
		if (horarioDeAbertura == null) {
			if (other.horarioDeAbertura != null)
				return false;
		} else if (!horarioDeAbertura.equals(other.horarioDeAbertura))
			return false;
		if (horarioDeFechamento == null) {
			if (other.horarioDeFechamento != null)
				return false;
		} else if (!horarioDeFechamento.equals(other.horarioDeFechamento))
			return false;
		return true;
	}
}
