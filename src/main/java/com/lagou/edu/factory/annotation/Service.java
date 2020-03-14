package com.lagou.edu.factory.annotation;

import java.lang.annotation.*;

/**
 * service注解
 * @author: james.wu
 * @email: james.wu@nf-3.com
 * @date: 2020/3/13
 * @module: 类所属模块
 * @version: v1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {

    //注解值
    String value() default "";
}
