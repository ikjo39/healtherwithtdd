package com.ikjo.healtherwithtdd.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikjo.healtherwithtdd.domain.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
	List<Coupon> findBySpace_IdAndMember_IdAndExpiredDateIsAfterAndOpenDateIsBeforeAndIsUsed(
		Long spaceId, Long memberId, LocalDate expiredNow, LocalDate openNow, boolean isUsed
	);

	Optional<Coupon> findTopBySpace_IdAndMember_IdAndExpiredDateIsAfterAndOpenDateIsBeforeAndIsUsed(
		Long spaceId, Long memberId, LocalDate expiredNow, LocalDate openNow, boolean isUsed
	);

	List<Coupon> findBySpace_IdAndMember_IdAndExpiredDateIsAfterAndOpenDateIsBefore(
		Long spaceId, Long memberId, LocalDate expiredNow, LocalDate openNow
	);

	boolean existsBySpace_IdAndIsUsed(Long spaceId, Boolean isUsed);

	Long countBySpace_IdAndMember_IdAndExpiredDateIsAfterAndOpenDateIsBeforeAndIsUsed(
		Long spaceId, Long memberId, LocalDate expiredNow, LocalDate openNow, boolean isUsed
	);
}
