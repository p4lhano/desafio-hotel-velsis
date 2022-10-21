package dev.palhano.velsis.hotel.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.palhano.velsis.hotel.entity.Hospede;
import dev.palhano.velsis.hotel.entity.dto.CheckInForm;
import dev.palhano.velsis.hotel.entity.dto.HospedeForm;
import dev.palhano.velsis.hotel.entity.mapper.HospedeMapper;
import dev.palhano.velsis.hotel.entity.relatorios.TotalPorHospede;
import dev.palhano.velsis.hotel.entity.relatorios.TotalPorHospedeUltimo;
import dev.palhano.velsis.hotel.entity.types.StatusEnum;
import dev.palhano.velsis.hotel.repository.CheckinRepository;
import dev.palhano.velsis.hotel.repository.HospedeRepository;

@Controller
@RequestMapping(value={"/hospede","/home"})
public class HospedeController {
	
	private final HospedeMapper hospedeMapper;
	private final HospedeRepository hospedeRepository;
	private final CheckinRepository checkinRepository;
	
	public HospedeController(HospedeRepository hospedeRepository,HospedeMapper hospedeMapper,CheckinRepository checkinRepository) {
		this.hospedeMapper = hospedeMapper;
		this.hospedeRepository = hospedeRepository;
		this.checkinRepository = checkinRepository;
	}
	
	@GetMapping
	public String home(Model request,CheckInForm checkInForm,HospedeForm hospedeForm) {
		
		List<TotalPorHospedeUltimo> totalPorHospedes = hospedeRepository.totalPorHospedesUltimo();
		totalPorHospedes.forEach(t -> {
			t.setUltimoCheckinUseFind(checkinRepository);
		});
		request.addAttribute("totalPorHospedes", totalPorHospedes);
		
		List<Hospede> hospedes = hospedeRepository.findAll();
		request.addAttribute("hospedes", hospedes);
		
		return "home";
	}
	
	@GetMapping("novo")
	public String formNewHospede(HospedeForm hospedeForm) {
		return "hospede/novo";
	}
	
	@PostMapping("novo")
	public String newHospede(@Validated HospedeForm hospedeForm,BindingResult result,Model request,CheckInForm checkInForm) {
		if(result.hasErrors()) {
//			return this.formNewHospede(hospedeForm);
			return this.home(request, checkInForm, hospedeForm);
		}
		Hospede hospede = this.hospedeMapper.toHospede(hospedeForm);
		
		
		hospedeRepository.save(hospede);
		
		
		return "redirect:/home";
	}

	
	@GetMapping("{status}")
	public String findStatus(@PathVariable String status, Model request,CheckInForm checkInForm) {
		
		System.out.println("Recebeu: "+status);
		
		List<TotalPorHospedeUltimo> hospedesFind = StatusEnum.valueOf(status.toUpperCase()).getHospedes(hospedeRepository);
		hospedesFind.forEach(t -> t.setUltimoCheckinUseFind(checkinRepository));
		request.addAttribute("totalPorHospedes", hospedesFind);
		
//		hospedeRepository.totalPorHospedesUltimoNotAtivos().forEach(b ->
//		System.out.println("Não ativo: "+b.getHospede().getNome()));
		
		List<Hospede> hospedes = hospedeRepository.findAll().stream().sorted().collect(Collectors.toList());
		request.addAttribute("hospedes", hospedes);
		request.addAttribute("status", status);
		return "/home";
	}

	
}
