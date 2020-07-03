package cn.jsu.dao;

import cn.jsu.bean.Drug;

import java.util.List;

public interface DrugDao {
    int add(Drug sta);
    List<Drug> showAll();
    int delete(int id);
    int update(int id);
    int update(Drug sta);
    Drug show(int id);
    List<Drug> sort(boolean flag);

}
