package com.ecommerce.paymentservice.service

import org.bson.types.ObjectId

interface IOrderService {
    fun showAllOrders():Any
    fun getOrder(id: ObjectId, email: String): Any
    fun showMyOrders(email: String): Any
}