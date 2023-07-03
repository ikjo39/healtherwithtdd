package com.ikjo.healtherwithtdd.client.oauth;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.ikjo.healtherwithtdd.dto.member.OAuthTokenResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuthClient {
	private final InMemoryClientRegistrationRepository inMemoryRepository;
	private final WebClient webClient;

	public Map<String, Object> getOAuth2UserInfo(String providerName, String code) {
		ClientRegistration provider = inMemoryRepository.findByRegistrationId(providerName.toLowerCase());
		OAuthTokenResponseDto tokenResponseDto = getToken(provider, code);
		return getUserAttribute(provider, tokenResponseDto);
	}

	private OAuthTokenResponseDto getToken(ClientRegistration provider, String code) {
		return webClient.post()
			.uri(provider.getProviderDetails().getTokenUri())
			.headers(header -> {
				header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
			})
			.bodyValue(tokenRequest(provider, code))
			.retrieve()
			.bodyToMono(OAuthTokenResponseDto.class)
			.block();
	}

	private MultiValueMap<String, String> tokenRequest(ClientRegistration provider, String code) {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("code", code);
		formData.add("grant_type", "authorization_code");
		formData.add("redirect_uri", provider.getRedirectUri());
		formData.add("client_secret", provider.getClientSecret());
		formData.add("client_id", provider.getClientId());
		return formData;
	}

	private Map<String, Object> getUserAttribute(ClientRegistration provider, OAuthTokenResponseDto tokenResponse) {
		return webClient.get()
			.uri(provider.getProviderDetails().getUserInfoEndpoint().getUri())
			.headers(header -> header.setBearerAuth(tokenResponse.getAccessToken()))
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
			})
			.block();
	}
}
