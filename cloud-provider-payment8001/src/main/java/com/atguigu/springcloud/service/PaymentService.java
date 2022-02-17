package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author cj
 * @Title:
 * @JdkVersion JDK1.8
 * @ClassName
 * @Version 1.0.0
 * @date 2022/2/1715:06
 * @Description:
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentByid(@Param("id") Long id);

}
