package com.jdbc.preparedStatement.curd;

import com.jdbc.pojo.User;
import com.jdbc.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Check {

    @Test
    public void testQureyUser() throws Exception {
        String sql = "select * from user where id = ?";
        User user = queryUser(sql, 1);
        System.out.println(user);
    }

    public User queryUser(String sql, Object ...args) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for(int i = 0; i < args.length; i++){
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            //��ȡ�������Ԫ���ݣ�ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            //ͨ��ResultSetMetaData��ȡ������е�����
            int columnCount = rsmd.getColumnCount();

            if(rs.next()){
                User user = new User();
                for(int i = 0; i < columnCount; i++){
                    Object columnValue = rs.getObject(i + 1);
                    //��ȡ������
                    String columnName = rsmd.getColumnName(i + 1);
                    //ͨ�����䣬��ָ�����Ը�ֵ
                    Field field = User.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(user,columnValue);
                }
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }

    @Test
    public void testQuery1() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from user where id = 1";
            ps = conn.prepareStatement(sql);

            //ִ�У������ؽ����
            resultSet = ps.executeQuery();
            //��������
            if(resultSet.next()){
                //��ȡ���ֶε�ֵ
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);

    //            System.out.println(id+name+age);

                //�����ݷ�װΪһ������
                User user = new User(id, name, age);
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //�ر���Դ
            JDBCUtils.closeResource(conn,ps,resultSet);
        }
    }
}
