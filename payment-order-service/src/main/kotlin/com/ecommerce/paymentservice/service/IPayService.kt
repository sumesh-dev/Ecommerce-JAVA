package com.ecommerce.paymentservice.service

import com.ecommerce.paymentservice.model.UserAmount

interface IPayService {

    fun createOrder(email:String,userAmount: UserAmount):String
    fun updateOrder(orderId:String,paymentId:String,status:String):String
}