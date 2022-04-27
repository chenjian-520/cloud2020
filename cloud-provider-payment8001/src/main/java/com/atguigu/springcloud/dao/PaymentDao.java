package com.atguigu.springcloud.dao;


import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
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
@Mapper
public interface PaymentDao {

    /**
     * create
     * @param payment payment
     * @return Payment
     */
    int create(Payment payment);

    /**
     * getPaymentById
     * @param id id
     * @return Payment
     */
    Payment getPaymentById(@Param("id") Long id);

}
