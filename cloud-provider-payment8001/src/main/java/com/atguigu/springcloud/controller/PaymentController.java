package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("********插入结果：" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据库成功", result);
        } else {
            return new CommonResult(500, "插入数据库失败", null);
        }

    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {

        Payment paymentByid = paymentService.getPaymentById(id);
        log.info("********查询结果****：" + paymentByid);

        if (paymentByid != null) {
            return new CommonResult(200, "查询成功", paymentByid);
        } else {
            return new CommonResult(500, "查询失败", null);
        }

    }

}
