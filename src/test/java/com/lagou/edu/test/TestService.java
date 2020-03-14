package com.lagou.edu.test;

import com.lagou.edu.factory.BeanFactory;
import com.lagou.edu.factory.ProxyFactory;
import com.lagou.edu.service.TransferService;
import org.junit.Test;

/**
 * @author: james.wu
 * @email: james.wu@nf-3.com
 * @date: 2020/3/10
 * @module: 类所属模块
 * @version: v1.0
 */
public class TestService {

    @Test
    public void test() throws Exception {
        TransferService transferService = (TransferService) BeanFactory.getBean("transferService");
        transferService.transfer("6029621011000", "6029621011001", 100);
    }

    @Test
    public void test1() throws Exception {
        ProxyFactory proxyFactory = (ProxyFactory) BeanFactory.getBean("proxyFactory");
        TransferService transferService = (TransferService) proxyFactory.getJdkProxy(BeanFactory.getBean("transferService"));
        transferService.transfer("6029621011000", "6029621011001", 100);
    }
}
