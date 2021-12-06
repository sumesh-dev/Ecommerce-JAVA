package com.ecommerce.loginauthenticationservice.models


import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Document("users")
class UserDao{

    @Autowired
    private var bCryptPasswordEncoder = BCryptPasswordEncoder(10)

    @Id
    lateinit var _id: ObjectId

    @field:NotBlank(message = "first name is mandatory")
    lateinit var firstName:String

    @field:NotBlank(message = "last name is mandatory")
    lateinit var lastName:String

    @field:Email(message = "Email id is mandatory")
    @field:NotBlank(message = "email is mandatory")
//    @Indexed(unique=true)
     lateinit var email:String

    @field:NotBlank(message = "password is mandatory")
    lateinit var password:String

    @field:NotBlank(message = "Role is mandatory")
    lateinit var role:String

//    @DocumentReference(collection = "Products")
    private var productInCart: MutableList<ObjectId> ?= null

    constructor()

    constructor(email: String, password: String) {
        this.email = email
        this.password = password
    }

    constructor(
        firstName: String,lastName: String,email: String, password: String,role: String, productInCart: MutableList<ObjectId>?)
    {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = this.bCryptPasswordEncoder.encode(password)
        this.role = "ROLE_"+role.lowercase()
        this.productInCart = productInCart
    }


}

