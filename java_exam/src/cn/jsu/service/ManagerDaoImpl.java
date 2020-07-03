package cn.jsu.service;

import cn.jsu.dao.ManagerDao;
import cn.jsu.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDaoImpl implements ManagerDao {
    public  boolean Login(String username, String password) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        if(username==null||password==null)
            return false;
        try {
            conn = JDBCUtils.getConnection();
            String sql="select * from manager where username = ? and password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            rs=pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return false;
    }
}
