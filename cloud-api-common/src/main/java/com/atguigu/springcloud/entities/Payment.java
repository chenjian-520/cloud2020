package com.atguigu.springcloud.entities;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author cj
 * @Title:
 * @JdkVersion JDK1.8
 * @ClassName
 * @Version 1.0.0
 * @date 2022/2/1614:38
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "对象Payment", value = "Payment")
public class Payment implements Serializable {

    private Long id;
    private String serial;

}
