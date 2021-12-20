package com.ecommerce.paymentservice.config

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
            .antMatchers("/order/showAllOrders").hasRole("admin")
            .antMatchers("/order/showMyOrders").hasAnyRole("customer","admin")
            .anyRequest()
//            .permitAll()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

}