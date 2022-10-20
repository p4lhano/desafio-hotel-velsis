package dev.palhano.velsis.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.palhano.velsis.hotel.entity.CheckIn;

@Repository
public interface CheckinRepository extends JpaRepository<CheckIn, Long> {
}
