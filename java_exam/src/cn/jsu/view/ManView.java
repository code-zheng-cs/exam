package cn.jsu.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManView extends JFrame {
    private JFrame f;//定义窗体
    private JMenuBar mb;//定义菜单栏
    private JMenu m;
    private JMenuItem staffItem,supplierItem,drugItem,saleItem;
    private JMenuItem showSaleItem,saveSaleItem;
    private JMenuItem closeItem;//定义条目“退出”和“子条目”菜单项
    ManView(){
        init();
    }
    public void init(){
        f=new JFrame("管理员操作界面");//创建窗体对象
        f.setBounds(700,200,500,600);//设置窗体位置和大小
        f.setLayout(new FlowLayout());//设置窗体布局为流式布局
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mb=new JMenuBar();//创建菜单栏

        m=new JMenu("请选择");//创建“文件”菜单

        drugItem = new JMenuItem("管理药品信息");//创建“子菜单”菜单
        saleItem = new JMenuItem("管理营收信息");
        staffItem = new JMenuItem("管理员工信息");
        supplierItem = new JMenuItem("管理供应商信息");

        closeItem=new JMenuItem("退出");//创建“退出"菜单项

        m.add(drugItem);//将“子菜单”菜单添加到“文件”菜单上
        m.add(saleItem);
        m.add(staffItem);
        m.add(supplierItem);

        m.add(closeItem);//将“退出”菜单项添加到“文件”菜单上

        mb.add(m);//将文件添加到菜单栏上

        f.setJMenuBar(mb);//将此窗体的菜单栏设置为指定的菜单栏。

        Events();

        f.setVisible(true);//设置窗体可见
    }
     public void Events(){
        staffItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new StaffManView();
            }
        });

        drugItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new DrugManView();
            }
        });
         supplierItem.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 f.dispose();
                 new SupplierManView();
             }
         });
        closeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new LoginView();
            }
        });
        saleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new SaleView(true);
            }
        });
}

    public static void main(String[] args) {
        new ManView();
    }
}
