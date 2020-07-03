package cn.jsu.service;

import cn.jsu.bean.Drug;
import cn.jsu.dao.DrugDao;
import cn.jsu.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugDaoImpl implements DrugDao {
    @Override
    public int add(Drug dr) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn = JDBCUtils.getConnection();
            String sql="insert into drug  values(?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,dr.getId());
            pstmt.setString(2,dr.getName());
            pstmt.setString(3,dr.getNo());
            pstmt.setString(4,dr.getSpecies());
            pstmt.setDouble(5,dr.getPrice());
            pstmt.setInt(6,dr.getNum());
            pstmt.setString(7,dr.getPlace());
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
    public List<Drug> showAll() {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List<Drug> list=null;
        Drug dr=null;
        try {
            conn = JDBCUtils.getConnection();
            String sql="select * from drug";
            pstmt = conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            list=new ArrayList<>();

            while(rs.next()){
                int id =rs.getInt("d_id");
                String no=rs.getString("d_no");
                String name=rs.getString("d_name");
                String species=rs.getString("d_species");
                int num=rs.getInt("d_num");
                String place=rs.getString("d_place");
                double price=rs.getDouble("d_price");
                dr=new Drug();
                dr.setId(id);
                dr.setName(name);
                dr.setNo(no);
                dr.setSpecies(species);
                dr.setPrice(price);
                dr.setNum(num);
                dr.setPlace(place);
                list.add(dr);
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
    public int delete(int id) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn=JDBCUtils.getConnection();
            String sql="delete from drug where d_id = ?";
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
            String sql="update drug set d_id=d_id-1 where d_id > ?";
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
    public int update(Drug dr) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn=JDBCUtils.getConnection();
            String sql="update drug set d_no = ?,d_name = ?, d_num = ?, d_species = ?,d_place = ?,d_price =? where d_id =?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,dr.getNo());
            pstmt.setString(2,dr.getName());
            pstmt.setInt(3,dr.getNum());
            pstmt.setString(4,dr.getSpecies());
            pstmt.setString(5,dr.getPlace());
            pstmt.setDouble(6,dr.getPrice());
            pstmt.setInt(7,dr.getId());
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
    public Drug show(int d_id) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        Drug dr=null;
        try {
            conn = JDBCUtils.getConnection();
            String sql="select * from drug where d_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,d_id);
            rs=pstmt.executeQuery();
            while(rs.next()){
                int id =rs.getInt("d_id");
                String no=rs.getString("d_no");
                String name=rs.getString("d_name");
                String species=rs.getString("d_species");
                int num=rs.getInt("d_num");
                String place=rs.getString("d_place");
                double price=rs.getDouble("d_price");

                dr=new Drug();
                dr.setId(id);
                dr.setName(name);
                dr.setNo(no);
                dr.setSpecies(species);
                dr.setPrice(price);
                dr.setNum(num);
                dr.setPlace(place);
            }
            return dr;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return null;
    }

    @Override
    public List<Drug> sort(boolean flag) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List<Drug> list=null;
        Drug dr=null;
        String str=null;
        try {
            conn = JDBCUtils.getConnection();
            if(flag==true){
                //降序
                str="DESC";
            }
            else{
                //升序
                str="ASC";
            }
            String sql="select * from drug  order by d_price "+str;
            pstmt = conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            list=new ArrayList<>();

            while(rs.next()){
                int id =rs.getInt("d_id");
                String no=rs.getString("d_no");
                String name=rs.getString("d_name");
                String species=rs.getString("d_species");
                int num=rs.getInt("d_num");
                String place=rs.getString("d_place");
                double price=rs.getDouble("d_price");
                dr=new Drug();
                dr.setId(id);
                dr.setName(name);
                dr.setNo(no);
                dr.setSpecies(species);
                dr.setPrice(price);
                dr.setNum(num);
                dr.setPlace(place);
                list.add(dr);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return null;
    }
}
