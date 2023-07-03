package com.ikjo.healtherwithtdd.api.service.member.response;

import com.ikjo.healtherwithtdd.domain.model.member.LoginType;
import com.ikjo.healtherwithtdd.domain.model.member.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberLoginResponse {
	private LoginType tokenType;
	private String accessToken;
	private String expiredTime;
	private String refreshToken;

	@Builder
	private MemberLoginResponse(LoginType tokenType, String accessToken, String expiredTime, String refreshToken) {
		this.tokenType = tokenType;
		this.accessToken = accessToken;
		this.expiredTime = expiredTime;
		this.refreshToken = refreshToken;
	}

	public static MemberLoginResponse of(Member member) {
		return MemberLoginResponse.builder()
			.tokenType(member.getLoginType())
			.build();
	}
}
