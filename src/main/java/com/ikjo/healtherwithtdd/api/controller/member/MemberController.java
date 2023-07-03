package com.ikjo.healtherwithtdd.api.controller.member;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ikjo.healtherwithtdd.api.controller.ApiResponse;
import com.ikjo.healtherwithtdd.api.service.member.MemberService;
import com.ikjo.healtherwithtdd.api.service.member.response.MemberLoginResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@RestController
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/login/callback/{provider}")
	public ApiResponse<MemberLoginResponse> login(
		@PathVariable String provider,
		@RequestParam String code
	) {
		return ApiResponse.ok(memberService.createOrLogin(provider, code));
	}
}
