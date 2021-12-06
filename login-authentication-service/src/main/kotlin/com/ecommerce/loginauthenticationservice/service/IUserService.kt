package com.ecommerce.loginauthenticationservice.service

import com.ecommerce.loginauthenticationservice.models.UserDao

interface IUserService {
    fun addUser(userDao: UserDao):String?
}