package com.ecommerce.loginauthenticationservice.service

import com.ecommerce.loginauthenticationservice.models.UserDao

interface IUserService {
    fun addUser(userDao: UserDao):String?
    fun getUserByEmail(email: String):Any?
    fun deleteUserByEmail(email: String):String
    fun getAllSellers(): Any
    fun getAllCustomer(): Any
    fun updateUserByEmail(email:String, userDao: UserDao):String
}