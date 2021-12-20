package com.sumesh.productservice.service

import com.sumesh.productservice.dao.IUserRepository
import com.sumesh.productservice.model.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService : UserDetailsService {

    @Autowired
    private lateinit var iUserRepository: IUserRepository



    override fun loadUserByUsername(email: String): UserDetails {
        val user: UserDao = iUserRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("User not found with username: $email")
//        println(user.toString())
        val list = mutableListOf(SimpleGrantedAuthority(user.role))
//        println("${user.email}${user.password}${list.toString()}")
        return User(user.email, user.password,list)
    }

}