package com.ecommerce.loginauthenticationservice.service

import com.ecommerce.loginauthenticationservice.dao.IUserRepository
import com.ecommerce.loginauthenticationservice.models.Product
import com.ecommerce.loginauthenticationservice.models.UserDao
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import javax.validation.constraints.Email

@Service
class CartServiceImple:CartService {

    @Autowired
    lateinit var userRepository: IUserRepository

    @Autowired
    lateinit var restTemplate: RestTemplate

    override fun addToCart(product_id: ObjectId,email: String): String {
          var userDao:UserDao = userRepository.findByEmail(email)
            userDao.productInCart?.add(product_id)
          userRepository.deleteByEmail(email)
         userRepository.save(userDao)
        return "Product successfully added to cart"
    }

    override fun deleteToCart(product_id: ObjectId,email: String): String {
        var userDao:UserDao = userRepository.findByEmail(email)
        userDao.productInCart?.remove(product_id)
        userRepository.deleteByEmail(email)
        userRepository.save(userDao)
        return "Product deleted from cart"
    }

    override fun showAllItemsInCart(email: String): List<Product>? {
        var list:List<Product>?= null
        for(product in userRepository?.findByEmail(email)?.productInCart!!){
           list = listOf<Product>(this.restTemplate.getForObject("http://localhost:9002/getProductById/"+product))
        }
        return list
    }

}