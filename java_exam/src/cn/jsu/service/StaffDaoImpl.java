package cn.jsu.service;

import cn.jsu.bean.Staff;
import cn.jsu.dao.StaffDao;
import cn.jsu.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDaoImpl implements StaffDao {
    @Override
    public boolean Login(String username, String password) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        if(username==null||password==null)
            return false;
        try {
            conn = JDBCUtils.getConnection();
            String sql="select * from staff where username = ? and password = ?";
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

    @Override
    /**
     * 添加员工信息
     */
    public int add(Staff sta) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn = JDBCUtils.getConnection();
            String sql="insert into staff  values(?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,sta.getS_id());
            pstmt.setString(2,sta.getS_no());
            pstmt.setString(3,sta.getS_name());
            pstmt.setString(4,sta.getS_sex());
            pstmt.setInt(5,sta.getS_age());
            pstmt.setString(6,sta.getS_phone());
            pstmt.setString(7,sta.getUsername());
            pstmt.setString(8,sta.getPassword());
            int count=pstmt.executeUpdate();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn);
        }
        return -1;
    }

    @Override
    /**
     * 打印员工表信息
     */
    public List<Staff> showAll() {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List<Staff> list=null;
        Staff sta=null;
        try {
            conn = JDBCUtils.getConnection();
            String sql="select * from staff";
            pstmt = conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            list=new ArrayList<>();

            while(rs.next()){
                int id =rs.getInt("s_id");
                String no=rs.getString("s_no");
                String name=rs.getString("s_name");
                String sex=rs.getString("s_sex");
                int age=rs.getInt("s_age");
                String phone=rs.getString("s_phone");
                String username=rs.getString("username");
                String password=rs.getString("password");
                sta=new Staff();
                sta.setS_id(id);
                sta.setS_phone(phone);
                sta.setS_no(no);
                sta.setS_name(name);
                sta.setS_sex(sex);
                sta.setS_age(age);
                sta.setUsername(username);
                sta.setPassword(password);
                list.add(sta);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return null;
    }

    @Override
    /**
     * 删除
     */
    public int delete(int id) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn=JDBCUtils.getConnection();
            String sql="delete from staff where s_id = ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            int count=pstmt.executeUpdate();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn);
        }
        return -1;
    }

    @Override
    public int update(int id) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn=JDBCUtils.getConnection();
            String sql="update staff set s_id=s_id-1 where s_id > ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            int count=pstmt.executeUpdate();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn);
        }
        return -1;
    }

    @Override
    public int update(Staff sta) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn=JDBCUtils.getConnection();
            String sql="update staff set s_no = ?,s_name = ?, s_sex = ?, s_age = ?,s_phone = ?,username = ?,password =? where s_id =?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,sta.getS_no());
            pstmt.setString(2,sta.getS_name());
            pstmt.setString(3,sta.getS_sex());
            pstmt.setInt(4,sta.getS_age());
            pstmt.setString(5,sta.getS_phone());
            pstmt.setString(6,sta.getUsername());
            pstmt.setString(7,sta.getPassword());
            pstmt.setInt(8,sta.getS_id());
            int count=pstmt.executeUpdate();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
        }
        return -1;
    }

    @Override
    public Staff show(int s_id) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        Staff sta=null;
        try {
            conn = JDBCUtils.getConnection();
            String sql="select * from staff where s_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,s_id);
            rs=pstmt.executeQuery();
            while(rs.next()){
                int id =rs.getInt("s_id");
                String no=rs.getString("s_no");
                String name=rs.getString("s_name");
                String sex=rs.getString("s_sex");
                int age=rs.getInt("s_age");
                String phone=rs.getString("s_phone");
                String username=rs.getString("username");
                String password=rs.getString("password");
                sta=new Staff();
                sta.setS_id(id);
                sta.setS_phone(phone);
                sta.setS_no(no);
                sta.setS_name(name);
                sta.setS_sex(sex);
                sta.setS_age(age);
                sta.setUsername(username);
                sta.setPassword(password);

            }
            return sta;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return null;
    }


}
