package com.ecommerce.paymentservice.client

import org.bson.types.ObjectId
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(url = "http://localhost:9002/", name = "UserClient")
interface UserClient {

    @GetMapping("cart/showProductIDinCart/{email}")
    fun getProductsFromCart(@PathVariable(name = "email")email:String):MutableList<ObjectId>

}