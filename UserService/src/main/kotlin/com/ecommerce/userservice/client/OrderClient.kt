package com.ecommerce.userservice.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

//@FeignClient(url="http://localhost:9003/order", name = "ORDER-CLIENT")
interface OrderClient {
//    @GetMapping("/{}")
//    fun showAllOrder(): Orders
}