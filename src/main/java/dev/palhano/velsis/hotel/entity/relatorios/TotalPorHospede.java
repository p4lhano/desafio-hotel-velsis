package dev.palhano.velsis.hotel.entity.relatorios;

import java.math.BigDecimal;

import dev.palhano.velsis.hotel.entity.Hospede;

public class TotalPorHospede {
	
	private Hospede hospede;
	private BigDecimal total;
	public TotalPorHospede(Hospede hospede, BigDecimal total) {
		this.hospede=hospede;
		this.total = total;
	}
	@Override
	public String toString() {
		return "TotalPorHospede [hospede=" + hospede + ", total=" + total + "]";
	}
	public Hospede getHospede() {
		return hospede;
	}
	public BigDecimal getTotal() {
		return total;
	}
	
	
}
