package com.sumesh.productservice.dao

import com.sumesh.productservice.entity.Product
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface ProductRepository : MongoRepository<Product,ObjectId> {

    @Query("{name : {\$regex:?0}}")
    fun searchByName(name:String): MutableList<Product>

    @Query("{addBy:?0}")
    fun getProductBySellerId(SellerId:ObjectId): MutableList<Product>

}