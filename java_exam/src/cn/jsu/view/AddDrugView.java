package cn.jsu.view;

import cn.jsu.service.DrugDaoImpl;
import cn.jsu.bean.Drug;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
public class AddDrugView extends  JFrame{
    JFrame f;
    JLabel lb1=new JLabel("当前是药品信息录入界面" );
    JTextField drug_no,name,species,price,num,place;
    JRadioButton yes,no;//声明单选项对象，性别选择
    ButtonGroup group=null;//声明按钮组
    JButton submit,back;//声明相应的操作的按钮
    JPanel p1,p2,p3,p4,p5,p6,p7,pv,ph,pb;//调节布局的通道
    AddDrugView(int id){
        f=new JFrame();
        name=new JTextField(10);
        drug_no=new JTextField(10);//创建文本信息的的对象
        species=new JTextField(15);
        price=new JTextField(5);
        num=new JTextField(15);
        place=new JTextField(18);

        group=new ButtonGroup();
        yes=new JRadioButton("处方药");//初始化单选框,
        no=new JRadioButton("非处方药");
        group.add(yes);//把按钮添加到按钮组
        group.add(no);
        back=new JButton("返回");
        submit=new JButton("提交");
        pb=new JPanel();
        pb.add(lb1,JLabel.CENTER);

        p1=new JPanel();
        p1.add(new JLabel("药品编号:",JLabel.CENTER));
        p1.add(drug_no);

        p2=new JPanel();
        p2.add(new JLabel("药品名:",JLabel.CENTER));
        p2.add(name);
        p3=new JPanel();
        p3.add(new JLabel("药品种类:",JLabel.CENTER));
        p3.add(yes);
        p3.add(no);
        p4=new JPanel();
        p4.add(new JLabel("药品价格:",JLabel.CENTER));
        p4.add(price);
        p5=new JPanel();
        p5.add(new JLabel("药品数量:",JLabel.CENTER));
        p5.add(num);
        p6=new JPanel();
        p6.add(new JLabel("药品出产地:",JLabel.CENTER));
        p6.add(place);


        pv=new JPanel();
        pv.setLayout(new GridLayout(7,1));   //把pv组件设置成第七行1列的网格布局

        pv.add(p1);
        pv.add(p2);
        pv.add(p3);
        pv.add(p4);
        pv.add(p5);
        pv.add(p6);

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
        events(id);
    }
    void events(int id){
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
                    int d_id=id+1;
                    String d_no=drug_no.getText();
                    String d_name=name.getText();
                    String d_species=null;
                    if(yes.isSelected()){
                        d_species=yes.getText();
                    }
                    else if(no.isSelected()){
                        d_species=no.getText();
                    }else
                        d_species="--";
                    double d_price=Double.parseDouble(price.getText());
                    int d_num=Integer.parseInt(num.getText());
                    String d_place=place.getText();
                    Drug dr=new Drug();
                    List<Drug> list=new LinkedList<>();
                    dr.setId(d_id);
                    dr.setNo(d_no);
                    dr.setName(d_name);
                    dr.setNum(d_num);
                    dr.setPlace(d_place);
                    dr.setPrice(d_price);
                    dr.setSpecies(d_species);
                    list.add(dr);
                    if(new DrugDaoImpl().add(dr)==1){
                        JOptionPane.showMessageDialog(null,"添加成功","恭喜",JOptionPane.PLAIN_MESSAGE);
                        drug_no.setText("");
                        name.setText("");
                        num.setText("");
                        price.setText("");
                        place.setText("");
                        group.clearSelection();
                        f.dispose();
                        new DrugManView();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"添加失败","抱歉",JOptionPane.ERROR_MESSAGE);
                        drug_no.setText("");
                        name.setText("");
                        num.setText("");
                        price.setText("");
                        place.setText("");
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
        new AddDrugView(4);
    }
}
