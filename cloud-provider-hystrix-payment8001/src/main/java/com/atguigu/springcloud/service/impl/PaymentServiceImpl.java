package com.atguigu.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler" ,commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_OK , id:" + id + "\t" + "O(∩_∩)O哈哈~" + "耗时：3S";
    }

    public String  paymentInfo_TimeOutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_TimeOutHandler , id:" + id + "\t" + "兜底哥们" ;
    }


    // ======服务熔断
    @Override
    @HystrixCommand(fallbackMethod ="paymentCircuitBreaker_fallback",commandProperties = {
            //服务降级是否启用，是否执行回调函数
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            //该属性用来没置在燎动时间窗中，断路器熔断的最小请求数。例如，默认该值为20的时候,
            //如果滚动时间窗(默以10秒)内仅收到了19个请求，即使这19个请求都失败了， 断路器也不会打开。
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            //该属性用来没置在燎动时间窗中，表示在熔动时间窗中，在请求数量超过
            // circuitBreaker. requestVolumeThreshold的情况下，如果错误请求数的百分比超过50,
            //就把断路器没置为”打开”状态，否则就设置为"关闭”状态。
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            //该属性用来没置当断路器打开之后的休眠时间窗。休眠时间窗结束之后,
            //会将断路器置为"半开”状态，尝试熔断的请求命令，如果依然失败就将断路器继续设置为”打开”状态,
            //如果成功就没置为"关闭”状态。
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("********id 不能为负数");
        }
        String UUID = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+UUID;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能为负数，请稍后再试。id:"+id;
    }

}
