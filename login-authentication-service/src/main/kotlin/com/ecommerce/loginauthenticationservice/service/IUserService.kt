package com.ecommerce.loginauthenticationservice.service

import com.ecommerce.loginauthenticationservice.models.UserDao

interface IUserService {
    fun addUser(userDao: UserDao):String?
    fun getUserByEmail(email: String):Any?
    fun deleteUserByEmail(email: String):String
    fun getAllSellers():MutableList<UserDao>?
    fun getAllCustomer():MutableList<UserDao>?
    fun updateUserByEmail(email:String, userDao: UserDao):String
}