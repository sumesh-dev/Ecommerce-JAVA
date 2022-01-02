package com.ecommerce.paymentservice.model

import org.bson.types.ObjectId
import java.time.LocalDateTime

data class OrderResponse(var orderId: String,
                         var email: String,
                         var amount: Int?,
                         var txnId: String,
                         var orderedDate: String,
                         var products: MutableList<String>,
                         var razorpayOrderId: String,
                         var paymentStatus: String,
                         var paymentId: String?)
