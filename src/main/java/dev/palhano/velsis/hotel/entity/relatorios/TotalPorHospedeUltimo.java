package dev.palhano.velsis.hotel.entity.relatorios;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import dev.palhano.velsis.hotel.entity.CheckIn;
import dev.palhano.velsis.hotel.entity.Hospede;
import dev.palhano.velsis.hotel.repository.CheckinRepository;

public class TotalPorHospedeUltimo {
	
	private Hospede hospede;
	private CheckIn ultimoCheckin;
	private BigDecimal total;
	public TotalPorHospedeUltimo(Hospede hospede,LocalDateTime ultimoEntrada, BigDecimal total) {
		this.hospede=hospede;
		this.total = total;
		this.ultimoCheckin =new CheckIn();
		this.ultimoCheckin.setDataEntrada(ultimoEntrada);
	}
	

	
	public Hospede getHospede() {
		return hospede;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public CheckIn getUltimoCheckin() {
		return ultimoCheckin;
	}
	
	public void setUltimoCheckin(CheckIn ultimoCheckin) {
		this.ultimoCheckin = ultimoCheckin;
	}
	public void setUltimoCheckinUseFind(CheckinRepository repository) {
		this.ultimoCheckin = repository
				.findByDataEntradaAndHospede(this.ultimoCheckin.getDataEntrada(),this.hospede)
				.orElse(this.ultimoCheckin);
	}

	@Override
	public String toString() {
		return "TotalPorHospedeUltimo [hospede=" + hospede + ", ultimoCheckin=" + ultimoCheckin + ", total=" + total
				+ "]";
	}
	
}
