package com.ecommerce.fiegnClient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class FiegnClientApplication

fun main(args: Array<String>) {
	runApplication<FiegnClientApplication>(*args)
}
