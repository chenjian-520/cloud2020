package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cj
 * @Title:
 * @JdkVersion JDK1.8
 * @ClassName
 * @Version 1.0.0
 * @date 2022/2/1810:47
 * @Description:
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable Integer id) {
        String paymentInfoOk = paymentService.paymentInfo_OK(id);
        log.info("**********result:" + paymentInfoOk);
        return paymentInfoOk;
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable Integer id) {
        String paymentInfoTimeout = paymentService.paymentInfo_TimeOut(id);
        log.info("**********result:" + paymentInfoTimeout);
        return paymentInfoTimeout;
    }

}
