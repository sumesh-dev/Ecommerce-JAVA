package com.ecommerce.userservice.controller

import com.ecommerce.userservice.config.JwtRequestFilter
import com.ecommerce.userservice.models.Product
import com.ecommerce.userservice.service.CartService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cart")
class CartController {

    @Autowired
    private lateinit var cartService: CartService

    @Autowired
    private val jwtRequestFilter: JwtRequestFilter? = null

    @GetMapping("/{product_id}")
    fun addToCart(@PathVariable product_id:ObjectId):ResponseEntity<String> {
        return ResponseEntity<String>(jwtRequestFilter?.let { cartService.addToCart(product_id, it.email) }, HttpStatus.OK)
    }

    @DeleteMapping("/{product_id}")
    fun deleteToCart(@PathVariable product_id:ObjectId):ResponseEntity<String>{
       return ResponseEntity<String>(jwtRequestFilter?.let { cartService.deleteToCart(product_id, it.email) },HttpStatus.OK)
    }

    @GetMapping("/showAllItemInCart")
    fun showAllItemInCart():ResponseEntity<MutableList<Product>>?{
       return ResponseEntity<MutableList<Product>>(jwtRequestFilter?.let { cartService.showAllItemsInCart(it.email) },HttpStatus.OK)
    }

//    @CrossOrigin(origins = ["http://localhost:9005"])
    @GetMapping("/showProductIDinCart/{email}")
    fun showProductIdCart(@PathVariable email: String):ResponseEntity<MutableList<ObjectId>>?{
        return ResponseEntity<MutableList<ObjectId>>(cartService.showProductIdInCart(email),HttpStatus.OK)
    }
}