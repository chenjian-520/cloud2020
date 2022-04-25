package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderFeignController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/payment/feign/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable Integer id) {
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping(value = "/payment/feign/hystrix/timeout/{id}")
    @HystrixCommand
//    @HystrixCommand(defaultFallback = "paymentInfo_TimeOutHandler" ,commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    public String paymentInfo_TimeOut(@PathVariable Integer id) {
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    public String  paymentInfo_TimeOutHandler(Integer id) {
        return "我是80消费者：对方支付系统繁忙，10秒后运行 兜底哥们" ;
    }

    // 下面是全区fallbalk方法
    public String payment_Global_FallbackMethod(){
        return "Global方法异常处理，请稍后再试！！！" ;
    }
}
