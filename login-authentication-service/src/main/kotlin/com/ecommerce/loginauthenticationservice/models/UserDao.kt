package com.ecommerce.loginauthenticationservice.models


import lombok.Data
import lombok.NoArgsConstructor
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Data
@NoArgsConstructor
@Document("users")
class UserDao{
    @Autowired
    private  lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Id
    private lateinit var _id: ObjectId

    @field:NotBlank(message = "first name is mandatory")
    lateinit var firstName:String

    @field:NotBlank(message = "last name is mandatory")
    lateinit var lastName:String

    @field:Email(message = "Email id is mandatory")
    @field:NotBlank(message = "email is mandatory")
    @Indexed(unique=true)
     lateinit var email:String

    @field:NotBlank(message = "password is mandatory")
    lateinit var password:String

    @field:NotBlank(message = "Role is mandatory")
    lateinit var role:String

    @DocumentReference(collection = "Products")
     var productInCart: MutableList<ObjectId> ?= null

    constructor(
        _id: ObjectId,
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        role: String,
        productInCart: MutableList<ObjectId>?
    ) {
        this._id = _id
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
        this.role = role
        this.productInCart = productInCart
    }

    constructor(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        role: String,
        productInCart: MutableList<ObjectId>?
    ) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
        this.role = role
        this.productInCart = productInCart
    }

    constructor(firstName: String, lastName: String, email: String, password: String, role: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
        this.role = role
    }


}

