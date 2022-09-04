package com.cj.product.controller;


import com.cj.product.service.SeataProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author zyf
 */
@RestController
@RequestMapping("/test/seata/com.cj.product")
public class SeataProductController {

    @Autowired
    private SeataProductService seataProductService;

    @GetMapping("/reduceStock")
    public BigDecimal reduceStock(@RequestParam Long productId, @RequestParam Integer count) {
        return seataProductService.reduceStock(productId, count);
    }
}
