package dev.palhano.velsis.hotel.entity.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import dev.palhano.velsis.hotel.entity.CheckIn;
import dev.palhano.velsis.hotel.entity.dto.CheckInForm;

@Component
public class CheckinMapper {
	private static final ModelMapper MODEL_MAPPER = new ModelMapper();
	private static final DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
	public CheckIn toCheckIn(CheckInForm form) {
		CheckIn checkIn = MODEL_MAPPER.map(form, CheckIn.class);
		checkIn.setDataEntrada(LocalDateTime.parse(form.getDataEntrada(), dtf));
		try {
			checkIn.setDataSaida(LocalDateTime.parse(form.getDataSaida(), dtf));			
		} catch (DateTimeParseException e) {
			System.out.println(e.getMessage());
		}
		return checkIn;
	}
}
