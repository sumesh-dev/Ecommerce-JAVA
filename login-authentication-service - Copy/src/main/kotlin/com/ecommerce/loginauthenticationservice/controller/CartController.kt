package com.ecommerce.loginauthenticationservice.controller

import com.ecommerce.loginauthenticationservice.config.JwtRequestFilter
import com.ecommerce.loginauthenticationservice.models.Product
import com.ecommerce.loginauthenticationservice.service.CartService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

//@RestController
class CartController {

//    @Autowired
//    lateinit var cartService: CartService
//
//    @GetMapping("/addToCart/{product_id}")
//    fun addToCart(@PathVariable product_id:ObjectId):ResponseEntity<String> {
//        return ResponseEntity<String>(cartService.addToCart(product_id, JwtRequestFilter.email), HttpStatus.OK)
//    }
//
//    @DeleteMapping("/deleteToCart/{product_id}")
//    fun deleteToCart(@PathVariable product_id:ObjectId):ResponseEntity<String>{
//       return ResponseEntity<String>(cartService.deleteToCart(product_id,JwtRequestFilter.email),HttpStatus.OK)
//    }
//
//    @GetMapping("/showAllItemInCart")
//    fun showAllItemInCart():ResponseEntity<List<Product>>?{
//       return ResponseEntity<List<Product>>(cartService.showAllItemsInCart(JwtRequestFilter.email),HttpStatus.OK)
//    }
}