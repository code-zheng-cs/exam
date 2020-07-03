package cn.jsu.view;

import cn.jsu.bean.Drug;
import cn.jsu.bean.Supplier;
import cn.jsu.service.DrugDaoImpl;
import cn.jsu.service.SupplierDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
public class AddSupplierView extends JFrame{
    JFrame f;
    JLabel lb1=new JLabel("当前是供应商信息录入界面" );
    JTextField supplier_no,name,phone,price,num,place;
    JButton submit,back;//声明相应的操作的按钮
    JPanel p1,p2,p3,p4,pv,ph,pb;//调节布局的通道

    AddSupplierView(){
        f=new JFrame();
        name=new JTextField(10);
        supplier_no=new JTextField(10);
        phone=new JTextField(15);
        place=new JTextField(18);

        back=new JButton("返回");
        submit=new JButton("提交");

        pb=new JPanel();
        pb.add(lb1,JLabel.CENTER);

        p1=new JPanel();
        p1.add(new JLabel("供应商编号:",JLabel.CENTER));
        p1.add(supplier_no);

        p2=new JPanel();
        p2.add(new JLabel("供应商名:",JLabel.CENTER));
        p2.add(name);

        p3=new JPanel();
        p3.add(new JLabel("供应商地址:",JLabel.CENTER));
        p3.add(place);

        p4=new JPanel();
        p4.add(new JLabel("供应商手机",JLabel.CENTER));
        p4.add(phone);

        pv=new JPanel();
        pv.setLayout(new GridLayout(7,1));   //把pv组件设置成第七行1列的网格布局

        pv.add(p1);
        pv.add(p2);
        pv.add(p3);
        pv.add(p4);

        ph=new JPanel();
        ph.add(submit);
        ph.add(back);

        f.setLayout(new BorderLayout());//设置布局为边框布局，边框布局分东南西北中5个方位来添加控件。
        f.add(pb, BorderLayout.NORTH);//Frame对象lb调用方法add（）,lb放在最北上方
        f.add(pv, BorderLayout.CENTER);//pv在中间
        f.add(ph, BorderLayout.SOUTH);//ph在南边
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);//置一个默认的关闭操作，也就是你的JFrame窗口的关闭按钮，点击它时，退出程序
        f.setBounds(550,350,900,450);//setBounds(x,y,width,height); x:组件在容器X轴上的起点 y:组件在容器Y轴上的起点 width:组件的长度 height:组件的高度
        f.setVisible(true);//目的是使控件可以显示出来,如果该控件已经被显示出来
        events();
    }
    void events(){
        /**
         * 点击提交按钮触发事件
         */
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select = JOptionPane.showConfirmDialog(null, "是否确认添加?", "确认", JOptionPane.YES_NO_OPTION);
                // select 为用户点的第几个按钮
                if(select == 0)
                {
                    Supplier sp=new Supplier();
                    sp.setPhone(phone.getText());
                    sp.setPlace(place.getText());
                    sp.setNo(supplier_no.getText());
                    sp.setName(name.getText());
                    if(addData(sp)==true){
                        JOptionPane.showMessageDialog(null,"添加成功","恭喜",JOptionPane.PLAIN_MESSAGE);
                        supplier_no.setText("");
                        name.setText("");
                        phone.setText("");
                        place.setText("");
                        f.dispose();
                        new SupplierManView();
                    }

                    else
                    {
                        JOptionPane.showMessageDialog(null,"添加失败","抱歉",JOptionPane.ERROR_MESSAGE);
                        supplier_no.setText("");
                        name.setText("");
                        phone.setText("");
                        place.setText("");

                    }

                }

            }
        });
        /**
         * 返回管理员操作界面
         */
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();

            }
        });
    }
    public boolean addData(Supplier sp) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Users\\85264\\Desktop\\supplier.txt",true);
            fw.write(" 供应商编码:  "+sp.getNo());
            fw.write(" 供应商名:  "+sp.getName());
            fw.write(" 供应商地址:  "+sp.getPlace());
            fw.write(" 供应商手机:  "+sp.getPhone());
            fw.write("\r\n");
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
