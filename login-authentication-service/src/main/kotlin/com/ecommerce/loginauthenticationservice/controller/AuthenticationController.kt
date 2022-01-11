package com.ecommerce.loginauthenticationservice.controller

import com.ecommerce.loginauthenticationservice.helper.JwtTokenUtil
import com.ecommerce.loginauthenticationservice.models.JwtRequest
import com.ecommerce.loginauthenticationservice.models.UserDao
import com.ecommerce.loginauthenticationservice.service.CustomUserDetailsService
import com.ecommerce.loginauthenticationservice.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
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

//    @CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
    @PostMapping("/signup")
    fun addUser(@Valid @RequestBody userDao: UserDao): ResponseEntity<String> {
        return if(userDao.role.lowercase() == "admin" ||((userDao.role.lowercase()!="customer")&&(userDao.role.lowercase()!="seller"))){
            ResponseEntity<String>("Role can either customer or seller", HttpStatus.BAD_REQUEST)
        } else
            ResponseEntity<String>(iUserService.addUser(userDao), HttpStatus.OK)
    }

    @GetMapping("/activeDefaultAccount")
    fun addDefaultAccount(): ResponseEntity<String> {
       return ResponseEntity<String>(iUserService.addDefaultAccount(),HttpStatus.OK)
    }

    @PostMapping("/adminSignup")
    fun adminSignup(@Valid @RequestBody userDao: UserDao):ResponseEntity<String> {
        return if((userDao.role.lowercase()!="admin")&&(userDao.role.lowercase()!="customer")&&(userDao.role.lowercase()!="seller")){
            ResponseEntity<String>("Role can only be customer or seller or admin", HttpStatus.BAD_REQUEST)
        } else
            ResponseEntity<String>(iUserService.addUser(userDao), HttpStatus.OK)
    }

    @PostMapping("/login")
    @ResponseBody
    @Throws(Exception::class)
    fun createAuthenticationToken(@Valid @RequestBody jwtRequest: JwtRequest, response: HttpServletResponse): ResponseEntity<MutableMap<String,String>> {
        val data: MutableMap<String, String> = mutableMapOf()
        try{
            this.authenticationManager.authenticate(UsernamePasswordAuthenticationToken(jwtRequest.email,jwtRequest.password))
        }
//        catch (e: UsernameNotFoundException,){
//            throw Exception("Invalid Credentials")
//        }
        catch(e: BadCredentialsException){
            data["msg"] = "Invalid Crendentials"
            return ResponseEntity<MutableMap<String,String>>(data,HttpStatus.BAD_REQUEST)
//            throw Exception("Invalid Credentials")
        }

        val userDetails: UserDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.email)
        val token: String = this.jwtTokenUtil.generateToken(userDetails)!!
        val cookie = Cookie("JwtToken",token)
        cookie.maxAge = 5*60*60*1000
//        cookie.isHttpOnly = true
//        cookie.secure = true
//        cookie.domain = "ecommerce-react-app.s3.ap-south-1.amazonaws.com"
        response.addCookie(cookie)
//        val headers = response.getHeaders(HttpHeaders.SET_COOKIE)
//        response.setHeader(HttpHeaders.SET_COOKIE,String.format("%s; %s", headers, "SameSite=NONE;Secure"))

        data["msg"] = "Login successful"
        data["JwtToken"] = token
        return ResponseEntity<MutableMap<String,String>>(data,HttpStatus.OK)
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