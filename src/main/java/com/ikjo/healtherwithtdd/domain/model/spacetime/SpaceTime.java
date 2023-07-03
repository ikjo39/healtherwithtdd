package com.ikjo.healtherwithtdd.domain.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at is null")
@SQLDelete(sql = "UPDATE space_time SET space_time.deleted_at = CURRENT_TIMESTAMP WHERE space_time.space_time_id = ?")
@Entity
public class SpaceTime extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Space space;

	private int openTime;

	private int closeTime;

	@Builder
	private SpaceTime(Space space, int openTime, int closeTime) {
		this.space = space;
		this.openTime = openTime;
		this.closeTime = closeTime;
	}
}
