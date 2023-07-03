package com.ikjo.healtherwithtdd.domain.model.coupon;

import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ikjo.healtherwithtdd.domain.model.BaseEntity;
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

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at is null")
@SQLDelete(sql = "UPDATE coupon SET COUPON.DELETED_AT = CURRENT_TIMESTAMP WHERE COUPON.COUPON_ID = ?")
@Getter
public class Coupon extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Space space;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Member member;

	private int discountAmount;

	private LocalDate openDate;

	private LocalDate expiredDate;

	private String couponNumber;

	private boolean isUsed;

	@Builder
	private Coupon(Space space, Member member, int discountAmount, LocalDate openDate, LocalDate expiredDate,
		String couponNumber, boolean isUsed) {
		this.space = space;
		this.member = member;
		this.discountAmount = discountAmount;
		this.openDate = openDate;
		this.expiredDate = expiredDate;
		this.couponNumber = couponNumber;
		this.isUsed = isUsed;
	}
}
