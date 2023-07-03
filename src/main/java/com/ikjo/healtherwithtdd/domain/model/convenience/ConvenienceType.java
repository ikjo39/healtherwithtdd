package com.ikjo.healtherwithtdd.domain.model.convenience;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConvenienceType {
	SHOWER("SHOWER"),
	PARKING("PARKING"),
	DRESSING_ROOM("DRESSING_ROOM"),
	MIRROR("MIRROR"),
	WIFI("WIFI"),
	FOOD("FOOD"),
	BRING_FOOD("BRING_FOOD");

	private final String text;
}
