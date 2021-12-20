package com.ecommerce.paymentservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@EnableFeignClients
class PaymentServiceApplication{

//	@Bean
//	fun restTemplate():RestTemplate{
//		return RestTemplate()
//	}

}

fun main(args: Array<String>) {
	runApplication<PaymentServiceApplication>(*args)
}
