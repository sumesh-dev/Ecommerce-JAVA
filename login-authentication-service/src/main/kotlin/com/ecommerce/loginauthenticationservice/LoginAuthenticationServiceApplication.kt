package com.ecommerce.loginauthenticationservice

import com.ecommerce.loginauthenticationservice.service.UserServiceImpl
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.bson.types.ObjectId
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class LoginAuthenticationServiceApplication{
	@Bean
	fun customizer(): Jackson2ObjectMapperBuilderCustomizer? {
		return Jackson2ObjectMapperBuilderCustomizer { builder: Jackson2ObjectMapperBuilder ->
			builder.serializerByType(
				ObjectId::class.java, ToStringSerializer()
			)
		}
	}
}

fun main(args: Array<String>) {
	runApplication<LoginAuthenticationServiceApplication>(*args)
}
