package cn.jsu.view;

import cn.jsu.service.StaffDaoImpl;
import cn.jsu.bean.Staff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddStaffView extends JFrame {
    JFrame f;
    JLabel lb1=new JLabel("当前是员工信息录入界面" );
    JTextField work_no,name,age,phone,username,password;
    JRadioButton man,woman;//声明单选项对象，性别选择
    ButtonGroup group=null;//声明按钮组
    JButton submit,back;//声明相应的操作的按钮
    JPanel p1,p2,p3,p4,p5,p6,p7,pv,ph,pb;//调节布局的通道

       AddStaffView(int id){
        f=new JFrame();
        work_no=new JTextField(10);
        name=new JTextField(10);
        phone=new JTextField(15);
        age=new JTextField(5);
        username=new JTextField(15);
        password=new JTextField(18);

        group=new ButtonGroup();
        man=new JRadioButton("男");//初始化单选框,
        woman=new JRadioButton("女");
        group.add(man);//把按钮添加到按钮组
        group.add(woman);
        back=new JButton("返回");
        submit=new JButton("提交");
        pb=new JPanel();
        pb.add(lb1,JLabel.CENTER);

        p1=new JPanel();
        p1.add(new JLabel("工号:",JLabel.CENTER));
        p1.add(work_no);

        p2=new JPanel();
        p2.add(new JLabel("姓名:",JLabel.CENTER));
        p2.add(name);
        p3=new JPanel();
        p3.add(new JLabel("性别:",JLabel.CENTER));
        p3.add(man);
        p3.add(woman);
        p4=new JPanel();
        p4.add(new JLabel("年龄:",JLabel.CENTER));
        p4.add(age);
        p5=new JPanel();
        p5.add(new JLabel("电话号码:",JLabel.CENTER));
        p5.add(phone);
        p6=new JPanel();
        p6.add(new JLabel("用户名:",JLabel.CENTER));
        p6.add(username);
        p7=new JPanel();
        p7.add(new JLabel("密码:",JLabel.CENTER));
        p7.add(password);

        pv=new JPanel();
        pv.setLayout(new GridLayout(7,1));   //把pv组件设置成第七行1列的网格布局

        pv.add(p1);
        pv.add(p2);
        pv.add(p3);
        pv.add(p4);
        pv.add(p5);
        pv.add(p6);
        pv.add(p7);

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
                    String s_no=work_no.getText();
                    String s_name=name.getText();
                    String s_sex=null;
                    if(man.isSelected()){
                        s_sex=man.getText();
                    }
                    else if(woman.isSelected()){
                        s_sex=woman.getText();
                    }else
                        s_sex="--";
                    int s_age=Integer.parseInt(age.getText());
                    String s_phone=phone.getText();
                    String s_username=username.getText();
                    String s_password=password.getText();
                    Staff sta=new Staff();
                    List<Staff> list=new ArrayList<>();
                    sta.setS_name(s_name);
                    sta.setS_age(s_age);
                    sta.setS_no(s_no);
                    sta.setS_sex(s_sex);
                    sta.setPassword(s_password);
                    sta.setUsername(s_username);
                    sta.setS_phone(s_phone);
                    list.add(sta);
                    if(new StaffDaoImpl().add(sta)==1){
                        JOptionPane.showMessageDialog(null,"添加成功","恭喜",JOptionPane.PLAIN_MESSAGE);
                        work_no.setText("");
                        name.setText("");
                        age.setText("");
                        phone.setText("");
                        username.setText("");
                        password.setText("");
                        group.clearSelection();
                        f.dispose();
                        new StaffManView();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"添加失败","抱歉",JOptionPane.ERROR_MESSAGE);
                        work_no.setText("");
                        name.setText("");
                        age.setText("");
                        phone.setText("");
                        username.setText("");
                        password.setText("");
                        group.clearSelection();
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
                //new ManView();
            }
        });
    }
    public static void main(String[] args) {
        new AddStaffView(5);
    }

}
