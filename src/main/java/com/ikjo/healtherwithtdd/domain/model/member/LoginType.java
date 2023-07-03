package com.ikjo.healtherwithtdd.domain.model.member;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoginType {
	KAKAO("kakao"),
	GOOGLE("google");

	private final String text;

	public static LoginType matchLoginType(String providerName) {
		return Arrays.stream(LoginType.values())
			.filter(s -> s.getText().equals(providerName))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
	}
}
