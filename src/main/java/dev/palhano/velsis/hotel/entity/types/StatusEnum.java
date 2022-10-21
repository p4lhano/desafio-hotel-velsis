package dev.palhano.velsis.hotel.entity.types;

import java.util.List;

import dev.palhano.velsis.hotel.entity.relatorios.TotalPorHospedeUltimo;
import dev.palhano.velsis.hotel.repository.HospedeRepository;

public enum StatusEnum {
	ACTIVE {
		@Override
		public List<TotalPorHospedeUltimo> getHospedes(HospedeRepository repository) {
			return repository.totalPorHospedesUltimoAtivos();
		}
	},NO_ACTIVE {
		@Override
		public List<TotalPorHospedeUltimo> getHospedes(HospedeRepository repository) {
			return repository.totalPorHospedesUltimoStatus(false);
		}
	};
	
	public abstract List<TotalPorHospedeUltimo> getHospedes(HospedeRepository repository);
}
