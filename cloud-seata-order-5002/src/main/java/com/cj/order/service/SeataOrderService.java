package com.cj.order.service;


import com.cj.order.dto.PlaceOrderRequest;

/**
 * @Description: 订单接口
 * @author: zyf
 * @date: 2022/01/24
 * @version: V1.0
 */
public interface SeataOrderService {
    /**
     * 下单
     *
     * @param placeOrderRequest 订单请求参数
     */
    void placeOrder(PlaceOrderRequest placeOrderRequest);
}
