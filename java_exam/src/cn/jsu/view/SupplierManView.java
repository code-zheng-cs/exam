package cn.jsu.view;

import cn.jsu.bean.Supplier;
import cn.jsu.service.DrugDaoImpl;
import cn.jsu.service.SupplierDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class SupplierManView extends JFrame{
    JPanel root = new JPanel();
    JTable table = null;
    JFrame frame;
    DefaultTableModel tableModel = new DefaultTableModel();
    JButton addButton,deleteButton,editButton,backButton,refreshButton,searchButton,riseButton,fallButton;
    JTextField searchField = new JTextField();
    public SupplierManView ()
    {
        frame=new JFrame("供应商信息管理");
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
        // 加载文件
        try {
            //writeData();
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        events();
    }
    /**
     * 选中状态显示
     */
    static class IDColumnRender extends JCheckBox implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column)
        {
            this.setSelected(isSelected);
            if(value != null)
                this.setText(value.toString());
            // 背景设置
            this.setOpaque(true);
            if (isSelected) {
                this.setBackground(table.getSelectionBackground());
                this.setForeground(table.getSelectionForeground());

            } else {
                this.setBackground(table.getBackground());
                this.setForeground(table.getForeground());
            }
            return this;
        }
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

        // 列设置：添加7列
        tableModel.addColumn ("供应商信息");

        table.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
        table.getColumnModel().getColumn(0).setPreferredWidth(110); // 该列的宽度
    }
    private void initToolBar()
    {
        JToolBar toolBar = new JToolBar();
        root.add(toolBar, BorderLayout.PAGE_START);
        toolBar.setFloatable(false);//固定工具栏

        ImageIcon img_add=new ImageIcon("src\\cn\\jsu\\icons\\ic_add.png");

        addButton = new JButton("添加");
        addButton.setIcon(img_add);
        toolBar.add(addButton);

        ImageIcon img_del=new ImageIcon("src\\cn\\jsu\\icons\\ic_delete.png");
        deleteButton = new JButton("删除");
        deleteButton.setIcon(img_del);
        toolBar.add(deleteButton);

        editButton = new JButton("编辑");
        ImageIcon img_edit=new ImageIcon("src\\cn\\jsu\\icons\\ic_edit.png");
        editButton.setIcon(img_edit);
        toolBar.add(editButton);

        refreshButton=new JButton("刷新");
        toolBar.add(refreshButton);

        backButton=new JButton("返回");
        toolBar.add(backButton);

        riseButton=new JButton("升序");
        toolBar.add(riseButton);

        fallButton=new JButton("降序");
        toolBar.add(fallButton);

        toolBar.addSeparator(new Dimension(140,10));
        toolBar.add( new JLabel("查询->") );
        toolBar.add( searchField );
        searchButton = new JButton("查询");
        toolBar.add(searchButton);
        searchField.setMaximumSize(new Dimension(120,30));
    }
    private void events(){
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ManView();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new AddSupplierView();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows=table.getSelectedRows();
                if(rows.length==0) {
                    return;
                }
                int select=JOptionPane.showConfirmDialog(null,"是否确认删除？","确认",JOptionPane.YES_NO_OPTION);
                // 0号按钮是'确定'按钮
                if(select != 0)
                    return;
                // 从后往前删除
                FileReader fr= null;
                FileWriter fw=null;
                try {
                    fr = new FileReader("C:\\Users\\85264\\Desktop\\supplier.txt");
                    BufferedReader bufr = new BufferedReader(fr);
                    List<String> list=new ArrayList<>();
                    String line = null;
                    while((line=bufr.readLine())!=null){
                        list.add(line);
                    }
                    fr.close();
                    bufr.close();
                    fw=new FileWriter("C:\\Users\\85264\\Desktop\\supplier.txt");
                    BufferedWriter bufw=new BufferedWriter(fw);
                    int num=0;
                    for(String str: list){
                        if(num==rows[0]){
                            num++;
                            continue;
                        }
                        else{
                            bufw.write(str);
                            bufw.newLine();
                            bufw.flush();
                            num++;
                        }
                    }
                    bufw.close();
                    fw.close();
                    frame.dispose();
                    new SupplierManView();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException E){
                    E.printStackTrace();
                }

            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows=table.getSelectedRows();
                if(rows.length==0) {
                    return;
                }
                int select=JOptionPane.showConfirmDialog(null,"是否确认修改？","确认",JOptionPane.YES_NO_OPTION);
                // 0号按钮是'确定'按钮
                if(select != 0)
                    return;
                // 从后往前删除
                FileReader fr= null;
                FileWriter fw=null;
                try {
                    fr = new FileReader("C:\\Users\\85264\\Desktop\\supplier.txt");
                    BufferedReader bufr = new BufferedReader(fr);
                    List<String> list=new ArrayList<>();
                    String line = null;
                    while((line=bufr.readLine())!=null){
                        list.add(line);
                    }
                    fr.close();
                    bufr.close();
                    fw=new FileWriter("C:\\Users\\85264\\Desktop\\supplier.txt");
                    BufferedWriter bufw=new BufferedWriter(fw);
                    int num=0;
                    for(String str: list){
                        if(num==rows[0]){
                            bufw.write(str+"*****");
                            bufw.newLine();
                            bufw.flush();
                            num++;
                        }
                        else{
                            bufw.write(str);
                            bufw.newLine();
                            bufw.flush();
                            num++;
                        }
                    }
                    bufw.close();
                    fw.close();
                    frame.dispose();
                    new SupplierManView();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException E){
                    E.printStackTrace();
                }
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SupplierManView();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileReader fr= null;
                try {
                    fr = new FileReader("C:\\Users\\85264\\Desktop\\supplier.txt");
                    BufferedReader bufr = new BufferedReader(fr);
                    tableModel.setRowCount(0);
                        String line = null;
                        String str=searchField.getText();
                        while((line=bufr.readLine())!=null){
                            if(line.contains(str)){
                                Vector<Object> rowData=new Vector<>();
                                rowData.add(line);
                                tableModel.addRow(rowData);
                            }
                        }
                        bufr.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException E){
                    E.printStackTrace();
                }
            }
        });
        riseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileReader fr= null;
                FileWriter fw=null;
                try {
                    fr = new FileReader("C:\\Users\\85264\\Desktop\\supplier.txt");
                    BufferedReader bufr = new BufferedReader(fr);
                    List<String> list=new ArrayList<>();
                    String line = null;
                    while((line=bufr.readLine())!=null){
                        list.add(line);
                    }
                    fr.close();
                    bufr.close();
                    Collections.sort(list, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            int i=o1.compareTo(o2);
                            if(i>0)
                                return 1;
                            else
                                return -1;
                        }
                    });
                    fw=new FileWriter("C:\\Users\\85264\\Desktop\\supplier.txt");
                    BufferedWriter bufw=new BufferedWriter(fw);
                    for(String str: list){
                            bufw.write(str);
                            bufw.newLine();
                            bufw.flush();
                    }
                    bufw.close();
                    fw.close();
                    frame.dispose();
                    new SupplierManView();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException E){
                    E.printStackTrace();
                }
            }
        });
        fallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileReader fr= null;
                FileWriter fw=null;
                try {
                    fr = new FileReader("C:\\Users\\85264\\Desktop\\supplier.txt");
                    BufferedReader bufr = new BufferedReader(fr);
                    List<String> list=new ArrayList<>();
                    String line = null;
                    while((line=bufr.readLine())!=null){
                        list.add(line);
                    }
                    fr.close();
                    bufr.close();
                    Collections.sort(list, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            int i=o1.compareTo(o2);
                            if(i>0)
                                return -1;
                            else
                                return 1;
                        }
                    });
                    fw=new FileWriter("C:\\Users\\85264\\Desktop\\supplier.txt");
                    BufferedWriter bufw=new BufferedWriter(fw);
                    for(String str: list){
                        bufw.write(str);
                        bufw.newLine();
                        bufw.flush();
                    }
                    bufw.close();
                    fw.close();
                    frame.dispose();
                    new SupplierManView();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException E){
                    E.printStackTrace();
                }
            }
        });
    }
    private void loadData() throws IOException{
        FileReader fr=new FileReader("C:\\Users\\85264\\Desktop\\supplier.txt");
        BufferedReader bufr = new BufferedReader(fr);
        String line = null;
        while((line=bufr.readLine())!=null){
            Vector<Object> rowData=new Vector<>();
            rowData.add(line);
            tableModel.addRow(rowData);
        }
        bufr.close();
    }
    private void writeData() throws IOException{
            boolean flag=new SupplierDaoImpl().Save(new SupplierDaoImpl().showAll());
    }
    public static void main(String[] args) {
        new SupplierManView();
    }

}
