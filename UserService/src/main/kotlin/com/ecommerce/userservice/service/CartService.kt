package com.ecommerce.userservice.service

import com.ecommerce.userservice.models.Product
import org.bson.types.ObjectId

interface CartService {

    fun addToCart(product_id:ObjectId,email:String):String
    fun deleteToCart(product_id: ObjectId,email: String):String
    fun showAllItemsInCart(email: String):MutableList<Product>?
    fun showProductIdInCart(email: String):MutableList<ObjectId>?

}