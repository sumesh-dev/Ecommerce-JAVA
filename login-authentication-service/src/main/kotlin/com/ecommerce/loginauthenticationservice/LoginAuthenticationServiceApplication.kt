package com.ecommerce.loginauthenticationservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class LoginAuthenticationServiceApplication{
	@Bean
//	@LoadBalanced
	public fun restTemplate(): RestTemplate {
		return RestTemplate();
	}
}

fun main(args: Array<String>) {
	runApplication<LoginAuthenticationServiceApplication>(*args)
}
