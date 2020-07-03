package cn.jsu.view;

import cn.jsu.service.StaffDaoImpl;
import cn.jsu.bean.Staff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
public class StaffManView  extends JFrame {
    private JPanel root = new JPanel();
    private JTable table = null;
    JFrame frame;
    List<Staff> backupList = null;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JButton addButton,deleteButton,editButton,backButton,refreshButton,searchButton;
    private JTextField searchField = new JTextField();
    public StaffManView()
    {
        frame = new JFrame("员工管理界面");
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

        initShow(new StaffDaoImpl().showAll());
        events();
    }

    /**
     * 打印全表
     */
    private <T> void initShow(List<T> list){
        for(T sta:list){
            Staff s=(Staff)sta;
            Vector<Object> rowData=new Vector<>();
            rowData.add(s.getS_id());
            rowData.add(s.getS_name());
            rowData.add(s.getS_no());
            rowData.add(s.getS_sex());
            rowData.add(s.getS_age());
            rowData.add(s.getS_phone());
            rowData.add(s.getUsername());
            rowData.add(s.getPassword());
            tableModel.addRow(rowData);
        }
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
        tableModel.addColumn ("序号");
        tableModel.addColumn ("员工姓名");
        tableModel.addColumn ("员工工号");
        tableModel.addColumn ("员工性别");
        tableModel.addColumn ("员工年龄");
        tableModel.addColumn ("员工手机号");
        tableModel.addColumn ("员工用户名");
        tableModel.addColumn ("员工密码");

        table.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
        table.getColumnModel().getColumn(0).setPreferredWidth(50); // 该列的宽度
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

        refreshButton =new JButton("刷新");
        toolBar.add(refreshButton);

        backButton = new JButton("返回");
        toolBar.add(backButton);

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
                int count=tableModel.getRowCount();

                new AddStaffView((int)tableModel.getValueAt(count-1,0));
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new StaffManView();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows=table.getSelectedRows();
                if(rows.length==-1)
                    return;
                int select=JOptionPane.showConfirmDialog(null,"是否确认删除？","确认",JOptionPane.YES_NO_OPTION);
                if(select != 0)
                    return; // 0号按钮是'确定'按钮
                // 从后往前删除
                for(int i= rows.length-1; i>=0; i--)
                {
                    new StaffDaoImpl().delete((int)tableModel.getValueAt(rows[i],0));
                    new StaffDaoImpl().update(rows[i]+1);
                    tableModel.removeRow( rows[i] );
                    frame.dispose();
                    new StaffManView();
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows=table.getSelectedRows();
                if(rows.length==-1)
                    return;
                int select=JOptionPane.showConfirmDialog(null,"是否确认修改此条信息？","确认",JOptionPane.YES_NO_OPTION);
                if(select != 0)
                    return; // 0号按钮是'确定'按钮
                new UpdateStaffView((int)tableModel.getValueAt(rows[0],0));
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filter = searchField.getText();
                if(filter.length() == 0)
                {
                    frame.dispose();
                    new StaffManView();
                    return ;
                }
                if( backupList == null)
                {
                    backupList = new ArrayList<>();
                    for(int i=0; i<tableModel.getRowCount(); i++)
                    {
                        Staff item =new Staff();
                        item.setS_id((int) tableModel.getValueAt(i, 0));
                        item.setS_name((String) tableModel.getValueAt(i, 1));
                        item.setS_no((String) tableModel.getValueAt(i, 2));
                        item.setS_sex((String) tableModel.getValueAt(i, 3));
                        item.setS_age((int) tableModel.getValueAt(i, 4));
                        item.setS_phone((String) tableModel.getValueAt(i, 5));
                        item.setUsername((String) tableModel.getValueAt(i, 6));
                        item.setPassword((String) tableModel.getValueAt(i, 7));
                        backupList.add( item );
                    }
                }
                // 把符合条件的记录显示在表格里
                tableModel.setRowCount(0);
                for(Staff s : backupList)
                {
                    if(s.getS_name().indexOf(filter)>=0)
                    {
                        Vector<Object> rowData = new Vector<>();
                        rowData.add(s.getS_id());
                        rowData.add(s.getS_name());
                        rowData.add(s.getS_no());
                        rowData.add(s.getS_sex());
                        rowData.add(s.getS_age());
                        rowData.add(s.getS_phone());
                        rowData.add(s.getUsername());
                        rowData.add(s.getPassword());
                        tableModel.addRow(rowData);
                    }
                }
                // 把其他操作按钮禁用
                addButton.setEnabled(false);
                deleteButton.setEnabled(false);
                editButton.setEnabled(false);
            }
        });
    }
    public static void main(String[] args) {
        new StaffManView();
    }
}
