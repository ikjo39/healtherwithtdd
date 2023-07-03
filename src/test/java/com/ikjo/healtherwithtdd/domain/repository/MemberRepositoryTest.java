package com.ikjo.healtherwithtdd.domain.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ikjo.healtherwithtdd.IntegrationTestSupport;
import com.ikjo.healtherwithtdd.domain.model.member.LoginType;
import com.ikjo.healtherwithtdd.domain.model.member.Member;
import com.ikjo.healtherwithtdd.domain.model.member.MemberRepository;
import com.ikjo.healtherwithtdd.domain.model.member.Role;

@Transactional
class MemberRepositoryTest extends IntegrationTestSupport {

	@Autowired
	MemberRepository memberRepository;

	@DisplayName("OAuth 고유식별번호로 회원 정보를 조회한다.")
	@Test
	void findByOauthId() {
		// given
		String targetOAuthId = "123456";
		Member member1 = createMember("test1@test.com", "홍길동", targetOAuthId);
		Member member2 = createMember("test2@test.com", "김첨지", "123457");
		Member member3 = createMember("test3@test.com", "박혁거세", "123458");
		memberRepository.saveAll(List.of(member1, member2, member3));
		// when
		Optional<Member> optionalMember = memberRepository.findByOauthId(targetOAuthId);

		// then
		assertThat(optionalMember).isPresent();
		assertThat(optionalMember.get().getOauthId()).isEqualTo(targetOAuthId);
	}

	private Member createMember(String email, String name, String oauthId) {
		return Member.builder()
			.email(email)
			.name(name)
			.role(Role.USER)
			.oauthId(oauthId)
			.loginType(LoginType.KAKAO)
			.build();
	}
}
