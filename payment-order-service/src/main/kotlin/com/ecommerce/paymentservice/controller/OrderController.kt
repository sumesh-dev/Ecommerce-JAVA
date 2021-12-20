package com.ecommerce.paymentservice.controller

import com.ecommerce.paymentservice.config.JwtRequestFilter
import com.ecommerce.paymentservice.service.IOrderService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderController {

    @Autowired
    lateinit var jwtRequestFilter: JwtRequestFilter

    @Autowired
    lateinit var iOrderService: IOrderService

    //only admin can access
    @GetMapping("/showAllOrders")
    fun showAllOrders():ResponseEntity<Any>{
        return ResponseEntity<Any>(iOrderService.showAllOrders(),HttpStatus.OK)
    }

    //particular customer as well as admin  can access
    @GetMapping("/showAllMyOrders")
    fun showMyOrders():ResponseEntity<Any>{
        return ResponseEntity<Any>(iOrderService.showMyOrders(jwtRequestFilter.email),HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun particularOrder(@PathVariable id:ObjectId):ResponseEntity<Any>{
        return ResponseEntity<Any>(iOrderService.getOrder(id,jwtRequestFilter.email),HttpStatus.OK)
    }

}