package dev.palhano.velsis.hotel.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.palhano.velsis.hotel.entity.Hospede;
import dev.palhano.velsis.hotel.entity.dto.CheckInForm;
import dev.palhano.velsis.hotel.entity.dto.HospedeForm;
import dev.palhano.velsis.hotel.entity.mapper.HospedeMapper;
import dev.palhano.velsis.hotel.entity.relatorios.TotalPorHospede;
import dev.palhano.velsis.hotel.repository.HospedeRepository;

@Controller
@RequestMapping(value={"/hospede","/home"})
public class HospedeController {
	
	private final HospedeMapper hospedeMapper;
	private final HospedeRepository hospedeRepository;
	
	public HospedeController(HospedeRepository hospedeRepository,HospedeMapper hospedeMapper) {
		this.hospedeMapper = hospedeMapper;
		this.hospedeRepository = hospedeRepository;
	}
	
	@GetMapping
	public String home(Model request,CheckInForm checkInForm) {
		
		List<TotalPorHospede> totalPorHospedes = hospedeRepository.totalPorHospedes();
		request.addAttribute("hospedes", totalPorHospedes);
		
		
		return "home";
	}
	
	@GetMapping("novo")
	public String formNewHospede(HospedeForm hospedeForm) {
		return "hospede/novo";
	}
	
	@PostMapping("novo")
	public String newHospede(@Validated HospedeForm hospedeForm,BindingResult result) {
		if(result.hasErrors())
			return this.formNewHospede(hospedeForm);
		
		Hospede hospede = this.hospedeMapper.toHospede(hospedeForm);
		
		System.out.println(hospede);
		
		hospedeRepository.save(hospede);
		
		System.out.println(hospede);
		
		return "redirect:/home";
	}


	
}
