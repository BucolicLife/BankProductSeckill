package com.sanxiangbank.seckill;

import com.alibaba.druid.pool.DruidDataSource;
import com.sanxiangbank.seckill.util.Sm3Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class SeckillApplicationTests {

    //    @Test
//    void contextLoads() {
//    }
    //DI注入数据源
    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
//        //看一下默认数据源
//        System.out.println(dataSource.getClass());
//        //获得连接
//        Connection connection =   dataSource.getConnection();
//        System.out.println(connection);
//        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
//        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
//        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
//        //关闭连接
//        connection.close();
    }

    @Test
    public void sm3Test() throws Exception {
        String password = "bnmbnm";
        String ans = Sm3Util.sm3bcHex(password.getBytes());
        System.out.println(ans);

        System.out.println(ans);
    }
}
