package com.sumesh.productservice.controller

import com.sumesh.productservice.config.JwtRequestFilter
import com.sumesh.productservice.dao.IUserRepository
import com.sumesh.productservice.model.Product
import com.sumesh.productservice.service.IProductService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/product")
class ProductController {

    @Autowired
    private lateinit var iProductService: IProductService

    @Autowired
    private lateinit var iUserRepository: IUserRepository

    @Autowired
    private lateinit var  jwtRequestFilter: JwtRequestFilter

    @PostMapping("/addProduct")
    fun addNewProduct(@Valid @RequestBody product: Product):ResponseEntity<String>{
        var user_id = iUserRepository.findByEmail(jwtRequestFilter?.email)?._id
        return ResponseEntity(user_id?.let { iProductService.addProduct(product, it) },HttpStatus.OK)
    }

    @GetMapping("getProduct/{id}")
    fun getProductById(@PathVariable id:ObjectId):ResponseEntity<Any>{
        return ResponseEntity(iProductService.getProductById(id),HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteProductById(@PathVariable id: ObjectId):ResponseEntity<String>{
        return ResponseEntity(iProductService.deleteProductById(id),HttpStatus.OK)
    }

    @GetMapping("/getAllProducts")
    fun getAllProducts():ResponseEntity<MutableList<Product>>{
        return ResponseEntity(iProductService.getAllProducts(),HttpStatus.OK)
    }

    @GetMapping("/getAllProductsByMe")
    fun getAllProductsByMe():ResponseEntity<MutableList<Product>>{
        var sellerId = iUserRepository.findByEmail(jwtRequestFilter?.email)?._id
        return ResponseEntity(sellerId?.let { iProductService.getAllProductsByParticularSeller(it) },HttpStatus.OK)
    }

    @GetMapping("/getAllProductsBySeller/{sellerId}")
    fun getAllProductsBySeller(@PathVariable sellerId:ObjectId):ResponseEntity<MutableList<Product>>{
        return ResponseEntity(iProductService.getAllProductsByParticularSeller(sellerId),HttpStatus.OK)
    }

    @PatchMapping("/update/{id}")
    fun updateProductsById(@Valid @RequestBody product:Product,@PathVariable id: ObjectId ):ResponseEntity<Any?>{
        return ResponseEntity(iProductService.updateProductById(id, product),HttpStatus.OK)
    }

    @GetMapping("/searchByName/{name}")
    fun SearchByName(@PathVariable name:String):ResponseEntity<MutableList<Product>>{
        return ResponseEntity(iProductService.searchByName(name),HttpStatus.OK)
    }

}