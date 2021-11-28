package com.ecommerce.loginauthenticationservice.service

import com.ecommerce.loginauthenticationservice.dao.IUserRepository
import com.ecommerce.loginauthenticationservice.models.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService : UserDetailsService {
    @Autowired
    private lateinit var iUserRepository:IUserRepository

    override fun loadUserByUsername(email: String): UserDetails {
        val user: UserDao = iUserRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("User not found with username: $email")
        return User(user.email, user.password, ArrayList());
    }

//    @Throws(UsernameNotFoundException::class)
//    override fun loadUserByUsername(email: String): UserDetails? {
//        val user: UserDao? = iUserRepository.findByEmail(email)
//            ?: throw UsernameNotFoundException("User not found with username: $email")
//        if (user != null) {
//            return User(user.email, user.password, ArrayList());
//        }
//        return null
//    }
//
//    fun save(userDao:UserDao): UserDao {
//        return iUserRepository.save(userDao)
//    }
}