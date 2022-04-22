package com.atguigu.springcloud.service;

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

    public String paymentInfo_OK(Integer id);

    public String paymentInfo_TimeOut(Integer id);

}
