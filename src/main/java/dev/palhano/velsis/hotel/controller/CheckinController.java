package dev.palhano.velsis.hotel.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	public CheckinController(CheckinRepository checkinRepository, HospedeController hospedeController, CheckinMapper checkinMapper) {
		this.hospedeController = hospedeController;
		this.checkinMapper = checkinMapper;
		this.checkinRepository = checkinRepository;
	}
	
	@GetMapping("novo")
	public String formNewHospede(CheckInForm checkInForm) {
		return "home";
	}
	
	@PostMapping
	public String newChelin(@Validated CheckInForm checkInForm,BindingResult result,Model request) {
		System.out.println(checkInForm);
		
		if(result.hasErrors())
			return this.hospedeController.home(request, checkInForm);
		
		CheckIn checkIn = this.checkinMapper.toCheckIn(checkInForm);
		
		System.out.println(checkIn);
		
		checkinRepository.save(checkIn);
		
		System.out.println(checkIn);
		
		return "redirect:/home";
	}
	
}
