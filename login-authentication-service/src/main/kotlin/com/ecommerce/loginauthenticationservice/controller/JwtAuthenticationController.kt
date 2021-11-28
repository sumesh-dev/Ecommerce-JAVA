package com.ecommerce.loginauthenticationservice.controller

import com.ecommerce.loginauthenticationservice.config.JwtTokenUtil
import com.ecommerce.loginauthenticationservice.models.JwtRequest
import com.ecommerce.loginauthenticationservice.models.JwtResponse
import com.ecommerce.loginauthenticationservice.service.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class JwtAuthenticationController {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var  jwtTokenUtil: JwtTokenUtil

    @Autowired
    private lateinit var customUserDetailsService: CustomUserDetailsService

    @RequestMapping(value = ["/authenticate"], method = [RequestMethod.POST])
    @Throws(Exception::class)
    fun createAuthenticationToken(@Valid @RequestBody authenticationRequest: JwtRequest): ResponseEntity<*> {
        println("inside authentication")
        try{
            this.authenticationManager.authenticate(UsernamePasswordAuthenticationToken(authenticationRequest.email,authenticationRequest.password))
        }
        catch (e: UsernameNotFoundException){
            throw Exception("Bad Credentials")
        }

        var userDetails: UserDetails = this.customUserDetailsService.loadUserByUsername(authenticationRequest.email)
        var token: String? = this.jwtTokenUtil.generateToken(userDetails)
        println("Token Genrated succesfully $token")
        return ResponseEntity.ok<Any>(JwtResponse(token))


//        authenticate(authenticationRequest.email, authenticationRequest.password)
//        val userDetails: UserDetails? = userDetailsService
//            ?.loadUserByUsername(authenticationRequest.email)
//        val token: String? = userDetails?.let { jwtTokenUtil?.generateToken(it) }
//        return ResponseEntity.ok<Any>(token?.let { JwtResponse(it) })
    }


    @Throws(Exception::class)
    private fun authenticate(username: String, password: String) {
        try {
            authenticationManager!!.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
    }

}