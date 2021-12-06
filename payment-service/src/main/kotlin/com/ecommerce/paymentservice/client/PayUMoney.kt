package com.ecommerce.paymentservice.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.data.mongodb.repository.Query
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(url = "https://test.payu.in/_payment", name = "PayUMoney")
interface PayUMoney {
    @PostMapping
}