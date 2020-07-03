package cn.jsu.service;

import cn.jsu.bean.Drug;
import cn.jsu.bean.Sale;
import cn.jsu.bean.Supplier;
import cn.jsu.dao.SupplierDao;
import cn.jsu.util.JDBCUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public void addSupplier(Supplier sp) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn = JDBCUtils.getConnection();
            String sql="insert into Supplier values(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,sp.getNo());
            pstmt.setString(2,sp.getName());
            pstmt.setString(3,sp.getPlace());
            pstmt.setString(4,sp.getPhone());
            int count=pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn);
        }
    }

    @Override
    public List<Supplier> showAll() {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List<Supplier> list=null;
        Supplier sp=null;
        try {
            conn = JDBCUtils.getConnection();
            String sql="select * from supplier";
            pstmt = conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            list=new ArrayList<>();
            while(rs.next()){
                String no=rs.getString("s_no");
                String name=rs.getString("s_name");
                String place=rs.getString("s_place");
                String phone=rs.getString("s_phone");
                sp=new Supplier();
                sp.setNo(no);
                sp.setName(name);
                sp.setPlace(place);
                sp.setPhone(phone);
                list.add(sp);
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
            fw = new FileWriter("C:\\Users\\85264\\Desktop\\supplier.txt");
            for(T sp:list){
                Supplier s=(Supplier) sp;
                fw.write(" 供应商编码:  "+s.getNo());
                fw.write(" 供应商名:  "+s.getName());
                fw.write(" 供应商地址:  "+s.getPlace());
                fw.write(" 供应商手机:  "+s.getPhone());
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
