package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class OrderFeignController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/payment/feign/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable Integer id) {
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping(value = "/payment/feign/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable Integer id) {
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }
}
