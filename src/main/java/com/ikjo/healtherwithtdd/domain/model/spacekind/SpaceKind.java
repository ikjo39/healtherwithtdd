package com.ikjo.healtherwithtdd.domain.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ikjo.healtherwithtdd.constant.SpaceType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@SQLDelete(sql = "UPDATE space_kind SET space_kind.deleted_at = CURRENT_TIMESTAMP WHERE space_kind.space_kind_id = ?")
@Entity
public class SpaceKind extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	@JsonIgnore
	private Space space;

	@Enumerated(EnumType.STRING)
	private SpaceType spaceType;

	@Builder
	private SpaceKind(Space space, SpaceType spaceType) {
		this.space = space;
		this.spaceType = spaceType;
	}
}
