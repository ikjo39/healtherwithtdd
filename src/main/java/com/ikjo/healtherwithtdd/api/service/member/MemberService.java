package com.ikjo.healtherwithtdd.api.service.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikjo.healtherwithtdd.api.service.member.response.MemberLoginResponse;
import com.ikjo.healtherwithtdd.api.service.oauth.OAuthService;
import com.ikjo.healtherwithtdd.domain.model.member.Member;
import com.ikjo.healtherwithtdd.domain.model.member.MemberRepository;
import com.ikjo.healtherwithtdd.dto.member.userinfo.OAuth2UserInfo;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final OAuthService oAuthService;

	@Transactional
	public MemberLoginResponse createOrLogin(String provider, String code) {
		OAuth2UserInfo oAuth2UserInfo = oAuthService.getOAuth2UserInfo(provider, code);
		Member member = memberRepository.findByOauthId(oAuth2UserInfo.getProviderId())
			.orElseGet(() -> memberRepository.save(Member.create(oAuth2UserInfo)));
		// TODO JWT 토큰 발행

		return MemberLoginResponse.of(member);
	}

}
