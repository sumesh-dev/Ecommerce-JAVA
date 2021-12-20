package com.ecommerce.loginauthenticationservice.service

import com.ecommerce.loginauthenticationservice.dao.IUserRepository
import com.ecommerce.loginauthenticationservice.models.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class UserServiceImpl :IUserService {

    @Value("\${account.email}")
    private lateinit var email: String
    @Value("\${account.password}")
    private lateinit var password: String

    @Autowired
    private lateinit var userRepository: IUserRepository

    override fun addUser(userDao: UserDao): String? {

        return if (userRepository.findByEmail(userDao.email)==null) {
            userRepository.save(UserDao(userDao.firstName,userDao.lastName,userDao.email,userDao.password,userDao.role,null))
            "user created successfully"
        } else
            "user already have account with this mail id "
    }

    override fun addDefaultAccount(): String {
            return if (userRepository.findByEmail(email) == null) {
                userRepository.save(UserDao("admin", "acoount", email, password, "admin", null))
                "default account created successfully"
            } else
                "default admin already exist you can login with that account"
        }

}