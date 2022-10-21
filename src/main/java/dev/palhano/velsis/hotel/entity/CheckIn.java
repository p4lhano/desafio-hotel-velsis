package dev.palhano.velsis.hotel.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CheckIn {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Hospede hospede;
	private LocalDateTime dataEntrada;
	private LocalDateTime dataSaida;
	private boolean adicionalVeiculo;
	private BigDecimal total;
	public Long getId() {
		return id;
	}
	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}
	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}
	public void setAdicionalVeiculo(boolean adicionalVeiculo) {
		this.adicionalVeiculo = adicionalVeiculo;
	}
	public boolean isAdicionalVeiculo() {
		return adicionalVeiculo;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "CheckIn [id=" + id + ", hospede=" + hospede + ", dataEntrada=" + dataEntrada + ", dataSaida="
				+ dataSaida + ", adicionalVeiculo=" + adicionalVeiculo + ", total=" + total + "]";
	}
	
	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}
	public LocalDateTime getDataSaida() {
		return dataSaida;
	}
	public Hospede getHospede() {
		return hospede;
	}
	
	
	
}
