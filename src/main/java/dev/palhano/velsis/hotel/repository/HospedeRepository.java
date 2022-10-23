package dev.palhano.velsis.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.palhano.velsis.hotel.entity.Hospede;
import dev.palhano.velsis.hotel.entity.relatorios.TotalPorHospedeUltimo;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {
	
	@Query("select new dev.palhano.velsis.hotel.entity.relatorios.TotalPorHospedeUltimo(h,max(c.dataEntrada),sum(c.total))"
			+ " from CheckIn c right join c.hospede h group by h.id")
	List<TotalPorHospedeUltimo> totalPorHospedesUltimo();

	@Query("select new dev.palhano.velsis.hotel.entity.relatorios.TotalPorHospedeUltimo(h,max(c.dataEntrada),sum(c.total))"
			+ " from CheckIn c right join c.hospede h where h.estaHospedado = :status  group by h.id")
	List<TotalPorHospedeUltimo> totalPorHospedesUltimoStatus(@Param("status") Boolean status);
	
}
