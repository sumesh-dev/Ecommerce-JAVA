package com.ecommerce.userservice.controller

import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController {

//    @GetMapping("/{id}/{email}")
//    fun addOrder(@PathVariable id:ObjectId,@PathVariable email:String):ResponseEntity<String>{
//        ResponseEntity<String>(,HttpStatus.OK)
//    }

//    @GetMapping("/showpastorder/{email}")
//    fun showPastOrder(email:String):Response<MutableList<Orders>>{
//        return Response<String>()
//     }

}