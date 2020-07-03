package cn.jsu.view;

import cn.jsu.service.SaleDaoImpl;
import cn.jsu.bean.Sale;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class SaleView extends JFrame {
    JPanel root = new JPanel();
    JTable table = null;
    JFrame frame;
    DefaultTableModel tableModel = new DefaultTableModel();
    JButton refreshButton,backButton,saveButton;
    JTextField searchField = new JTextField();
    public SaleView(boolean flag)
    {
        frame=new JFrame("营收信息表");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setBounds(550,250,840, 600);
        frame.setVisible(true);
        this.setContentPane(root);
        root.setLayout(new BorderLayout());
        frame.add(root);
        // 表格初始化
        initTable();
        // 初始化工具栏
        initToolBar();

        initShow(new SaleDaoImpl().showAll());

        events(flag);

    }
    private <T> void initShow(List<T> list){
        for(T sta:list){
            Sale s=(Sale)sta;
            Vector<Object> rowData=new Vector<>();
            rowData.add(s.getId());
            rowData.add(s.getName());
            rowData.add(s.getNo());
            rowData.add(s.getPrice());
            rowData.add(s.getNum());
            rowData.add(s.getSum());
            tableModel.addRow(rowData);
        }
        double money=0;
        for(int i=0;i<tableModel.getRowCount();i++){
            money+=(double)tableModel.getValueAt(i,5);
        }
        searchField.setText(String.valueOf(money));
    }
    private void initTable() {
        table = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        JScrollPane scrollPane = new JScrollPane(table);
        root.add(scrollPane, BorderLayout.CENTER);

        // 添加到主界面
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true); // 整行选择
        table.setRowHeight(30);

        // 列设置：添加6列
        tableModel.addColumn ("序号");
        tableModel.addColumn ("药品编号");
        tableModel.addColumn ("药品名");
        tableModel.addColumn ("售出价格");
        tableModel.addColumn ("售出数量");
        tableModel.addColumn ("售出单笔总价");

        table.getColumnModel().getColumn(0).setPreferredWidth(110); // 该列的宽度
    }

    private void initToolBar()
    {
        JToolBar toolBar = new JToolBar();
        root.add(toolBar, BorderLayout.PAGE_START);
        toolBar.setFloatable(false);//固定工具栏

        toolBar.addSeparator(new Dimension(40,10));
        refreshButton = new JButton("刷新");
        toolBar.add(refreshButton);

        toolBar.addSeparator(new Dimension(40,10));
        saveButton = new JButton("保存");
        toolBar.add(saveButton);

        toolBar.addSeparator(new Dimension(50,10));
        backButton =new JButton("返回");
        toolBar.add(backButton);

        toolBar.addSeparator(new Dimension(140,10));
        toolBar.add( new JLabel("总营业额------->") );
        toolBar.add( searchField );
        searchField.setText(String.valueOf(0));
        searchField.setMaximumSize(new Dimension(120,30));

    }
    public void events(boolean flag){
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
               new SaleView(flag);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select = JOptionPane.showConfirmDialog(null, "是否确认保存数据?", "确认", JOptionPane.YES_NO_OPTION);
                if(select==0){
                    boolean flag=new SaleDaoImpl().Save(new SaleDaoImpl().showAll());
                    if(flag==true){
                        JOptionPane.showMessageDialog(null, "保存到本地成功", "恭喜", JOptionPane.PLAIN_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "保存到本地失败", "抱歉", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                if(flag==true){
                    new ManView();
                }
                else{
                    new StaffView();
                }
            }
        });

    }
    public static void main(String[] args) {
        new SaleView(false);
    }
}
