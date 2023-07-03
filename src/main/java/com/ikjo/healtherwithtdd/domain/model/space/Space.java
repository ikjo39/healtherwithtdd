package com.ikjo.healtherwithtdd.domain.model.space;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ikjo.healtherwithtdd.domain.model.BaseEntity;
import com.ikjo.healtherwithtdd.domain.model.convenience.Convenience;
import com.ikjo.healtherwithtdd.domain.model.image.Image;
import com.ikjo.healtherwithtdd.domain.model.member.Member;
import com.ikjo.healtherwithtdd.domain.model.review.Review;
import com.ikjo.healtherwithtdd.domain.model.spacekind.SpaceKind;
import com.ikjo.healtherwithtdd.domain.model.spacetime.SpaceTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "DELETED_AT is null")
@SQLDelete(sql = "UPDATE space SET sapce.deleted_at = CURRENT_TIMESTAMP WHERE space.sapce_id = ?")
@Entity
public class Space extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SPACE_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@OneToMany(mappedBy = "space")
	private List<Review> reviews = new ArrayList<>();

	@Column(name = "TITLE")
	private String title;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "ADDRESS_DETAIL")
	private String addressDetail;

	@Column(name = "NOTICE")
	private String notice;

	@Column(name = "RULE")
	private String rule;

	@Column(name = "PRICE")
	private int price;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "space")
	private SpaceTime spaceTime;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "space")
	private Set<SpaceKind> spaceKinds = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "space")
	private Set<Convenience> conveniences = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "space")
	private List<Image> images;

	@Builder
	private Space(Member member, List<Review> reviews, String title, String content, String address,
		String addressDetail,
		String notice, String rule, int price, SpaceTime spaceTime, Set<SpaceKind> spaceKinds,
		Set<Convenience> conveniences, List<Image> images) {
		this.member = member;
		this.reviews = reviews;
		this.title = title;
		this.content = content;
		this.address = address;
		this.addressDetail = addressDetail;
		this.notice = notice;
		this.rule = rule;
		this.price = price;
		this.spaceTime = spaceTime;
		this.spaceKinds = spaceKinds;
		this.conveniences = conveniences;
		this.images = images;
	}
}
