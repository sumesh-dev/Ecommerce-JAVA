package com.ecommerce.paymentservice.service

import com.ecommerce.paymentservice.client.UserClient
import com.ecommerce.paymentservice.dao.OrdersRepository
import com.ecommerce.paymentservice.model.Orders
import com.ecommerce.paymentservice.model.UserAmount
import com.razorpay.RazorpayClient
import org.bson.types.ObjectId
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class payService: IPayService{

    @Autowired
    lateinit var userClient: UserClient

    @Autowired
    lateinit var ordersRepository: OrdersRepository

    @Value("\${razorPay.key}")
    private val key: String? = null

    @Value("\${razorpay.secret}")
    private val secret: String? = null

    override fun createOrder(email: String, userAmount: UserAmount): String {

        val products: MutableList<ObjectId> = userClient.getProductsFromCart(email)
        if(products.size > 0) {
            val client = RazorpayClient(key, secret)
            val txnid = UUID.randomUUID().toString().replace("-", "")
            println(txnid)
            val options = JSONObject()
            options.put("amount", userAmount.amount * 100)
            options.put("currency", "INR")
            options.put("receipt", txnid)

            // creating order
            val order = client.Orders.create(options)
            println(order.toString())
            // save the order info in database
            ordersRepository.save(
                Orders(
                    email, userAmount.amount, txnid, LocalDateTime.now(), products,
                    order.get("id") as String, "created", null
                )
            )
            return order.toString()
        }
        else{
            return "no Product in cart"
        }
    }

    override fun updateOrder(orderId: String, paymentId: String, status: String): String {
        val order = ordersRepository.findByRazorpayOrderId(orderId)
        order.paymentId = paymentId
        order.paymentStatus = status
        ordersRepository.save(order)
        return "updated successfully"
    }
}