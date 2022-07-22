package com.jdbc.util;

import com.jdbc.connection.ConnectionTest;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * �������ݿ�Ĺ�����
 */
public class JDBCUtils {

    //��ȡ���ݿ�����
    public static Connection getConnection() throws Exception {
        //��ȡ�����ļ���Ϣ
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        //��������
        Class.forName(driver);

        //��ȡ����
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    //�ر���Դ
    public static void closeResource(Connection conn, PreparedStatement ps){
        //��Դ�Ĺر�
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
        //��Դ�Ĺر�
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
