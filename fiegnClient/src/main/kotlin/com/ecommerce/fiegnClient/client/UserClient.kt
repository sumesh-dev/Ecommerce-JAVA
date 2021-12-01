package com.ecommerce.fiegnClient.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(url="", name = "User-client")
interface UserClient {

    @GetMapping("")

}