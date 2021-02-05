package br.ifpb.dac.projeto.entidades;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class EnderecoReserva {
	
	private int idAgenciaRetirada;
	
	private int idAgenciaDevolucao;
	
	@Temporal(TemporalType.DATE)
	private Date dataRetirada;
	
	private String horaRetirada;
	
	@Temporal(TemporalType.DATE)
	private Date dataDevolucao;
	
	private String horaDevolucao;
	
	public EnderecoReserva() {
		// TODO Auto-generated constructor stub
	}

	
	public int getIdAgenciaRetirada() {
		return idAgenciaRetirada;
	}
	public void setIdAgenciaRetirada(int idAgenciaRetirada) {
		this.idAgenciaRetirada = idAgenciaRetirada;
	}
	public int getIdAgenciaDevolucao() {
		return idAgenciaDevolucao;
	}
	public void setIdAgenciaDevolucao(int idAgenciaDevolucao) {
		this.idAgenciaDevolucao = idAgenciaDevolucao;
	}
	public Date getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public String getHoraRetirada() {
		return horaRetirada;
	}
	public void setHoraRetirada(String horaRetirada) {
		this.horaRetirada = horaRetirada;
	}
	public Date getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public String getHoraDevolucao() {
		return horaDevolucao;
	}
	public void setHoraDevolucao(String horaDevolucao) {
		this.horaDevolucao = horaDevolucao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataDevolucao == null) ? 0 : dataDevolucao.hashCode());
		result = prime * result + ((dataRetirada == null) ? 0 : dataRetirada.hashCode());
		result = prime * result + ((horaDevolucao == null) ? 0 : horaDevolucao.hashCode());
		result = prime * result + ((horaRetirada == null) ? 0 : horaRetirada.hashCode());
		result = prime * result + idAgenciaDevolucao;
		result = prime * result + idAgenciaRetirada;
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
		EnderecoReserva other = (EnderecoReserva) obj;
		if (dataDevolucao == null) {
			if (other.dataDevolucao != null)
				return false;
		} else if (!dataDevolucao.equals(other.dataDevolucao))
			return false;
		if (dataRetirada == null) {
			if (other.dataRetirada != null)
				return false;
		} else if (!dataRetirada.equals(other.dataRetirada))
			return false;
		if (horaDevolucao == null) {
			if (other.horaDevolucao != null)
				return false;
		} else if (!horaDevolucao.equals(other.horaDevolucao))
			return false;
		if (horaRetirada == null) {
			if (other.horaRetirada != null)
				return false;
		} else if (!horaRetirada.equals(other.horaRetirada))
			return false;
		if (idAgenciaDevolucao != other.idAgenciaDevolucao)
			return false;
		if (idAgenciaRetirada != other.idAgenciaRetirada)
			return false;
		return true;
	}
	
	

}
