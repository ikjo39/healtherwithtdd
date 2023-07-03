package com.ikjo.healtherwithtdd.configuration;

import java.util.Enumeration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class WebClientConfig {

	@Bean
	public WebClient webClient() {
		return WebClient
			.builder()
			.baseUrl("http://localhost:8080")
			.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
			.filter(ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
				ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
				log.info("####" + Thread.currentThread().getName());
				log.info(clientRequest.url().toString());
				ClientRequest.Builder from = ClientRequest.from(clientRequest);
				for (Enumeration<?> e = servletRequestAttributes.getRequest().getHeaderNames(); e.hasMoreElements(); ) {
					String nextHeaderName = (String)e.nextElement();
					String headerValue = servletRequestAttributes.getRequest().getHeader(nextHeaderName);
					if (nextHeaderName.equals("caller")) {
						from.header(nextHeaderName, headerValue);
					}
					log.info(nextHeaderName + headerValue);
				}
				return Mono.just(from.build());
			}).andThen(ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
				clientResponse.headers().asHttpHeaders().forEach((name, value) -> {
					try {
						log.info(name + new ObjectMapper().writeValueAsString(value));
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				});
				return Mono.just(clientResponse);
			})))
			.build();
	}
}
