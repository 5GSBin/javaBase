package com.jdbc.connection;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 获取数据库连接
 */

public class ConnectionTest {

    //方法一
    @Test
    public void testConnection1() throws SQLException {

        //获取Driver实现类对象
        Driver driver = new com.mysql.jdbc.Driver();

        //数据库url
        String url = "jdbc:mysql://localhost:3306/111";
        //讲账号密码封装
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","1234");
        //获取连接
        Connection connect = driver.connect(url, info);

        System.out.println(connect);
    }

    //方法二：方法一的迭代
    @Test
    public void testConnection2() throws Exception {
        //1.获取Driver实现类对象，使用反射
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //提供要连接的数据库
        //数据库url
        String url = "jdbc:mysql://localhost:3306/111";
        //讲账号密码封装
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","1234");
        //获取连接
        Connection connect = driver.connect(url, info);

        System.out.println(connect);
    }

    //方式三：使用DriverManager替换Driver
    @Test
    public void testConnection3() throws Exception {

        //1.获取Driver实现类对象，使用反射
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //注册驱动
        DriverManager.registerDriver(driver);

        //数据库url
        String url = "jdbc:mysql://localhost:3306/111";
        //账号密码
        String user = "root";
        String password = "1234";
        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

    }

    //方式四：直接加载驱动，会自己注册
    @Test
    public void testConnection4() throws Exception {

        //1.获取Driver实现类对象，使用反射
        //加载Driver类，静态代码执行，会自动注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //相对方法三，下两行操作可省略
//        Driver driver = (Driver) clazz.newInstance();

        //注册驱动
//        DriverManager.registerDriver(driver);

        //数据库url
        String url = "jdbc:mysql://localhost:3306/111";
        //账号密码
        String user = "root";
        String password = "1234";
        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

    }

    //方式五：将数据库连接需要的信息声明在配置文件中，通过读取配置文件的方式，获取链接

    /**
     * 终版好处：
     * 解耦，实现了数据和代码的分离
     * 如果修改数据，可以避免程序重复打包，直接修改数据即可
     */
    @Test
    public void testConnection5() throws Exception {
        //读取配置文件信息
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
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
        System.out.println(connection);

    }


}
