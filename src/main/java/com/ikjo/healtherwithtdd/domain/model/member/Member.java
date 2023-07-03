package com.ikjo.healtherwithtdd.domain.model.member;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ikjo.healtherwithtdd.domain.model.BaseEntity;
import com.ikjo.healtherwithtdd.dto.member.userinfo.OAuth2UserInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at is null")
@SQLDelete(sql = "UPDATE member SET member.deleted_at = CURRENT_TIMESTAMP WHERE member.member_id = ?")
@Entity
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String name;

	@Enumerated(EnumType.STRING)
	private Role role;

	private String oauthId;

	@Enumerated(EnumType.STRING)
	private LoginType loginType;

	@Builder
	private Member(String email, String name, Role role, String oauthId, LoginType loginType) {
		this.email = email;
		this.name = name;
		this.role = role;
		this.oauthId = oauthId;
		this.loginType = loginType;
	}

	public static Member create(OAuth2UserInfo oAuth2UserInfo) {
		return Member.builder()
			.email(oAuth2UserInfo.getEmail())
			.name(oAuth2UserInfo.getName())
			.role(Role.USER)
			.oauthId(oAuth2UserInfo.getProviderId())
			.loginType(oAuth2UserInfo.getProvider())
			.build();
	}
}
