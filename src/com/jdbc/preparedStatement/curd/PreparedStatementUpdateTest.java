package com.jdbc.preparedStatement.curd;

import com.jdbc.connection.ConnectionTest;
import com.jdbc.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用PreparedStatement来替换Statement，实现对数据表的增删改查操作
 */
public class PreparedStatementUpdateTest {

    @Test
    public void testDelete(){
        String sql = "delete from user where id = ?";
        update(sql,1);
    }

    //通用的增删改操作
    public void update(String sql,Object ...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for(int i = 0; i < args.length; i++){
                ps.setObject(i+1,args[i]);
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,ps);
        }

    }

    //修改user中一条数据
    @Test
    public void testUpdate() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //获取连接
            conn = JDBCUtils.getConnection();
            //操作数据库:预编译SQL语句，返回实例，填充占位符，执行SQL
            String sql = "update user set name = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"斌总");
            ps.setInt(2,1);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            JDBCUtils.closeResource(conn,ps);
        }
    }

    //像user中添加一条数据
    @Test
    public void testInsert() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
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
            connection = DriverManager.getConnection(url, user, password);
//        System.out.println(connection);

            //预编译sql语句，返回PreparedStatement实例
            String sql = "insert into user(id,name,age) value(?,?,?)";  //占位符
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            preparedStatement.setInt(1,1);
            preparedStatement.setString(2,"斌哥");
            preparedStatement.setInt(3,21);

            //执行操作
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //资源的关闭
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
