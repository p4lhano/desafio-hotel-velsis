package dev.palhano.velsis.hotel;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import dev.palhano.velsis.hotel.entity.CheckIn;

@Service
public class HospedagemService {
	
	private BigDecimal valorDiariaDiaUtil = new BigDecimal(150.0);
	private BigDecimal valorDiariaFinde = new BigDecimal(210.0);
	private int maxHourEstadia = 16 - 1 ;
	
	public BigDecimal calcularTotalHospedagem(CheckIn checkIn) {
		
		LocalDateTime dataSaida = checkIn.getDataSaida();
		if(dataSaida.getHour() > maxHourEstadia)
			dataSaida=dataSaida.plusDays(1l);
		
		long totalDias = checkIn.getDataEntrada().until(dataSaida, ChronoUnit.DAYS);
		
		int totalDiasUteis = qtnBusinessDay(checkIn.getDataEntrada(), totalDias);
                
        if(checkIn.isAdicionalVeiculo()) {
        	valorDiariaDiaUtil = valorDiariaDiaUtil.add(new BigDecimal(15.0));
        	valorDiariaFinde = valorDiariaFinde.add(new BigDecimal(20.0));
        }
        BigDecimal total = valorDiariaDiaUtil.multiply(new BigDecimal(totalDiasUteis))
					        	.add(
					        valorDiariaFinde.multiply(new BigDecimal(totalDias - totalDiasUteis))
					        		);
		return total;
	}

	/**
	 * @param dateStart
	 * @param totalDias
	 * @return
	 */
	private int qtnBusinessDay(LocalDateTime dateStart, long totalDias) {
		int totalDiasUteis=0;
		Predicate<LocalDateTime> isWeekend = dateCompare ->   dateCompare.getDayOfWeek() == DayOfWeek.SATURDAY 
	                								|| dateCompare.getDayOfWeek() == DayOfWeek.SUNDAY;

        for (int i = 0; i < totalDias; i++) {
        	dateStart = dateStart.plusDays(1);
            if (isWeekend.negate().test(dateStart)) {
            	totalDiasUteis++;
            }
		}
		return totalDiasUteis;
	}
	
	
	public static void main(String[] args) {
		
		HospedagemService hospedagemService = new HospedagemService();
		CheckIn checkIn = new CheckIn();
		checkIn.setAdicionalVeiculo(false);
		checkIn.setDataEntrada(LocalDateTime.of(2022, 10, 12, 12, 0));
		checkIn.setDataSaida(LocalDateTime.of(2022, 10, 17, 12, 0));
		
		hospedagemService.calcularTotalHospedagem(checkIn);
		
	}
}
