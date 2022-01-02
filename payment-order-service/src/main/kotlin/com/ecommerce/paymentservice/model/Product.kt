package com.ecommerce.paymentservice.model

import org.bson.types.ObjectId


data class Product(
     var _id: String,
     var name:String,
     var img: String,
     var price: Int,
     var desc: String,
     var addBy: String)
