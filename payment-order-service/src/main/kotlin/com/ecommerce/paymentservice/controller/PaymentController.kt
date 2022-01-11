package com.ecommerce.paymentservice.controller

import com.ecommerce.paymentservice.config.JwtRequestFilter
import com.ecommerce.paymentservice.model.UpdateOrder
import com.ecommerce.paymentservice.model.UserAmount
import com.ecommerce.paymentservice.service.IPayService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/payment")
class PaymentController {
    @Autowired
    lateinit var payService:IPayService

    @Autowired
    lateinit var jwtRequestFilter: JwtRequestFilter


    @PostMapping("/createOrder")
    fun createOrder(@RequestBody userAmount: UserAmount): ResponseEntity<String> {
        return ResponseEntity<String>(payService.createOrder(jwtRequestFilter.email,userAmount),HttpStatus.OK)
    }

    @PostMapping("/updateOrder")
    fun updateOrder(@RequestBody updateOrder: UpdateOrder):ResponseEntity<String>{
//        return ResponseEntity<String>(payService.updateOrder(data["order_id"].toString(),data["payment_id"].toString(),data["status"].toString(),jwtRequestFilter.email),HttpStatus.OK)
        return ResponseEntity<String>(payService.updateOrder(updateOrder.order_id,updateOrder.payment_id,updateOrder.status,jwtRequestFilter.email),HttpStatus.OK)
    }

}
