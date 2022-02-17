package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author cj
 * @Title:
 * @JdkVersion JDK1.8
 * @ClassName
 * @Version 1.0.0
 * @date 2022/2/1614:59
 * @Description:
 */
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentByid(@Param("id") Long id);

}
