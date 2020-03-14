package com.lagou.edu.factory.annotation;

import java.lang.annotation.*;

/**
 * 自动注入注解
 * @author: james.wu
 * @email: james.wu@nf-3.com
 * @date: 2020/3/13
 * @module: 类所属模块
 * @version: v1.0
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
}
