package com.ikjo.healtherwithtdd.api.service.member;

import static com.ikjo.healtherwithtdd.domain.model.member.LoginType.*;
import static com.ikjo.healtherwithtdd.domain.model.member.Role.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ikjo.healtherwithtdd.IntegrationTestSupport;
import com.ikjo.healtherwithtdd.api.service.member.response.MemberLoginResponse;
import com.ikjo.healtherwithtdd.domain.model.member.Member;
import com.ikjo.healtherwithtdd.domain.model.member.MemberRepository;
import com.ikjo.healtherwithtdd.dto.member.userinfo.KakaoUserInfo;

@Transactional
class MemberServiceTest extends IntegrationTestSupport {

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberRepository memberRepository;

	@DisplayName("소셜명과 코드를 통해 회원가입/로그인을 성공한다.")
	@Test
	void createOrLogin() {
		// given
		given(oAuthService.getOAuth2UserInfo(anyString(), anyString()))
			.willReturn(new KakaoUserInfo(Map.of("id", 1234, "kakao_account", Map.of("email", "test@test.com"),
				"profile", Map.of("nickname", "hi"))));
		// when
		MemberLoginResponse memberLoginResponse = memberService.createOrLogin("kakao", "");

		// then
		assertThat(memberLoginResponse)
			.extracting("tokenType", "accessToken", "expiredTime", "refreshToken")
			.contains(KAKAO, null, null, null);
		List<Member> members = memberRepository.findAll();
		assertThat(members).hasSize(1)
			.extracting("email", "name", "role", "oauthId", "loginType")
			.containsExactlyInAnyOrder(
				tuple("test@test.com", "hi", USER, "1234", KAKAO)
			);
	}

	private Member createMember(String email, String name, String oauthId) {
		return Member.builder()
			.email(email)
			.name(name)
			.role(USER)
			.oauthId(oauthId)
			.loginType(KAKAO)
			.build();
	}
}
