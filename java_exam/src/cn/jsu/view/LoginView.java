package cn.jsu.view;

import cn.jsu.service.ManagerDaoImpl;
import cn.jsu.service.StaffDaoImpl;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class LoginView extends  JFrame{
    private JPanel LoginPanel;
    private JPasswordField passwordField;
    private JButton LogManBtn;
    private JButton LogStaBtn;
    private JFormattedTextField idTextField;
    public LoginView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 485, 343);
        setResizable(false);//禁止最大化
        setLocationRelativeTo(null);//居中

        LoginPanel = new JPanel();
        LoginPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.setContentPane(LoginPanel);
        LoginPanel.setLayout(null);

        ImageIcon backimg;
        backimg = new ImageIcon("src\\cn\\jsu\\icons\\background.png");

        JLabel TitleLabel=new JLabel("药店管理系统");
        TitleLabel.setFont(new Font("新宋体",Font.BOLD,50));
        TitleLabel.setForeground(Color.LIGHT_GRAY);
        TitleLabel.setBounds(90,35,350,90);
        TitleLabel.setOpaque(false);
        LoginPanel.add(TitleLabel);

        JLabel IDLabel = new JLabel("账  号：");
        IDLabel.setFont(new Font("新宋体", Font.BOLD, 20));
        IDLabel.setForeground(Color.LIGHT_GRAY);
        IDLabel.setBounds(49, 170, 91, 38);
        IDLabel.setOpaque(false);
        LoginPanel.add(IDLabel);

        JLabel PasswordLabel = new JLabel("密  码：");
        PasswordLabel.setOpaque(false);
        PasswordLabel.setForeground(Color.LIGHT_GRAY);
        PasswordLabel.setFont(new Font("新宋体", Font.BOLD, 20));
        PasswordLabel.setBounds(49, 221, 91, 38);
        LoginPanel.add(PasswordLabel);

        idTextField = new JFormattedTextField();
        idTextField.setForeground(Color.LIGHT_GRAY);
        idTextField.setToolTipText("");
        idTextField.setText("请输入您的账号");
        idTextField.setBounds(143, 175, 227, 33);

        idTextField.addMouseListener(new MouseAdapter() {
             @Override
             public void mousePressed(MouseEvent e) {
                 if (idTextField.getText().equals("请输入您的账号"))
                     idTextField.setText(null);
                 idTextField.setForeground(Color.BLACK);
             }
         }
        );

        LoginPanel.add(idTextField);

        passwordField = new JPasswordField();
        passwordField.setBounds(143, 221, 227, 33);
        LoginPanel.add(passwordField);

        LogManBtn = new JButton("管理员登录");

        LogManBtn.setForeground(Color.DARK_GRAY);
        LogManBtn.setFont(new Font("宋体", Font.BOLD, 17));
        LogManBtn.setBackground(Color.LIGHT_GRAY);
        LogManBtn.setBounds(88, 266, 130, 33);
        LoginPanel.add(LogManBtn);


        LogStaBtn = new JButton("员工登录");
        LogStaBtn.setForeground(Color.DARK_GRAY);

        LogStaBtn.setBackground(Color.LIGHT_GRAY);
        LogStaBtn.setBounds(238, 266, 110, 33);
        LoginPanel.add(LogStaBtn);

         LoginEvents();

        JLabel BackgroundLabel = new JLabel(backimg);
        BackgroundLabel.setBounds(0, 0, 482, 309);
        LoginPanel.add(BackgroundLabel);
        setVisible(true);
    }
    public void LoginEvents(){
        LogManBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username=idTextField.getText();
                String password=new String(passwordField.getPassword());
                System.out.println(password);
                boolean flag=new ManagerDaoImpl().Login(username,password);
                if(flag==true) {
                    JOptionPane.showMessageDialog(null,"欢迎用户"+username+"登录","恭喜",JOptionPane.PLAIN_MESSAGE);
                    dispose();
                    new ManView();
                }
                else{
                    JOptionPane.showMessageDialog(null,"用户名或密码错误","抱歉",JOptionPane.ERROR_MESSAGE);
                    idTextField.setText("");
                    passwordField.setText("");
                }

            }
        });
        LogStaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username=idTextField.getText();
                String password=new String(passwordField.getPassword());
                boolean flag=new StaffDaoImpl().Login(username,password);
                if(flag==true) {
                    JOptionPane.showMessageDialog(null,"欢迎用户"+username+"登录","恭喜",JOptionPane.PLAIN_MESSAGE);
                dispose();
                    new StaffView();

                }
                else{
                    JOptionPane.showMessageDialog(null,"用户名或密码错误","抱歉",JOptionPane.ERROR_MESSAGE);
                    idTextField.setText("");
                    passwordField.setText("");
                }

            }
        });
    }
    public static void main(String[] args) {
        new LoginView();
    }
}
