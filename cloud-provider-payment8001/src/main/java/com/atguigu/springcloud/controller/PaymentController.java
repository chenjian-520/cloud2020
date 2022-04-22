package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("********插入结果：serverPort->" + serverPort + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据库成功:serverPort->" + serverPort, result);
        } else {
            return new CommonResult(500, "插入数据库失败:serverPort->" + serverPort, null);
        }

    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {

        Payment paymentByid = paymentService.getPaymentById(id);
        log.info("********查询结果****:serverPort->：" + serverPort + paymentByid);

        if (paymentByid != null) {
            return new CommonResult(200, "查询成功:serverPort->" + serverPort, paymentByid);
        } else {
            return new CommonResult(500, "查询失败:serverPort->" + serverPort, null);
        }

    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("******element: " + element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPayment() {
        return serverPort;
    }

}
