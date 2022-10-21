package dev.palhano.velsis.hotel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import dev.palhano.velsis.hotel.entity.CheckIn;

@Service
public class HospedagemService {
	
	private BigDecimal valorDiaria = new BigDecimal(100);
	
	public BigDecimal calcularTotalHospedagem(CheckIn checkIn) {
		// TODO calcular dias uteis
		// TODO calcular finais de semana
		
		return null;
	}
}
