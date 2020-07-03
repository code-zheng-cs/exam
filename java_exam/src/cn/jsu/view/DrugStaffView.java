package cn.jsu.view;

import cn.jsu.service.DrugDaoImpl;
import cn.jsu.bean.Drug;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
public class DrugStaffView extends JFrame{
    JPanel root = new JPanel();
    JTable table = null;
    JFrame frame;
    List<Drug> backupList = null;
    DefaultTableModel tableModel = new DefaultTableModel();
    JButton addButton,deleteButton,editButton,backButton,refreshButton,searchButton,riseButton,fallButton;
    JTextField searchField = new JTextField();
    public DrugStaffView()
    {
        frame=new JFrame("药品信息管理");
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

        initShow(new DrugDaoImpl().showAll());
        events();
    }
    /**
     * 打印全表
     */
    private <T> void initShow(List<T> list){
        for(T sta:list){
            Drug s=(Drug)sta;
            Vector<Object> rowData=new Vector<>();
            rowData.add(s.getId());
            rowData.add(s.getName());
            rowData.add(s.getNo());
            rowData.add(s.getSpecies());
            rowData.add(s.getPrice());
            rowData.add(s.getNum());
            rowData.add(s.getPlace());
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
        tableModel.addColumn ("药品名");
        tableModel.addColumn ("药品编号");
        tableModel.addColumn ("药品种类");
        tableModel.addColumn ("药品价格");
        tableModel.addColumn ("药品数量");
        tableModel.addColumn ("药品供应商");



        table.getColumnModel().getColumn(0).setCellRenderer(new DrugManView.IDColumnRender());
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

        backButton = new JButton("返回");
        toolBar.add(backButton);

        refreshButton =new JButton("刷新");
        toolBar.add(refreshButton);

        riseButton = new JButton("升序");
        toolBar.add(riseButton);
        fallButton = new JButton("降序");
        toolBar.add(fallButton);

        toolBar.addSeparator(new Dimension(140,10));
        toolBar.add( new JLabel("查询->") );
        toolBar.add( searchField );
        searchButton = new JButton("查询");
        toolBar.add(searchButton);
        searchField.setMaximumSize(new Dimension(120,30));

        addButton.setEnabled(false);
        deleteButton.setEnabled(false);
        editButton.setEnabled(false);
    }
    private void events(){
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new StaffView();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count=tableModel.getRowCount();
                new AddDrugView((int)tableModel.getValueAt(count-1,0));
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
                new UpdateDrugView((int)tableModel.getValueAt(rows[0],0));
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
                    new DrugDaoImpl().delete((int)tableModel.getValueAt(rows[i],0));
                    new DrugDaoImpl().update((int)tableModel.getValueAt(rows[i],0));
                    tableModel.removeRow( rows[i] );
                    frame.dispose();
                    new DrugManView();
                }
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new DrugStaffView();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户输入的过滤条件
                String filter = searchField.getText();
                if(filter.length() == 0) // 过滤条件为空
                {
                    // 恢复原始数据
                    frame.dispose();
                    new DrugManView();
                    return ;
                }
                // System.out.println(backupList);
                // 首次执行数据备份，放到一个 List 里
                if( backupList == null)
                {

                    backupList = new ArrayList<>();
                    for(int i=0; i<tableModel.getRowCount(); i++)
                    {

                        Drug item =new Drug();
                        item.setId((int) tableModel.getValueAt(i, 0));
                        item.setName((String) tableModel.getValueAt(i, 1));
                        item.setNo((String) tableModel.getValueAt(i, 2));
                        item.setSpecies((String) tableModel.getValueAt(i, 3));
                        item.setPrice((double) tableModel.getValueAt(i, 4));
                        item.setNum((int) tableModel.getValueAt(i, 5));
                        item.setPlace((String) tableModel.getValueAt(i, 6));
                        backupList.add( item );
                    }
                }
                // 把符合条件的记录显示在表格里
                tableModel.setRowCount(0);//清空
                for(Drug s : backupList)
                {
                    if(s.getName().indexOf(filter)>=0)
                    {

                        Vector<Object> rowData = new Vector<>();
                        rowData.add(s.getId());
                        rowData.add(s.getName());
                        rowData.add(s.getNo());
                        rowData.add(s.getSpecies());
                        rowData.add(s.getPrice());
                        rowData.add(s.getNum());
                        rowData.add(s.getPlace());
                        tableModel.addRow(rowData);
                    }
                }
                // 把其他操作按钮禁用
                addButton.setEnabled(false);
                deleteButton.setEnabled(false);
                editButton.setEnabled(false);
            }
        });
        riseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
                initShow(new DrugDaoImpl().sort(false));
            }
        });
        fallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
                initShow(new DrugDaoImpl().sort(true));
            }
        });
    }
    public static void main(String[] args) {
        new DrugStaffView();
    }
}
