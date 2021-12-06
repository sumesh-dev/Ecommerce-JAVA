package com.sumesh.productservice.dao

import com.sumesh.productservice.model.UserDao
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface IUserRepository: MongoRepository<UserDao,ObjectId>{

    @Query("{email:?0}")
    fun findByEmail(email: String?): UserDao?
}