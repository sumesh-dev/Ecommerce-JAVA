package com.ecommerce.userservice.service

import com.ecommerce.userservice.client.ProductClient
import com.ecommerce.userservice.dao.IUserRepository
import com.ecommerce.userservice.models.Product
import com.ecommerce.userservice.models.UserDao
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CartServiceImpl: CartService {

    @Autowired
    lateinit var userRepository: IUserRepository

    @Autowired
    lateinit var productClient: ProductClient

    override fun addToCart(product_id: ObjectId,email: String): String {
          val userDao: UserDao? = userRepository.findByEmail(email)
        return if (userDao != null) {
            userRepository.deleteById(userDao._id)
            userRepository.save(UserDao(userDao.firstName,userDao.lastName,userDao.email,userDao.password,userDao.role,product_id))
            "Product successfully added to cart"
        } else{
            "error occurred"
        }
    }

    override fun deleteToCart(product_id: ObjectId,email: String): String {
        val userDao: UserDao? = userRepository.findByEmail(email)
        return if (userDao != null) {
            userDao.productInCart?.remove(product_id)
//            userRepository.deleteById(userDao._id)
            userRepository.save(userDao)
            "Product successfully deleted from cart"
        } else{
            "error occurred"
        }

    }

    override fun showAllItemsInCart(email: String): MutableList<Product>? {
        val list:MutableList<Product>?= null
        val cartProduct: MutableList<ObjectId>? = userRepository.findByEmail(email)?.productInCart
        return if (cartProduct != null) {
            for(product_id in cartProduct){
                list?.add(productClient.getProduct(product_id))
            }
            list
        } else{
            null
        }

    }

}