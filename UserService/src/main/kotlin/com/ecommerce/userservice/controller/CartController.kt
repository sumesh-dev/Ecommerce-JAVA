package com.ecommerce.userservice.controller

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

    @GetMapping("/{product_id}/{email}")
    fun addToCart(@PathVariable product_id:ObjectId,@PathVariable email:String):ResponseEntity<String> {
        return ResponseEntity<String>(cartService.addToCart(product_id, email), HttpStatus.OK)
    }

    @DeleteMapping("/{product_id}/{email}")
    fun deleteToCart(@PathVariable product_id:ObjectId,@PathVariable email:String):ResponseEntity<String>{
       return ResponseEntity<String>(cartService.deleteToCart(product_id,email),HttpStatus.OK)
    }

    @GetMapping("/showAllItemInCart/{email}")
    fun showAllItemInCart(@PathVariable email:String):ResponseEntity<MutableList<Product>>?{
       return ResponseEntity<MutableList<Product>>(cartService.showAllItemsInCart(email),HttpStatus.OK)
    }
}