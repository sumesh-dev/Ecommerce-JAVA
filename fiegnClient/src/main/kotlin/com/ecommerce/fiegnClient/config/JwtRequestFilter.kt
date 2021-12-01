package com.ecommerce.fiegnClient.config

import com.ecommerce.loginauthenticationservice.service.CustomUserDetailsService
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.constraints.Email

@Component
class JwtRequestFilter : OncePerRequestFilter() {

    @Autowired
    private val customUserDetailsService: CustomUserDetailsService? = null

    @Autowired
    private lateinit var  jwtTokenUtil: JwtTokenUtil

    companion object{
        lateinit var email: String
    }

    @Throws(ServletException::class, IOException::class)
    protected override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val requestTokenHeader: String? = request.getHeader("Authorization")
        var username: String? = null
        var jwtToken: String? = null
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7)
                println("send Jwt token $jwtToken")
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken)
                println("username from jwt token $username")
                email = username
            }

            catch (e: IllegalArgumentException) {
                println("Unable to get JWT Token")
            }

            catch (e: ExpiredJwtException) {
                println("JWT Token has expired")
            }
        }

        else {
            logger.warn("JWT Token does not begin with Bearer String")
        }

        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            val userDetails: UserDetails? = customUserDetailsService?.loadUserByUsername(username)

            // if token is valid configure Spring Security to manually set
            // authentication


            if (userDetails.let { jwtTokenUtil.validateToken(jwtToken, it) }!!) {
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails?.getAuthorities()
                )
                usernamePasswordAuthenticationToken.setDetails(WebAuthenticationDetailsSource().buildDetails(request))
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken)
            }
        }
        chain.doFilter(request, response)
    }

}