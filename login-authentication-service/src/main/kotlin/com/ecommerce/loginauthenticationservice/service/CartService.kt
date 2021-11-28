package com.ecommerce.loginauthenticationservice.service

import com.ecommerce.loginauthenticationservice.models.Product
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity

interface CartService {

    fun addToCart(product_id:ObjectId,email:String):String
    fun deleteToCart(product_id: ObjectId,email: String):String
    fun showAllItemsInCart(email: String):List<Product>?

}