package com.ecommerce.loginauthenticationservice.controller

import com.ecommerce.loginauthenticationservice.helper.JwtTokenUtil
import com.ecommerce.loginauthenticationservice.models.JwtRequest
import com.ecommerce.loginauthenticationservice.models.UserDao
import com.ecommerce.loginauthenticationservice.service.CustomUserDetailsService
import com.ecommerce.loginauthenticationservice.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
class AuthenticationController {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var  jwtTokenUtil: JwtTokenUtil

    @Autowired
    private lateinit var customUserDetailsService: CustomUserDetailsService

    @Autowired
    private lateinit var iUserService: IUserService

    @PostMapping("/signup")
    fun addUser(@Valid @RequestBody userDao: UserDao): ResponseEntity<String> {
        return ResponseEntity<String>(iUserService.addUser(userDao), HttpStatus.OK)
    }

    @PostMapping("/login")
    @ResponseBody
    @Throws(Exception::class)
    fun createAuthenticationToken(@Valid @RequestBody jwtRequest: JwtRequest, response: HttpServletResponse): ResponseEntity<String> {
        try{
            this.authenticationManager.authenticate(UsernamePasswordAuthenticationToken(jwtRequest.email,jwtRequest.password))
        }
//        catch (e: UsernameNotFoundException,){
//            throw Exception("Invalid Credentials")
//        }
        catch(e: BadCredentialsException){
            return ResponseEntity<String>("Invalid Crendentials",HttpStatus.BAD_REQUEST)
//            throw Exception("Invalid Credentials")
        }

        val userDetails: UserDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.email)
        val token: String? = this.jwtTokenUtil.generateToken(userDetails)
        val cookie = Cookie("JwtToken",token)
        response.addCookie(cookie)
        return ResponseEntity<String>("Login successfully",HttpStatus.OK)
    }

    @GetMapping("/logout")
    @ResponseBody
    fun logout(request: HttpServletRequest,response: HttpServletResponse):ResponseEntity<String>{
        var responseCookie: Cookie? = request.cookies?.find { c->c.name=="JwtToken" }
        var jwtToken: String? = responseCookie?.value
//        println(jwtToken)

        return if (jwtToken != null) {
            val cookie = Cookie("JwtToken", null)
            cookie.maxAge = 0
            response.addCookie(cookie)
            ResponseEntity<String>("Logout Successfully", HttpStatus.OK)
            ResponseEntity<String>("Logout Successfully", HttpStatus.OK)
        } else{
            ResponseEntity<String>("User must Login first", HttpStatus.BAD_REQUEST)
        }
    }
}