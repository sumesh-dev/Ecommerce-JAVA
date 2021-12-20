package com.ecommerce.paymentservice.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

@Document("Orders")
class Orders {

    @Id
    lateinit var orderId:ObjectId

    @field:NotBlank(message = "email is mandatory")
    lateinit var email:String

    @field:NotBlank(message = "amount is mandatory")
    var amount : Int? = null

    @field:NotBlank(message = "txnId is mandatory")
    lateinit var txnId:String

    @field:NotBlank(message = "date is mandatory")
    lateinit var orderedDate : String

    @field:NotBlank(message = "products is necessary")
    lateinit var products:MutableList<ObjectId>

    @field:NotBlank(message = "receipt is necessary")
    lateinit var razorpayOrderId:String

    @field:NotBlank(message = "Payment status should not be blank ")
    lateinit var paymentStatus:String

    var paymentId:String?=null

    constructor(
        orderId: ObjectId,
        email: String,
        amount: Int?,
        txnId: String,
        orderedDate: LocalDateTime,
        products: MutableList<ObjectId>,
        razorpayOrderId: String,
        paymentStatus: String,
        paymentId: String?
    ) {
        this.orderId = orderId
        this.email = email
        this.amount = amount
        this.txnId = txnId
        this.orderedDate = orderedDate.toString()
        this.products = products
        this.razorpayOrderId = razorpayOrderId
        this.paymentStatus = paymentStatus
        this.paymentId = paymentId
    }

    constructor(
        email: String,
        amount: Int?,
        txnId: String,
        orderedDate: LocalDateTime,
        products: MutableList<ObjectId>,
        razorpayOrderId: String,
        paymentStatus: String,
        paymentId: String?
    ) {
        this.email = email
        this.amount = amount
        this.txnId = txnId
        this.orderedDate = orderedDate.toString()
        this.products = products
        this.razorpayOrderId = razorpayOrderId
        this.paymentStatus = paymentStatus
        this.paymentId = paymentId
    }

    constructor()


}
