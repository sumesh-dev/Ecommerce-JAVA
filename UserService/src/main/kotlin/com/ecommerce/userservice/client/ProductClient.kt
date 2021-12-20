package com.ecommerce.userservice.client

import com.ecommerce.userservice.models.Product
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

//@FeignClient(url="http://localhost:9003/product", name = "PRODUCT-CLIENT")
@FeignClient(url="http://product-service", name = "PRODUCT-CLIENT")
interface ProductClient {

    @GetMapping("/getProduct/{id}")
    fun getProduct(@PathVariable(name = "id") id : String) :Product

}