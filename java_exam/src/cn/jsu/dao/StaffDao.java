package cn.jsu.dao;

import cn.jsu.bean.Staff;

import java.sql.ResultSet;
import java.util.List;

public interface StaffDao {
    boolean Login(String username,String password);
    int add(Staff sta);
    List<Staff> showAll();
    int delete(int id);
    int update(int id);
    int update(Staff sta);
    Staff show(int id);
}
