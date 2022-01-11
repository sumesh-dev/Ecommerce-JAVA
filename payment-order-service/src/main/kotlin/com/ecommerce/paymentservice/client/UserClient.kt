package com.ecommerce.paymentservice.client

import org.bson.types.ObjectId
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.net.URI

//@FeignClient(url = "http://localhost:9002/", name = "UserClient")
@FeignClient(name="UserClient")
interface UserClient {

    @GetMapping("cart/showProductIDinCart/{secretCode}/{email}")
    fun getProductsFromCart(baseUrl:URI, @PathVariable(name = "secretCode")secretCode:String, @PathVariable(name = "email")email:String):MutableList<ObjectId>

    @DeleteMapping("cart/deleteAllProductFromCart/{secretCode}/{email}")
    fun deleteProductsFromCart(baseUrl:URI,@PathVariable(name = "secretCode")secretCode:String, @PathVariable(name = "email")email: String):String
}