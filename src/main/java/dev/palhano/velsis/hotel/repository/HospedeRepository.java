package dev.palhano.velsis.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.palhano.velsis.hotel.entity.Hospede;
import dev.palhano.velsis.hotel.entity.relatorios.TotalPorHospede;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {
	@Query("select new dev.palhano.velsis.hotel.entity.relatorios.TotalPorHospede(h,sum(c.total))"
			+ " from CheckIn c join c.hospede h group by h.id")
	List<TotalPorHospede> totalPorHospedes();
}
