package com.ecommerce.loginauthenticationservice

import com.ecommerce.loginauthenticationservice.models.UserDao
import com.ecommerce.loginauthenticationservice.service.IUserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class LoginAuthenticationServiceApplicationTests {

	@Autowired
	private lateinit var iUserService: IUserService


//	@Test
//	fun signUp(){
//		val userDao = UserDao()
//		userDao.firstName = "Test"
//		userDao.lastName = "service"
//		userDao.email = "testservice@gmail.com"
//		userDao.password = "123456789"
//		userDao.role = "customer"
//		Assertions.assertEquals("user created successfully", iUserService.addUser(userDao))
//	}


//	fun contextLoads() {
//	}

}
