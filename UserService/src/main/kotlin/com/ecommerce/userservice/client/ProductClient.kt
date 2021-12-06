package com.ecommerce.userservice.client

import com.ecommerce.userservice.models.Product
import org.bson.types.ObjectId
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(url="http://product-service/product", name = "PRODUCT-CLIENT")
interface ProductClient {

    @GetMapping("/{id}")
    fun getProduct( id : ObjectId) :Product

}