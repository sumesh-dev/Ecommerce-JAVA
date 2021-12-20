package com.ecommerce.paymentservice.controller

import com.ecommerce.paymentservice.config.JwtRequestFilter
import com.ecommerce.paymentservice.model.UserAmount
import com.ecommerce.paymentservice.service.IPayService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/payment")
class PaymentController {
    @Autowired
    lateinit var payService:IPayService

    @Autowired
    lateinit var jwtRequestFilter: JwtRequestFilter


    @PostMapping("/createOrder")
    fun createOrder(@RequestBody userAmount: UserAmount): ResponseEntity<String> {
        return ResponseEntity<String>(payService.createOrder(jwtRequestFilter.email,userAmount),HttpStatus.OK)
    }

    @PostMapping("/updateOrder")
    fun updateOrder(@RequestBody data:Map<String,Objects>):ResponseEntity<String>{
        return ResponseEntity<String>(payService.updateOrder(data["order_id"].toString(),data["payment_id"].toString(),data["status"].toString()),HttpStatus.OK)
    }

//    @GetMapping("/getAllOrders")
//    fun getAllOrders():ResponseEntity<MutableList<Orders>>{
//        return ResponseEntity(ordersRepository.findAll(),HttpStatus.OK)
//    }
//
//    @GetMapping("/orders")
//    fun getAllOrders():ResponseEntity<MutableList<Orders>>{
//        return ResponseEntity(ordersRepository.findAllOrderByEmail(jwtRequestfilter.email),HttpStatus.OK)
//    }


}


//    @GetMapping("/proceedPayment/{email}")
//    fun proceedPayment(@PathVariable email:String) : String?{
//        val uuid = UUID.randomUUID()
//        val txnid = uuid.toString().replace("-","")
//        var firstname = "sumesh"
//        println(txnid)
//        val productInfo = "educational purpose"
//        var hashString:String = """$MERCHANT_KEY|$txnid|10000|$productInfo|$firstname|$email|||||||||||$MERCHANT_SALT"""
//        var hash = javaIntegrationKit.hashCal("SHA-512",hashString)
//        val urlParams: MutableMap<String, String> = HashMap()
//        urlParams["txnid"]=txnid
//        urlParams["key"]= MERCHANT_KEY
//        urlParams["surl"]="http://localhost:9004/payment/success"
//        urlParams["furl"]="http://localhost:9004/payment/failure"
//        urlParams["hash"]= hash
//        urlParams["amount"]= "10000"
//        urlParams["phone"]="9999999999"
//        urlParams["service_provider"] = "payu_paisa"
//        urlParams["productinfo"] = productInfo
//        urlParams["firstname"] = firstname
//        urlParams["email"] = email
//        urlParams["lastname"] = "k"
//
//        var headers = HttpHeaders()
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON))
//        val request: HttpEntity<MutableMap<String, String>> = HttpEntity<MutableMap<String,String>>(urlParams,headers)

//        var request : HttpEntity<MutableMap<String,String>> = HttpEntity<MutableMap<String,String>>()
//        var response :ResponseEntity<String> = restTemplate.exchange("https://test.payu.in/_payment",HttpMethod.POST,request,String)
//        println(response.headers.toString())
//        println(response.headers.location.toString())
//        return response.body
//        var response :ResponseEntity<Unit> = restTemplate.getForEntity<Unit>("https://test.payu.in/_payment",Unit)
//        println(response.headers.toString())
    //        var responseEntity :ResponseEntity<Unit> = payuMoney.payment(urlParams)
//        println(responseEntity.headers.toString())
//        responseEntity.getHeaders().forEach{k->
//            println("Key is:"+ k.getKey());
//            println("Values are:"+k.getValue().stream().collect(Collectors.joining())}
//        println(response)

//    }
//    @PostMapping("/createOrder/{email}/{amount}")
//    fun createOrder(@PathVariable email:String,@PathVariable amount:String): String{
//     var razorpayClient = RazorpayClient("rzp_test_XZmE9htlGHPrr3","uOeBhzaxVD0LwctitTtF4JfG")
//        val options = JSONObject()
//        options.put("amount", 5000)
//        options.put("currency", "INR")
//        options.put("receipt", "txn_123456")
//       //create orders
//        val order: Order = razorpayClient.Orders.create(options)
//
//        return "done"
//    }
//
//    @PostMapping("/success")
//    fun success(){
//
//    }
//
//    @

//}