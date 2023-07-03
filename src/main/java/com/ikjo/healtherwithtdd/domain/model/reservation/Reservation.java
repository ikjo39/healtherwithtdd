package com.ikjo.healtherwithtdd.domain.model.reservation;

import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ikjo.healtherwithtdd.domain.model.BaseEntity;
import com.ikjo.healtherwithtdd.domain.model.coupon.Coupon;
import com.ikjo.healtherwithtdd.domain.model.member.Member;
import com.ikjo.healtherwithtdd.domain.model.space.Space;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "DELETED_AT is null")
@SQLDelete(sql = "UPDATE reservation SET reservation.deleted_at = CURRENT_TIMESTAMP WHERE reservation.reservation_id = ?")
@Entity
public class Reservation extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Space space;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Coupon coupon;

	private LocalDate reservationDate;

	private int reservationTime;

	private int price;

	@Builder
	private Reservation(Member member, Space space, Coupon coupon, LocalDate reservationDate, int reservationTime,
		int price) {
		this.member = member;
		this.space = space;
		this.coupon = coupon;
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;
		this.price = price;
	}
}
