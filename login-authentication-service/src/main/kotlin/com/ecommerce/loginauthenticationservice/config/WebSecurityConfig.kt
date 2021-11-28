package com.ecommerce.loginauthenticationservice.config

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.beans.factory.annotation.Autowired
import com.ecommerce.loginauthenticationservice.config.JwtAuthenticationEntryPoint
import org.springframework.security.core.userdetails.UserDetailsService
import com.ecommerce.loginauthenticationservice.config.JwtRequestFilter
import com.ecommerce.loginauthenticationservice.service.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlin.Throws
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.lang.Exception

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint? = null

    @Autowired
    private val jwtUserDetailsService: UserDetailsService? = null

    @Autowired
    private val jwtRequestFilter: JwtRequestFilter? = null

    @Autowired
    private lateinit var customUserDetailsService: CustomUserDetailsService

//    @Autowired
//    @Throws(Exception::class)
//    fun configureGlobal(auth: AuthenticationManagerBuilder) {
//        // configure AuthenticationManager so that it knows from where to load
//        // user for matching credentials
//        // Use BCryptPasswordEncoder
//        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder())
//    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
//        return BCryptPasswordEncoder()
        return NoOpPasswordEncoder.getInstance()
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

//    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {

        httpSecurity
            .csrf().disable()
            .cors().disable()
            .authorizeRequests()
            .antMatchers("/register","/authenticate","/user")
            .permitAll().anyRequest()
            .authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        httpSecurity.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter::class.java)


//        // We don't need CSRF for this example
//        httpSecurity.csrf().disable() // dont authenticate this particular request
//            .authorizeRequests().antMatchers("/authenticate", "/register")
//            .permitAll().anyRequest() // all other requests need to be authenticated
//        .authenticated().and().exceptionHandling() // make sure we use stateless session; session won't be used to
//        // store user's state.
//        .authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//        // Add a filter to validate the tokens with every request
//        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(customUserDetailsService)?.passwordEncoder(passwordEncoder())
    }
}