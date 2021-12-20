package com.ecommerce.userservice.service

import com.ecommerce.userservice.models.UserDao
import org.bson.types.ObjectId

interface IUserService {
    fun addUser(userDao: UserDao):String?
    fun getUserByEmail(email: String):Any?
    fun deleteUserByEmail(email: String):String
    fun getAllSellers(): Any
    fun getAllCustomer(): Any
    fun updateUserByEmail(email:String, userDao: UserDao):String
//    fun updateOrder(email: String,id:ObjectId):String
}