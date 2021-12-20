package com.ecommerce.paymentservice.service

import com.ecommerce.paymentservice.dao.IUserRepository
import com.ecommerce.paymentservice.dao.OrdersRepository
import com.ecommerce.paymentservice.model.Orders
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderServiceImp:IOrderService {

    @Autowired
    lateinit var ordersRepository: OrdersRepository

    @Autowired
    lateinit var iUserRepository: IUserRepository

    override fun getOrder(id: ObjectId,email:String): Any {
      val order :Orders= ordersRepository.findById(id).get()
        if(order.email==email || iUserRepository.findByEmail(email)?.role=="admin") {
            return order
        }
        return "You are not authorize to see the order"
    }

    override fun showAllOrders(): Any {
        var orders:MutableList<Orders> = ordersRepository.findAll()
        return if(orders.size<1){
             "No Orders Exist"
        }
        else
            orders
    }

    override fun showMyOrders(email: String): Any {
       var orders:MutableList<Orders> = ordersRepository.findAllOrderByEmail(email)
        return if(orders.size<1){
             "No Orders Exist"
        }
        else
            orders
    }
}