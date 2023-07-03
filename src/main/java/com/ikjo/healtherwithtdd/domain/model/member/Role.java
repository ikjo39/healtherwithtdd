package com.ikjo.healtherwithtdd.domain.model.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	USER("일반 회원");

	private final String text;
}
