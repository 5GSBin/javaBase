package com.jdbc.connection;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ��ȡ���ݿ�����
 */

public class ConnectionTest {

    //����һ
    @Test
    public void testConnection1() throws SQLException {

        //��ȡDriverʵ�������
        Driver driver = new com.mysql.jdbc.Driver();

        //���ݿ�url
        String url = "jdbc:mysql://localhost:3306/111";
        //���˺������װ
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","1234");
        //��ȡ����
        Connection connect = driver.connect(url, info);

        System.out.println(connect);
    }

    //������������һ�ĵ���
    @Test
    public void testConnection2() throws Exception {
        //1.��ȡDriverʵ�������ʹ�÷���
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //�ṩҪ���ӵ����ݿ�
        //���ݿ�url
        String url = "jdbc:mysql://localhost:3306/111";
        //���˺������װ
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","1234");
        //��ȡ����
        Connection connect = driver.connect(url, info);

        System.out.println(connect);
    }

    //��ʽ����ʹ��DriverManager�滻Driver
    @Test
    public void testConnection3() throws Exception {

        //1.��ȡDriverʵ�������ʹ�÷���
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //ע������
        DriverManager.registerDriver(driver);

        //���ݿ�url
        String url = "jdbc:mysql://localhost:3306/111";
        //�˺�����
        String user = "root";
        String password = "1234";
        //��ȡ����
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

    }

    //��ʽ�ģ�ֱ�Ӽ������������Լ�ע��
    @Test
    public void testConnection4() throws Exception {

        //1.��ȡDriverʵ�������ʹ�÷���
        //����Driver�࣬��̬����ִ�У����Զ�ע������
        Class.forName("com.mysql.jdbc.Driver");
        //��Է������������в�����ʡ��
//        Driver driver = (Driver) clazz.newInstance();

        //ע������
//        DriverManager.registerDriver(driver);

        //���ݿ�url
        String url = "jdbc:mysql://localhost:3306/111";
        //�˺�����
        String user = "root";
        String password = "1234";
        //��ȡ����
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

    }

    //��ʽ�壺�����ݿ�������Ҫ����Ϣ�����������ļ��У�ͨ����ȡ�����ļ��ķ�ʽ����ȡ����

    /**
     * �հ�ô���
     * ���ʵ�������ݺʹ���ķ���
     * ����޸����ݣ����Ա�������ظ������ֱ���޸����ݼ���
     */
    @Test
    public void testConnection5() throws Exception {
        //��ȡ�����ļ���Ϣ
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
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
        System.out.println(connection);

    }


}
