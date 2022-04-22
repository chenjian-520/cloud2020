package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author cj
 * @Title:
 * @JdkVersion JDK1.8
 * @ClassName
 * @Version 1.0.0
 * @date 2022/2/1715:08
 * @Description:
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     * 正常访问
     *
     * @param id id
     * @return String
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_OK , id:" + id + "\t" + "O(∩_∩)O哈哈~";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_OK , id:" + id + "\t" + "O(∩_∩)O哈哈~" + "耗时：3S";
    }
}
