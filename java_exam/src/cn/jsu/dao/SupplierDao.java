package cn.jsu.dao;

import cn.jsu.bean.Supplier;

import java.util.List;

public interface SupplierDao {
    void addSupplier(Supplier sp);
    List<Supplier> showAll();
    <T>boolean Save(List<T> list);
}
