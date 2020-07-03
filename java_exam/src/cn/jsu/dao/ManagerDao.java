package cn.jsu.dao;

import cn.jsu.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ManagerDao {
    boolean Login(String username,String password);


}
