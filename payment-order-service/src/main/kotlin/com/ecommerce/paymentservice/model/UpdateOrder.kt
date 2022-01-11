package com.ecommerce.paymentservice.model

data class UpdateOrder(var order_id:String,
var payment_id:String,
var status:String)
