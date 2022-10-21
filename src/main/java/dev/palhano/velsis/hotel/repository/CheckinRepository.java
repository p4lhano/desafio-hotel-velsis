package dev.palhano.velsis.hotel.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.palhano.velsis.hotel.entity.CheckIn;
import dev.palhano.velsis.hotel.entity.Hospede;

@Repository
public interface CheckinRepository extends JpaRepository<CheckIn, Long> {

	Optional<CheckIn> findByDataEntradaAndHospede(LocalDateTime dataEntrada, Hospede hospede);

	
}
