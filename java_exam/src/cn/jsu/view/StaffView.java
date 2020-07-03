package cn.jsu.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffView extends JFrame {
    private JFrame f;//定义窗体
    private JMenuBar mb;//定义菜单栏
    private JMenu m;
    private JMenuItem drugItem;//定义"文件"和"子菜单"菜单
    private JMenuItem saleItem;
    private JMenuItem supplierItem;
    private JMenuItem closeItem;//定义条目“退出”和“子条目”菜单项
    private Dialog d;
    private JLabel lab;
    private JButton okBut;
    StaffView(){
        init();
    }
    public void init(){
        f=new JFrame("员工操作界面");
        f.setBounds(680,270,500,600);
        f.setLayout(new FlowLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mb=new JMenuBar();
        m=new JMenu("请选择");//创建“文件”菜单

        drugItem = new JMenuItem("查看药品信息");//创建“子菜单”菜单
        saleItem = new JMenuItem("查看营收信息");
        supplierItem = new JMenuItem("查看供应商信息");


        closeItem=new JMenuItem("退出");//创建“退出"菜单项

        m.add(drugItem);//将“子菜单”菜单添加到“文件”菜单上
        m.add(saleItem);
        m.add(supplierItem);

        m.add(closeItem);//将“退出”菜单项添加到“文件”菜单上

        mb.add(m);//将文件添加到菜单栏上

        f.setJMenuBar(mb);//将此窗体的菜单栏设置为指定的菜单栏。

        events();//加载事件处理

        f.setVisible(true);//设置窗体可见
    }
    public void events(){
            saleItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    new SaleView(false);
                }
            });
            drugItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    new DrugStaffView();
                }
            });
            closeItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    new LoginView();
                }
            });
            supplierItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    new SupplierStaffView();
                }
            });
    }
    public static void main(String[] args) {
        new StaffView();
    }
}

