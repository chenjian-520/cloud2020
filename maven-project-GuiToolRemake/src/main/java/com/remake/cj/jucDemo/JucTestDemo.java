package com.remake.cj.jucDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * @author cj
 * @Title:
 * @JdkVersion JDK1.8
 * @ClassName
 * @Version 1.0.0
 * @date 2022/11/2321:53
 * @Description:
 */
public class JucTestDemo {
    public static void main(String[] args) {
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "默认线程" + Thread.currentThread().getName();
        }, Executors.newFixedThreadPool(2));
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("默认线程runAsync" + Thread.currentThread().getName());
        }, Executors.newFixedThreadPool(2));

    }
}
