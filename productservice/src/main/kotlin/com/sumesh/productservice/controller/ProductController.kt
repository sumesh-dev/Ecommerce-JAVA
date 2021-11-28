package com.sumesh.productservice.controller

import com.sumesh.productservice.entity.Product
import com.sumesh.productservice.service.IProductService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/product")
class ProductController {

    @Autowired
    private lateinit var iProductService: IProductService

    @PostMapping("/addProduct")
    fun addNewProduct(@Valid @RequestBody product: Product):ResponseEntity<String>{
        return ResponseEntity(iProductService.addProduct(product),HttpStatus.OK)
    }

    @GetMapping("/getProductById/{id}")
    fun getProductById(@PathVariable id:ObjectId):ResponseEntity<Any>{
        return ResponseEntity(iProductService.getProductById(id),HttpStatus.OK)
    }

    @DeleteMapping("/deleteProductById/{id}")
    fun deleteProductById(@PathVariable id: ObjectId):ResponseEntity<String>{
        return ResponseEntity(iProductService.deleteProductById(id),HttpStatus.OK)
    }

    @GetMapping("/getAllProducts")
    fun getAllProducts():ResponseEntity<MutableList<Product>>{
        return ResponseEntity(iProductService.getAllProducts(),HttpStatus.OK)
    }

    @GetMapping("/getAllProductsBySeller/{sellerId}")
    fun getAllProductsBySelller(@PathVariable sellerId:ObjectId):ResponseEntity<MutableList<Product>>{
        return ResponseEntity(iProductService.getAllProductsByParticularSeller(sellerId),HttpStatus.OK)
    }

    @PatchMapping("/updateProductsById/{id}")
    fun updateProductsById(@Valid @RequestBody product:Product,@PathVariable id: ObjectId ):ResponseEntity<Any?>{
        return ResponseEntity(iProductService.updateProductById(id, product),HttpStatus.OK)
    }

    @GetMapping("/searchByName/{name}")
    fun SearchByName(@PathVariable name:String):ResponseEntity<MutableList<Product>>{
        return ResponseEntity(iProductService.searchByName(name),HttpStatus.OK)
    }

}