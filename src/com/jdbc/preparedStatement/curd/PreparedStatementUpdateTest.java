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
 * ʹ��PreparedStatement���滻Statement��ʵ�ֶ����ݱ����ɾ�Ĳ����
 */
public class PreparedStatementUpdateTest {

    @Test
    public void testDelete(){
        String sql = "delete from user where id = ?";
        update(sql,1);
    }

    //ͨ�õ���ɾ�Ĳ���
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

    //�޸�user��һ������
    @Test
    public void testUpdate() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //��ȡ����
            conn = JDBCUtils.getConnection();
            //�������ݿ�:Ԥ����SQL��䣬����ʵ�������ռλ����ִ��SQL
            String sql = "update user set name = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"����");
            ps.setInt(2,1);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //�ر���Դ
            JDBCUtils.closeResource(conn,ps);
        }
    }

    //��user�����һ������
    @Test
    public void testInsert() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
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
            connection = DriverManager.getConnection(url, user, password);
//        System.out.println(connection);

            //Ԥ����sql��䣬����PreparedStatementʵ��
            String sql = "insert into user(id,name,age) value(?,?,?)";  //ռλ��
            preparedStatement = connection.prepareStatement(sql);
            //���ռλ��
            preparedStatement.setInt(1,1);
            preparedStatement.setString(2,"���");
            preparedStatement.setInt(3,21);

            //ִ�в���
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //��Դ�Ĺر�
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
