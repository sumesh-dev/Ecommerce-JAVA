package com.sumesh.productservice.service

import com.sumesh.productservice.model.Product
import org.bson.types.ObjectId

interface IProductService {
    fun addProduct(product: Product,user_id:ObjectId):String
    fun getProductById(id:ObjectId):Any
    fun deleteProductById(id: ObjectId):String
    fun getAllProducts():MutableList<Product>
    fun getAllProductsByParticularSeller(sellerId: ObjectId):MutableList<Product>?
    fun updateProductById(id:ObjectId, product: Product):Any?
    fun searchByName(name:String):MutableList<Product>
}