package dev.palhano.velsis.hotel.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.palhano.velsis.hotel.HospedagemService;
import dev.palhano.velsis.hotel.entity.CheckIn;
import dev.palhano.velsis.hotel.entity.Hospede;
import dev.palhano.velsis.hotel.entity.dto.CheckInForm;
import dev.palhano.velsis.hotel.entity.dto.HospedeForm;
import dev.palhano.velsis.hotel.entity.mapper.CheckinMapper;
import dev.palhano.velsis.hotel.entity.mapper.HospedeMapper;
import dev.palhano.velsis.hotel.entity.relatorios.TotalPorHospede;
import dev.palhano.velsis.hotel.repository.CheckinRepository;
import dev.palhano.velsis.hotel.repository.HospedeRepository;

@Controller
@RequestMapping(value={"/checkin"})
public class CheckinController {
	
	private final HospedeController hospedeController;
	private final CheckinMapper checkinMapper;
	private final CheckinRepository checkinRepository;
	private final HospedeRepository hospedeRepository;
	private final HospedagemService hospedagemService;
	
	public CheckinController(CheckinRepository checkinRepository, HospedeController hospedeController, CheckinMapper checkinMapper,HospedeRepository hospedeRepository,HospedagemService hospedagemService) {
		this.hospedeController = hospedeController;
		this.checkinMapper = checkinMapper;
		this.checkinRepository = checkinRepository;
		this.hospedeRepository = hospedeRepository;
		this.hospedagemService = hospedagemService;
	}
	
	@GetMapping("novo")
	public String formNewHospede(CheckInForm checkInForm) {
		return "home";
	}
	
	@PostMapping
	@Transactional
	public String newChelin(@Validated CheckInForm checkInForm,BindingResult result,Model request) {
		System.out.println(checkInForm);
		
		if(result.hasErrors())
			return this.hospedeController.home(request, checkInForm);
		
		CheckIn checkIn = this.checkinMapper.toCheckIn(checkInForm);
		checkIn.setHospede(hospedeRepository.findById(checkIn.getHospede().getId()).orElseThrow(() -> new RuntimeException()));
		System.out.println(checkIn);
		
		if(checkIn.getHospede().isEstaHospedado())
			throw new RuntimeException("Hospede já esta hospedado");
			
		checkinRepository.save(checkIn);
		System.out.println(checkIn.getHospede());
		if(checkIn.getDataSaida() == null) {
			checkIn.getHospede().setEstaHospedado(true);
			return "redirect:/home";
		}
		BigDecimal total = hospedagemService.calcularTotalHospedagem(checkIn);
		checkIn.setTotal(total);
		// TODO criar na view um rececptor para alertas genéricos com o nome alert
		request.addAttribute("alert", "Total da hospedagem de "+checkIn.getHospede().getNome()+" é R$ "+total);
		return "redirect:/home";
	}
	
}
