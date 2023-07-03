package com.ikjo.healtherwithtdd.api.service.oauth;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ikjo.healtherwithtdd.client.oauth.OAuthClient;
import com.ikjo.healtherwithtdd.domain.model.member.LoginType;
import com.ikjo.healtherwithtdd.dto.member.userinfo.OAuth2UserInfo;

@ExtendWith(MockitoExtension.class)
class OAuthServiceTest {

	@Mock
	private OAuthClient oAuthClient;

	@InjectMocks
	private OAuthService oAuthService;

	@DisplayName("소셜명과 코드로 사용자 정보를 반환한다.")
	@Test
	void getOAuth2UserInfo() {
		// given
		given(oAuthClient.getOAuth2UserInfo(anyString(), anyString()))
			.willReturn(Map.of("id", 1234, "kakao_account", Map.of("email", "test@test.com"),
				"profile", Map.of("nickname", "hi")));
		// when
		OAuth2UserInfo oAuth2UserInfo = oAuthService.getOAuth2UserInfo("kakao", "");

		// then
		assertThat(oAuth2UserInfo.getName()).isEqualTo("hi");
		assertThat(oAuth2UserInfo.getProviderId()).isEqualTo("1234");
		assertThat(oAuth2UserInfo.getProvider()).isEqualByComparingTo(LoginType.KAKAO);
		assertThat(oAuth2UserInfo.getEmail()).isEqualTo("test@test.com");
	}

	@DisplayName("잘못된 소셜명으로 사용자 정보를 요청하면 예외를 반환한다.")
	@Test
	void getOAuth2UserInfoWithWrongProviderName() {
		// given
		given(oAuthClient.getOAuth2UserInfo(anyString(), anyString()))
			.willReturn(Map.of("id", 1234, "kakao_account", Map.of("email", "test@test.com"),
				"profile", Map.of("nickname", "hi")));
		// when	// then
		assertThatThrownBy(() -> oAuthService.getOAuth2UserInfo("", ""))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("잘못된 접근입니다.");
	}
}
