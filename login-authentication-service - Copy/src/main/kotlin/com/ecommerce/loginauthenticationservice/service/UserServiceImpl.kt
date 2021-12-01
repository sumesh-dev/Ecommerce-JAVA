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
        return if (userRepository.findByEmail(userDao.email)==null) {
            userRepository.save(UserDao(userDao.firstName,userDao.lastName,userDao.email,userDao.password,userDao.role,null))
            "user created successfully"
        } else
            "user already have account with this mail id "
    }

    override fun getUserByEmail(email: String): Any {
        return userRepository.findByEmail(email) ?: "user does not exist"

    }

    override fun deleteUserByEmail(email: String): String {
        var user: UserDao? = userRepository.findByEmail(email)
        return if (user!==null) {
            userRepository.deleteById(user._id)
            "user deleted successfully"
        } else{
            "user does not exist"
        }
    }

    override fun getAllSellers(): Any {
        var sellers = userRepository.findAllSeller()
        return if (sellers!=null){
            sellers
        } else
            "No Seller exist"
    }

    override fun getAllCustomer(): Any {
        var customers = userRepository.findAllCustomer()
        return if (customers!=null){
            customers
        } else
            "No Seller exist"
    }

    override fun updateUserByEmail(email: String, userDao: UserDao): String {
        var user: UserDao? = userRepository.findByEmail(email)
        return if (user!==null) {
            userRepository.deleteById(user._id)
            userRepository.save(UserDao(userDao.firstName,userDao.lastName,userDao.email,userDao.password,userDao.role,null))
            "user updated successfully"
        } else {
            "user does not exist"
        }
    }

}