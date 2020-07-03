package cn.jsu.service;

import cn.jsu.bean.Drug;
import cn.jsu.bean.Sale;
import cn.jsu.dao.SaleDao;
import cn.jsu.util.JDBCUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleDaoImpl implements SaleDao {
    @Override
    public int addSale(Drug dr) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn = JDBCUtils.getConnection();
            String sql="insert into sale(s_no,s_name,s_num,s_price,s_sum)  values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,dr.getNo());
            pstmt.setString(2,dr.getName());
            pstmt.setInt(3,dr.getNum());
            pstmt.setDouble(4,dr.getPrice());
            pstmt.setDouble(5,dr.getPrice()*dr.getNum());
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
    public Drug show(String d_no) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        Drug dr=null;
        try {
            conn = JDBCUtils.getConnection();
            String sql="select * from drug where d_no = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,d_no);
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

    public boolean update(String code,int number,boolean flag){
        Connection conn=null;
        PreparedStatement pstmt=null;
        Drug dr=null;
        int num=0;
        try {
            dr=new Drug();
            dr=show(code);
            if(flag==true){
                num=number+dr.getNum();
            }
            else{
                if((dr.getNum()-number)>=0)
                {
                    num=dr.getNum()-number;
                }
                else
                    return false;
            }
            conn=JDBCUtils.getConnection();
            String sql="update drug set d_num = ? where d_no =?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,num);
            pstmt.setString(2,code);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
        }
        return false;
    }

    @Override
    public List<Sale> showAll() {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List<Sale> list=null;
        Sale s=null;
        try {
            conn = JDBCUtils.getConnection();
            String sql="select * from sale";
            pstmt = conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            list=new ArrayList<>();

            while(rs.next()){
                int id =rs.getInt("s_id");
                String no=rs.getString("s_no");
                String name=rs.getString("s_name");
                int num=rs.getInt("s_num");
                double price=rs.getDouble("s_price");
                double sum=rs.getDouble("s_sum");
                s=new Sale();
                s.setId(id);
                s.setName(name);
                s.setNo(no);
                s.setPrice(price);
                s.setNum(num);
                s.setSum(sum);
                list.add(s);
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
    public <T> boolean Save(List<T> list) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Users\\85264\\Desktop\\sale.txt");
            for(T sta:list){
              Sale s=(Sale)sta;
              fw.write(" 序号:  "+s.getId());
              fw.write(" 药品编码:  "+s.getNo());
              fw.write(" 药品名:  "+s.getName());
              fw.write(" 药品数量:  "+s.getNum());
              fw.write(" 药品价格:  "+String.valueOf(s.getPrice()));
              fw.write(" 药品单笔总价:  "+String.valueOf(s.getSum()));
                fw.write("\r\n");
            }
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
