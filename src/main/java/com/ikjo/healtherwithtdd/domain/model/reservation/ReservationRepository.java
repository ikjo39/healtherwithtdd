package com.ikjo.healtherwithtdd.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikjo.healtherwithtdd.domain.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	Optional<Reservation> findByReservationDateAndReservationTime(LocalDate reserveDate, int reserveTime);

	List<Reservation> findByMember_IdOrderByReservationDate(Long memberId);

	List<Reservation> findAllByMember_IdAndReservationDateOrderByReservationTime(
		Long memberId,
		LocalDate reservationDate
	);
}
