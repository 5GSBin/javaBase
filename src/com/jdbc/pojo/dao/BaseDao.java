package com.jdbc.pojo.dao;

import com.jdbc.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDao {
    //通用增删改操作----考虑了事务
    public int update(Connection conn, String sql, Object ...args) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for(int i = 0; i < args.length; i++){
                ps.setObject(i+1, args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(null, ps);
        }
        return 0;
    }
}
