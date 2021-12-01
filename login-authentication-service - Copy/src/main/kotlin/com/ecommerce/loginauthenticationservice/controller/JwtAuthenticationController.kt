package com.ecommerce.loginauthenticationservice.controller

import com.ecommerce.loginauthenticationservice.helper.JwtTokenUtil
import com.ecommerce.loginauthenticationservice.models.JwtRequest
import com.ecommerce.loginauthenticationservice.service.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
class JwtAuthenticationController {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var  jwtTokenUtil: JwtTokenUtil

    @Autowired
    private lateinit var customUserDetailsService: CustomUserDetailsService

    @GetMapping("/logout")
    @ResponseBody
    fun logout(response: HttpServletResponse):ResponseEntity<String>{
        println("inside logout")
        val cookie = Cookie("JwtToken",null)
        cookie.maxAge=0
        response.addCookie(cookie)
        return ResponseEntity<String>("Logout Successfully",HttpStatus.OK)
    }

    @PostMapping("/login")
    @ResponseBody
    @Throws(Exception::class)
    fun createAuthenticationToken(@Valid @RequestBody jwtRequest: JwtRequest,response: HttpServletResponse): ResponseEntity<String> {
        try{
            this.authenticationManager.authenticate(UsernamePasswordAuthenticationToken(jwtRequest.email,jwtRequest.password))
        }
        catch (e: UsernameNotFoundException){
            throw Exception("Invalid Credentials")
        }

        val userDetails: UserDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.email)
        val token: String? = this.jwtTokenUtil.generateToken(userDetails)
//        println("Token Genrated succesfully $token")

        val cookie = Cookie("JwtToken",token)
        response.addCookie(cookie)
        return ResponseEntity<String>("Login successfully",HttpStatus.OK)
    }

}