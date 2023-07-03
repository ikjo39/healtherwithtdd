package com.ikjo.healtherwithtdd.dto.member.userinfo;

import java.util.Map;

import com.ikjo.healtherwithtdd.domain.model.member.LoginType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KakaoUserInfo implements OAuth2UserInfo {

	private final Map<String, Object> attributes;
	private final Map<String, Object> attributesAccount;
	private final Map<String, Object> attributesProfile;

	public KakaoUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
		this.attributesAccount = (Map<String, Object>)attributes.get("kakao_account");
		this.attributesProfile = (Map<String, Object>)attributes.get("profile");
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getProviderId() {
		return attributes.get("id").toString();
	}

	@Override
	public LoginType getProvider() {
		return LoginType.KAKAO;
	}

	@Override
	public String getEmail() {
		return attributesAccount.get("email").toString();
	}

	@Override
	public String getName() {
		return attributesProfile.get("nickname").toString();
	}
}
