package com.ecommerce.paymentservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/payment")
class PaymentController {

    @GetMapping("/proceedPayment/{email}")
    fun proceedPayment(@PathVariable email:String){
        val uuid = UUID.randomUUID()
        val txnid = uuid.toString().replace("-","")
        println(txnid)

    }
}