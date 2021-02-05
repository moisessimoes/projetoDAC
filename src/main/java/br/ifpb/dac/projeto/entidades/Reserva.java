package br.ifpb.dac.projeto.entidades;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table (name = "tb_reservas", uniqueConstraints = {@UniqueConstraint(name = "uk__tb_reservas__codigoDaReserva", columnNames = { "codigoDaReserva" }) })
public class Reserva implements Indentificavel {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Embedded
	private EnderecoReserva enderecoReserva;
	
	private int quantidadeDeDiarias;
	
	private double tarifaCoberturaCarro;
	
	private double tarifaCoberturaTerceiros;
	
	private double tarifaGPS;
	
	private double taxa;
	
	private double valorTotalPrevisto;
	
	private int codigoDaReserva;
	
	@ManyToOne
	@JoinColumn(name = "cliente_fk", foreignKey = @ForeignKey(name = "fk__tb_reservas__cliente"))
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "carro_fk", foreignKey = @ForeignKey(name = "fk__tb_reservas__carro"))
	private Carro carro;
	
	
	
	public Reserva() {
		
	}
	
	
//--------------GETS E SETTERS---------------------------------------------------
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getQuantidadeDeDiarias() {
		return quantidadeDeDiarias;
	}
	public void setQuantidadeDeDiarias(int quantidadeDeDiarias) {
		this.quantidadeDeDiarias = quantidadeDeDiarias;
	}
	public double getTarifaCoberturaCarro() {
		return tarifaCoberturaCarro;
	}
	public void setTarifaCoberturaCarro(double tarifaCoberturaCarro) {
		this.tarifaCoberturaCarro = tarifaCoberturaCarro;
	}
	public double getTarifaCoberturaTerceiros() {
		return tarifaCoberturaTerceiros;
	}
	public void setTarifaCoberturaTerceiros(double tarifaCoberturaTerceiros) {
		this.tarifaCoberturaTerceiros = tarifaCoberturaTerceiros;
	}
	public double getTarifaGPS() {
		return tarifaGPS;
	}
	public void setTarifaGPS(double tarifaGPS) {
		this.tarifaGPS = tarifaGPS;
	}
	public double getTaxa() {
		return taxa;
	}
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	public double getValorTotalPrevisto() {
		return valorTotalPrevisto;
	}
	public void setValorTotalPrevisto(double valorTotalPrevisto) {
		this.valorTotalPrevisto = valorTotalPrevisto;
	}
	public int getCodigoDaReserva() {
		return codigoDaReserva;
	}
	public void setCodigoDaReserva(int codigoDaReserva) {
		this.codigoDaReserva = codigoDaReserva;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	public EnderecoReserva getEnderecoReserva() {
		return enderecoReserva;
	}
	public void setEnderecoReserva(EnderecoReserva enderecoReserva) {
		this.enderecoReserva = enderecoReserva;
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carro == null) ? 0 : carro.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + codigoDaReserva;
		result = prime * result + ((enderecoReserva == null) ? 0 : enderecoReserva.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + quantidadeDeDiarias;
		long temp;
		temp = Double.doubleToLongBits(tarifaCoberturaCarro);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tarifaCoberturaTerceiros);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tarifaGPS);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(taxa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotalPrevisto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Reserva other = (Reserva) obj;
		if (carro == null) {
			if (other.carro != null)
				return false;
		} else if (!carro.equals(other.carro))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (codigoDaReserva != other.codigoDaReserva)
			return false;
		if (enderecoReserva == null) {
			if (other.enderecoReserva != null)
				return false;
		} else if (!enderecoReserva.equals(other.enderecoReserva))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (quantidadeDeDiarias != other.quantidadeDeDiarias)
			return false;
		if (Double.doubleToLongBits(tarifaCoberturaCarro) != Double.doubleToLongBits(other.tarifaCoberturaCarro))
			return false;
		if (Double.doubleToLongBits(tarifaCoberturaTerceiros) != Double
				.doubleToLongBits(other.tarifaCoberturaTerceiros))
			return false;
		if (Double.doubleToLongBits(tarifaGPS) != Double.doubleToLongBits(other.tarifaGPS))
			return false;
		if (Double.doubleToLongBits(taxa) != Double.doubleToLongBits(other.taxa))
			return false;
		if (Double.doubleToLongBits(valorTotalPrevisto) != Double.doubleToLongBits(other.valorTotalPrevisto))
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "AluguelDoCarro [id=" + id + ", enderecoReserva=" + enderecoReserva + ", quantidadeDeDiarias="
				+ quantidadeDeDiarias + ", tarifaCoberturaCarro=" + tarifaCoberturaCarro + ", tarifaCoberturaTerceiros="
				+ tarifaCoberturaTerceiros + ", tarifaGPS=" + tarifaGPS + ", taxa=" + taxa + ", valorTotalPrevisto="
				+ valorTotalPrevisto + ", codigoDaReserva=" + codigoDaReserva + ", cliente=" + cliente + ", carro="
				+ carro + "]";
	}


	@Override
	public Reserva clone() {
		
		Reserva clone = new Reserva();
		
		clone.setId(id);
		clone.setQuantidadeDeDiarias(quantidadeDeDiarias);
		clone.setValorTotalPrevisto(valorTotalPrevisto);
		clone.setTarifaCoberturaCarro(tarifaCoberturaCarro);
		clone.setTarifaCoberturaTerceiros(tarifaCoberturaTerceiros);
		clone.setTarifaGPS(tarifaGPS);
		clone.setTaxa(taxa);
		clone.setCodigoDaReserva(codigoDaReserva);
		clone.setCliente(cliente);
		clone.setCarro(carro);
		
		return clone;
	}
}
