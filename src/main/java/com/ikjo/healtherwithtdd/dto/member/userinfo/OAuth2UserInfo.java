package com.ikjo.healtherwithtdd.dto.member.userinfo;

import java.util.Map;

import com.ikjo.healtherwithtdd.domain.model.member.LoginType;

public interface OAuth2UserInfo {

	Map<String, Object> getAttributes();

	String getProviderId();

	LoginType getProvider();

	String getEmail();

	String getName();
}
