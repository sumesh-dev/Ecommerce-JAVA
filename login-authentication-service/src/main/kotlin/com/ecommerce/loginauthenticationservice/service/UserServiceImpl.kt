package com.ecommerce.loginauthenticationservice.service

import com.ecommerce.loginauthenticationservice.dao.IUserRepository
import com.ecommerce.loginauthenticationservice.models.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl :IUserService {

    @Autowired
    private lateinit var userRepository: IUserRepository

    override fun addUser(userDao: UserDao): String? {
//         try{
//            userRepository.findByEmail(user.email)
//            return "user already exist"
//        }
//        catch () {
            userRepository.save(userDao)
            return "user created successfully"
//        }
    }

    override fun getUserByEmail(email: String): Any? {
        return userRepository.findByEmail(email)
    }

    override fun deleteUserByEmail(email: String): String {
        userRepository.deleteByEmail(email)
        return "user deleted successfully"
    }

    override fun getAllSellers(): MutableList<UserDao>? {
        return userRepository.findAllSeller()
    }

    override fun getAllCustomer(): MutableList<UserDao>? {
        return userRepository.findAllCustomer()
    }

    override fun updateUserByEmail(email: String, userDao: UserDao): String {
        userRepository.deleteByEmail(email)
        userRepository.save(userDao)
        return "user updated successfully"
    }

}