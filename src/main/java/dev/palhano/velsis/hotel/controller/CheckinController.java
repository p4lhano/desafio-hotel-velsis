package dev.palhano.velsis.hotel.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.palhano.velsis.hotel.entity.CheckIn;
import dev.palhano.velsis.hotel.entity.Hospede;
import dev.palhano.velsis.hotel.entity.dto.CheckInForm;
import dev.palhano.velsis.hotel.entity.dto.HospedeForm;
import dev.palhano.velsis.hotel.entity.mapper.CheckinMapper;
import dev.palhano.velsis.hotel.repository.CheckinRepository;
import dev.palhano.velsis.hotel.repository.HospedeRepository;
import dev.palhano.velsis.hotel.service.HospedagemService;

@Controller
@RequestMapping(value={"/checkin"})
public class CheckinController {
	
	private final HospedeController hospedeController;
	private final CheckinMapper checkinMapper;
	private final CheckinRepository checkinRepository;
	private final HospedeRepository hospedeRepository;
	private final HospedagemService hospedagemService;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
	
	public CheckinController(CheckinRepository checkinRepository, HospedeController hospedeController, CheckinMapper checkinMapper,HospedeRepository hospedeRepository,HospedagemService hospedagemService) {
		this.hospedeController = hospedeController;
		this.checkinMapper = checkinMapper;
		this.checkinRepository = checkinRepository;
		this.hospedeRepository = hospedeRepository;
		this.hospedagemService = hospedagemService;
	}
	
	@GetMapping
	public String hospedarNow(@RequestParam Long user,CheckInForm checkInForm,HospedeForm hospedeForm,Model request) {
		Hospede hospede = hospedeRepository.findById(user).orElseThrow(() -> new RuntimeException("Hospede n??o encontrado"));
		
		if(hospede.isEstaHospedado())
			throw new RuntimeException("Hospede j?? esta hospedado");
		
		checkInForm.setHospedeId(user);
		checkInForm.setDataEntrada(LocalDateTime.now().format(formatter));
		
		hospedeController.populate(request);
		
		return "/home";
	}
	
	@GetMapping("out/{id}")
	@Transactional
	public String Checkout(@PathVariable Long id,Model request,HospedeForm hospedeForm,CheckInForm checkInForm) {
		
		CheckIn checkin = checkinRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("CheckIn n??o encontrado"));
		System.out.println(checkin);
		if(checkin.getDataSaida() != null)
			throw new RuntimeException("J?? realizado checkin");
		if(!checkin.getHospede().isEstaHospedado())
			throw new RuntimeException("Erro no hospede, n??o estava hospedado");
		
		checkin.setDataSaida(LocalDateTime.now());
		checkin.getHospede().setEstaHospedado(false);

		checkin.setTotal(hospedagemService.calcularTotalHospedagem(checkin)) ;
		
		request.addAttribute("alert", "Total da hospedagem de "+checkin.getHospede().getNome()+" ?? R$ "+checkin.getTotal());
        
		
		hospedeController.populate(request);
		
		return "/home";
	}
	
	@PostMapping
	@Transactional
	public String newChelin(@Validated CheckInForm checkInForm,BindingResult result,Model request,HospedeForm hospedeForm) {
		System.out.println(checkInForm);
		
		if(result.hasErrors())
			return this.hospedeController.home(request, checkInForm,hospedeForm);
		
		CheckIn checkIn = this.checkinMapper.toCheckIn(checkInForm);
		checkIn.setHospede(hospedeRepository.findById(checkIn.getHospede().getId()).orElseThrow(() -> new RuntimeException()));
		System.out.println(checkIn);
		
		if(checkIn.getHospede().isEstaHospedado())
			throw new RuntimeException("Hospede j?? esta hospedado");
			
		checkinRepository.save(checkIn);
		System.out.println(checkIn.getHospede());
		if(checkIn.getDataSaida() == null) {
			checkIn.getHospede().setEstaHospedado(true);
			return "redirect:/home";
		}
		BigDecimal total = hospedagemService.calcularTotalHospedagem(checkIn);
		checkIn.setTotal(total);
		// TODO criar na view um rececptor para alertas gen??ricos com o nome alert
		hospedeController.populate(request);
		request.addAttribute("alert", "Total da hospedagem de "+checkIn.getHospede().getNome()+" ?? R$ "+total);
		return "/home";
	}
	
}
