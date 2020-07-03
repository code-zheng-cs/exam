package cn.jsu.dao;

import cn.jsu.bean.Drug;
import cn.jsu.bean.Sale;

import java.util.List;

public interface SaleDao {
    int addSale(Drug dr);
    Drug show (String d_no);
    boolean update(String code,int number,boolean flag);
    List<Sale> showAll();
    <T>boolean Save(List<T> list);
}
