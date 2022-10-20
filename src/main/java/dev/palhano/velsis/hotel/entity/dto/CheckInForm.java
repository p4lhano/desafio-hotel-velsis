package dev.palhano.velsis.hotel.entity.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CheckInForm {
	@NotNull
	private Long hospedeId;
	@NotNull
	@NotBlank
	private String dataEntrada;
	@NotNull
	@NotBlank
	private String dataSaida;
	private boolean adicionalVeiculo;
	public Long getHospedeId() {
		return hospedeId;
	}
	public void setHospedeId(Long hospedeId) {
		this.hospedeId = hospedeId;
	}
	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}
	public boolean isAdicionalVeiculo() {
		return adicionalVeiculo;
	}
	public void setAdicionalVeiculo(boolean adicionalVeiculo) {
		this.adicionalVeiculo = adicionalVeiculo;
	}
	@Override
	public String toString() {
		return "CheckInForm [hospedeId=" + hospedeId + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida
				+ ", adicionalVeiculo=" + adicionalVeiculo + "]";
	}
	
	
	
}
