package com.ecommerce.userservice.controller

import com.ecommerce.userservice.config.JwtRequestFilter
import com.ecommerce.userservice.dao.IUserRepository
import com.ecommerce.userservice.models.UserDao
import com.ecommerce.userservice.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Email

@RestController
@RequestMapping("/users")
class UserController {
    @Autowired
    private lateinit var iUserService: IUserService

    @Autowired
    private lateinit var iUserRepository: IUserRepository

    @Autowired
    private val jwtRequestFilter: JwtRequestFilter? = null

    @GetMapping("/me")
    fun getUserByEmail(): ResponseEntity<Any> {
        return ResponseEntity<Any>(jwtRequestFilter?.email?.let { iUserService.getUserByEmail(it) }, HttpStatus.OK)
    }

    @DeleteMapping("/")
    fun deleteUserByEmail(): ResponseEntity<String> {
        return ResponseEntity<String>(jwtRequestFilter?.email?.let { iUserService.deleteUserByEmail(it) }, HttpStatus.OK)
    }

    @PutMapping("/")
    fun updateUserByEmail(@Valid @RequestBody userDao: UserDao): ResponseEntity<String> {
        return ResponseEntity<String>(jwtRequestFilter?.email?.let { iUserService.updateUserByEmail(it,userDao) }, HttpStatus.OK)
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