package dev.palhano.velsis.hotel.entity.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import dev.palhano.velsis.hotel.entity.Hospede;
import dev.palhano.velsis.hotel.entity.dto.HospedeForm;

@Component
public class HospedeMapper {
	private static final ModelMapper MODEL_MAPPER = new ModelMapper();

	public Hospede toHospede(HospedeForm hospedeForm) {
		return MODEL_MAPPER.map(hospedeForm, Hospede.class);
		
	}
}
