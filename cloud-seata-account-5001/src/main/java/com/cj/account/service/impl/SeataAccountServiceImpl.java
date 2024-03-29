package com.cj.account.service.impl;


import com.cj.account.entity.SeataAccount;
import com.cj.account.mapper.SeataAccountMapper;
import com.cj.account.service.SeataAccountService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Description: TODO
 * @author: zyf
 * @date: 2022/01/24
 * @version: V1.0
 */
@Slf4j
@Service
public class SeataAccountServiceImpl implements SeataAccountService {
    @Resource
    private SeataAccountMapper accountMapper;

    /**
     * 事务传播特性设置为 REQUIRES_NEW 开启新的事务
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void reduceBalance(Long userId, BigDecimal amount) {
        log.info("=============ACCOUNT START=================");
        System.out.println("seata全局事务id====================>" + RootContext.getXID());
        SeataAccount account = accountMapper.selectById(userId);
        Assert.notNull(account, "用户不存在");
        BigDecimal balance = account.getBalance();
        log.info("下单用户{}余额为 {},商品总价为{}", userId, balance, amount);

        if (balance.compareTo(amount) == -1) {
            log.warn("用户 {} 余额不足，当前余额:{}", userId, balance);
            throw new RuntimeException("余额不足");
        }
        log.info("开始扣减用户 {} 余额", userId);
        BigDecimal currentBalance = account.getBalance().subtract(amount);
        account.setBalance(currentBalance);
        accountMapper.updateById(account);
        log.info("扣减用户 {} 余额成功,扣减后用户账户余额为{}", userId, currentBalance);
        log.info("=============ACCOUNT END=================");
    }
}
