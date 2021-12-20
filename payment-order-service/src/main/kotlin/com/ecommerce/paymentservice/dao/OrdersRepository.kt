package com.ecommerce.paymentservice.dao

import com.ecommerce.paymentservice.model.Orders
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface OrdersRepository:MongoRepository<Orders,ObjectId> {

    @Query("{email:?0}")
    fun findAllOrderByEmail(email:String):MutableList<Orders>
    @Query("{razorpayOrderId:?0}")
    fun findByRazorpayOrderId(razorpayOrderId:String):Orders

}