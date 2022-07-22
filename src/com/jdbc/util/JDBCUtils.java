package com.jdbc.util;

import com.jdbc.connection.ConnectionTest;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 操作数据库的工具类
 */
public class JDBCUtils {

    //获取数据库连接
    public static Connection getConnection() throws Exception {
        //读取配置文件信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        //加载驱动
        Class.forName(driver);

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    //关闭资源
    public static void closeResource(Connection conn, PreparedStatement ps){
        //资源的关闭
        try {
            if(ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs){
        //资源的关闭
        try {
            if(rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
