package com.ikjo.healtherwithtdd.api.controller.member;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import com.ikjo.healtherwithtdd.ControllerTestSupport;
import com.ikjo.healtherwithtdd.api.service.member.response.MemberLoginResponse;
import com.ikjo.healtherwithtdd.domain.model.member.LoginType;

class MemberControllerTest extends ControllerTestSupport {

	@DisplayName("회원가입/로그인을 시도한다.")
	@Test
	@WithMockUser
	void login() throws Exception {
		//given
		String provider = "kakao";
		String code = "exampleCode";

		MemberLoginResponse loginResponse = MemberLoginResponse.builder()
			.tokenType(LoginType.KAKAO)
			.build();

		when(memberService.createOrLogin(anyString(), anyString()))
			.thenReturn(loginResponse);

		//when //then
		mockMvc.perform(post("/api/v1/members/login/callback/{provider}", provider)
				.param("code", code)
				.with(csrf())
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").exists())
			.andExpect(jsonPath("$.error").doesNotExist());
	}

}
