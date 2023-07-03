package com.ikjo.healtherwithtdd.domain.model.spacekind;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SpaceType {
	GX("GX"),
	PILATES("PILATES"),
	ANAEROBIC("ANAEROBIC"),
	AEROBIC("AEROBIC");

	private final String value;

	SpaceType(String value) {
		this.value = value;
	}

	@JsonCreator
	public static SpaceType from(String value) {
		for (SpaceType status : SpaceType.values()) {
			if (status.getValue().equals(value)) {
				return status;
			}
		}
		return null;
	}

	@JsonValue
	public String getValue() {
		return value;
	}
}
