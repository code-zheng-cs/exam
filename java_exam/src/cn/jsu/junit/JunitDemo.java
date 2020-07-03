package cn.jsu.junit;

import cn.jsu.service.SaleDaoImpl;
import cn.jsu.service.SupplierDaoImpl;
import cn.jsu.bean.Drug;
import cn.jsu.bean.Supplier;
import cn.jsu.util.JDBCUtils;
import cn.jsu.view.SupplierManView;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JunitDemo {
    @Test
    public void addStaff(){
        Connection conn=null;
        PreparedStatement pstmt=null;
        int n1,n2,n3;
        try {
            conn= JDBCUtils.getConnection();
            String[] str1=new String[]{"王","李","张","刘","陈","杨","黄","赵","周","吴","徐","孙","马","胡","朱","郭","何","罗","高","林","郑"};
            String[] str2=new String[]{"弈","宸","烨","岚","漠","庭","城","途","伯","弘","枭","律","哲","凌","沙","辞","汉","恺","斯","烟","暘",
                    "寒","离","尘","崖","伞","谪","泽","扬","赫","穆","独","绝","轩"};
            String[] str3=new String[]{"筱","湘","玥","媱","汐","瑄","羽","霏","影","沫","燕","泠","梓","言","斓","璃","落","矞","琅",
                    "湛","露","浅","采","然","流","梦","溪","墨","染","冰","绡","舞","野","凝","纱","菡"};
            for(int i=1;i<=1000;i++){
                String sql="insert into staff values(?,?,?,?,?,?,?,?)";
                pstmt=conn.prepareStatement(sql);
                pstmt.setInt(1,i);
                pstmt.setString(2,String.valueOf(10000+i));
                n1=new Random().nextInt(str1.length);
                //System.out.println(n1+" "+str1.length);
                n2=new Random().nextInt(str2.length);
                n3=new Random().nextInt(str3.length);

                pstmt.setString(3,str1[n1]+str2[n2]+str3[n3]);
                if(i%2==0)
                    pstmt.setString(4,"女");
                else
                    pstmt.setString(4,"男");
                pstmt.setInt(5,i%100);
                pstmt.setString(6,String.valueOf(18356412-i));
                pstmt.setString(7,"2020user"+i);
                pstmt.setString(8,"2020pass"+i);
                int count=pstmt.executeUpdate();
                System.out.println(count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
        }
    }
    @Test
    public  void addDrug(){
        Connection conn=null;
        PreparedStatement pstmt=null;
        int n1,n2,n3;
        double n4;
        try {
            conn= JDBCUtils.getConnection();
            String[] str1=new String[]{"钩吻","灵仙","半夏","丁香","苏叶","白薇","泽兰","杜若","白英","茵陈","淡竹","苍术","豆蔻","银朱","附子","首乌","玉竹","川穹","郁金","佩兰","神曲","莲心","紫菀","橘红","苁蓉","蝉衣","青黛","海月"};
            String[] str2=new String[]{"辰砂","园参", "旱芹", "旱莲", "岗松", "岗梅", "牡蛎" ,"杜桂", "皂角" ,"佛手", "佛片" ,"条苓" ,"龟甲", "龟板" ,"忘忧" ,"辛夷","良姜","谷芽", "羌活", "远志", "连翘", "沙参", "沙棘", "沉香", "没药", "诃子","没石", "陆英", "灵芝" ,"鸡头" ,"阿胶" ,"阿魏", "陈皮" ,"附子","青皮", "青果" ,"青蒿" };
            String[] str3=new String[]{"北京","天津","南京","上海","重庆","香港","澳门","台北","贵阳","长沙","郑州","青岛","杭州","苏州","宁波","深圳","广东","西安","武汉","沈阳","大连","合肥","福州","厦门","南昌","济南","南宁","海口","三亚","成都","昆明",};
            for(int i=1;i<=2000;i++){
                n1=new Random().nextInt(str1.length);
                n2=new Random().nextInt(str2.length);
                n3=new Random().nextInt(str3.length);
                n4=new Random().nextDouble()*80+5;
                String sql="insert into drug values(null,?,?,?,?,?,?)";
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1,str1[n1]+str2[n2]);
                pstmt.setString(2,("Cn"+2020+i));
                if(i%2==0)
                    pstmt.setString(3,"处方药");
                else
                    pstmt.setString(3,"非处方药");
                pstmt.setDouble(4,(double)((int)(n4*10))/10);
                pstmt.setInt(5,(n2+1)*37-25);
                pstmt.setString(6,str3[n3]);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
        }
    }
    @Test
    public void UpdateDrug(){
        //true为进货，false为售出
        String no="Cn20205";
        int num=5;
        boolean f=false;
        Drug dr=new Drug();
        boolean flag=new SaleDaoImpl().update(no,num,f);
        System.out.println(flag);
        if(flag==true&&f==false){
            dr=new SaleDaoImpl().show(no);
            dr.setNum(num);
            new SaleDaoImpl().addSale(dr);
            System.out.println(dr);
        }
        else{
            System.out.println("操作失败");
        }
    }
    @Test
    public void addSuppier(){
        Supplier sp=null;
        int n1,n2,n3;
        String[] str1=new String[]{"集团","有限公司"};
        String[] str2=new String[]{"辰砂","园参", "旱芹", "旱莲", "岗松", "岗梅", "牡蛎" ,"杜桂", "皂角" ,"佛手", "佛片" ,"条苓" ,"龟甲", "龟板" ,"忘忧" ,"辛夷","良姜","谷芽", "羌活", "远志", "连翘", "沙参", "沙棘", "沉香", "没药", "诃子","没石", "陆英", "灵芝" ,"鸡头" ,"阿胶" ,"阿魏", "陈皮" ,"附子","青皮", "青果" ,"青蒿" };
        String[] str3=new String[]{"北京","天津","南京","上海","重庆","香港","澳门","台北","贵阳","长沙","郑州","青岛","杭州","苏州","宁波","深圳","广东","西安","武汉","沈阳","大连","合肥","福州","厦门","南昌","济南","南宁","海口","三亚","成都","昆明",};
        for(int i=0;i<100;i++){
            n1=new Random().nextInt(str1.length);
            n2=new Random().nextInt(str2.length);
            n3=new Random().nextInt(str3.length);
            sp=new Supplier();
            sp.setNo("So2020"+i);
            sp.setName(str3[n3]+str2[n2]+str1[n1]);
            sp.setPlace(str3[n3]);
            sp.setPhone("1555821"+i);
            new SupplierDaoImpl().addSupplier(sp);
        }

    }
    @Test
    public void deleteFile(){
        FileReader fr= null;
        FileWriter fw=null;
        try {
            fr = new FileReader("C:\\Users\\85264\\Desktop\\s.txt");
            BufferedReader bufr = new BufferedReader(fr);
            List<String> list=new ArrayList<>();
            String line = null;
            while((line=bufr.readLine())!=null){
                list.add(line);
            }
            fr.close();
            bufr.close();
            fw=new FileWriter("C:\\Users\\85264\\Desktop\\s.txt");
            BufferedWriter bufw=new BufferedWriter(fw);
            int num=0;
            for(String str: list){
                if(num==2){
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
            //frame.dispose();
           // new SupplierManView();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException E){
            E.printStackTrace();
        }
    }
}
