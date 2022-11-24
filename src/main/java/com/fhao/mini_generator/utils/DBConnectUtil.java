package com.fhao.mini_generator.utils;

import com.fhao.mini_generator.bean.ConfigInfo;
import com.fhao.mini_generator.bean.GlobalConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * author: FHao
 * create time: 2022-11-21 18:51
 * description: 获取数据库链接，使用双重锁检查机制，防止并发问题
 */
public class DBConnectUtil {
    private volatile static Connection conn = null;
    private static Logger logger = LoggerFactory.getLogger(DBConnectUtil.class);

    public static Connection getConnection() {
        try {
            if (conn == null) {
                synchronized (DBConnectUtil.class) {
                    if (conn == null) {
                        long start = System.currentTimeMillis();
                        Class.forName(GlobalConfiguration.getConfigInfo().getDriver());
                        ConfigInfo config = GlobalConfiguration.getConfigInfo();
                        String url = config.getUrl();
                        conn = DriverManager.getConnection(url, config.getUserName(), config.getPassWord());
                        logger.info("已连接数据库，耗时：" + (System.currentTimeMillis() - start) + "ms");
                    }
                }
            }
        } catch (Exception e) {
            logger.error("数据库连接失败！", e);
        }
        return conn;
    }

}
