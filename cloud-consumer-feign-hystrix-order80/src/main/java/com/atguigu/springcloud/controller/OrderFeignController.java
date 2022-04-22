package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/payment/feign/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable Integer id) {
        return paymentFeignService.paymentInfo_OK(id);
    }

    @GetMapping(value = "/payment/feign/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable Integer id) {
        return paymentFeignService.paymentInfo_TimeOut(id);
    }
}
