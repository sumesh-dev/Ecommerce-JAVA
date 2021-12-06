package com.ecommerce.userservice.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter(){

    @Autowired
    private val jwtRequestFilter: JwtRequestFilter? = null

    override fun configure(httpSecurity: HttpSecurity) {

        httpSecurity
            .csrf().disable()
//            .cors().disable()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .antMatchers("users/getAllCustomer","users/getAllSeller").hasRole("admin")
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

}