package com.ikjo.healtherwithtdd.api.service.oauth;

import static com.ikjo.healtherwithtdd.domain.model.member.LoginType.*;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ikjo.healtherwithtdd.client.oauth.OAuthClient;
import com.ikjo.healtherwithtdd.domain.model.member.LoginType;
import com.ikjo.healtherwithtdd.dto.member.userinfo.KakaoUserInfo;
import com.ikjo.healtherwithtdd.dto.member.userinfo.OAuth2UserInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService {

	private final OAuthClient oAuthClient;

	public OAuth2UserInfo getOAuth2UserInfo(String providerName, String code) {
		Map<String, Object> userAttribute = oAuthClient.getOAuth2UserInfo(providerName, code);
		return createUserInfo(providerName, userAttribute);
	}

	private OAuth2UserInfo createUserInfo(String providerName, Map<String, Object> userAttribute) {
		LoginType loginType = matchLoginType(providerName);
		if (loginType.equals(KAKAO)) {
			return new KakaoUserInfo(userAttribute);
		} else {
			log.info("잘못된 접근입니다.");
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
	}
}
