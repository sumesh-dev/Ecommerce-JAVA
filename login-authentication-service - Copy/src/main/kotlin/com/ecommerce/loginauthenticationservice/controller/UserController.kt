package com.ecommerce.loginauthenticationservice.controller

import com.ecommerce.loginauthenticationservice.models.UserDao
import com.ecommerce.loginauthenticationservice.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Email

@RestController

class UserController {
    @Autowired
    private lateinit var iUserService: IUserService

    @PostMapping("/register")
    fun addUser(@Valid @RequestBody userDao: UserDao): ResponseEntity<String> {
        return ResponseEntity<String>(iUserService.addUser(userDao), HttpStatus.OK)
    }

    @GetMapping("/getUserByEmail/{email}")
    fun getUserByEmail(@Valid @Email @PathVariable email:String): ResponseEntity<Any> {
        return ResponseEntity<Any>(iUserService.getUserByEmail(email), HttpStatus.OK)
    }

    @DeleteMapping("/deleteUserByEmail/{email}")
    fun deleteUserByEmail(@PathVariable email: String): ResponseEntity<String> {
        return ResponseEntity<String>(iUserService.deleteUserByEmail(email), HttpStatus.OK)
    }

    @PutMapping("/updateUserByEmail/{email}")
    fun updateUserByEmail(@PathVariable email: String, @Valid @RequestBody userDao: UserDao): ResponseEntity<String> {
        return ResponseEntity<String>(iUserService.updateUserByEmail(email,userDao), HttpStatus.OK)
    }

    @GetMapping("/getAllCustomer")
    fun getAllCustomer(): ResponseEntity<Any> {
        return ResponseEntity<Any>(iUserService.getAllCustomer(), HttpStatus.OK)
    }

    @GetMapping("/getAllSeller")
    fun getAllSeller(): ResponseEntity<Any> {
        return ResponseEntity<Any>(iUserService.getAllSellers(), HttpStatus.OK)
    }
}