package com.ecommerce.loginauthenticationservice.dao

import com.ecommerce.loginauthenticationservice.models.UserDao
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface IUserRepository: MongoRepository<UserDao,Long>{

    @Query("{email:?0}")
    fun findByEmail(email: String?): UserDao
    @Query("db.User.remove({email:?0})")
    fun deleteByEmail(email: String)
    @Query("{role:seller}")
    fun findAllSeller(): MutableList<UserDao>?
    @Query("{role:customer}")
    fun findAllCustomer(): MutableList<UserDao>?

}