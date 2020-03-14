package com.lagou.edu.utils;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author 应癫
 */
public class DruidUtils {

    private DruidUtils(){
    }

    private static DruidDataSource druidDataSource = new DruidDataSource();


    static {
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://10.0.101.91:3306/zdy_mybatis?useUnicode=true&amp;characterEncoding=utf8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("Wu&12035417");

    }

    public static DruidDataSource getInstance() {
        return druidDataSource;
    }

}
