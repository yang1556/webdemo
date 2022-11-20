package com.stx;

import java.sql.*;

import com.stx.DBHelper;
public class ShopDbUtils {
    private final String USERNAME = "root";
    private final String PASSWORD="a201A201";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection mConnection;
    private final String URL ="jdbc:mysql://sh-cynosdbmysql-grp-dj59h4tu.sql.tencentcdb.com:26130/OrderSystem";
    private PreparedStatement pstmt;


    public int ShopItem()
    {
        try {
            Class.forName(DRIVER);
            System.out.println("加载驱动成功！");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("找不到驱动程序类 ，加载驱动失败！");
            e.printStackTrace();
        }
        try{
            //Class.forName(DRIVER);
            mConnection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String sql = "select * from shops ";
            Statement stm = mConnection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs);
            }
            rs.close();
            stm.close();
            mConnection.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    return 0;

    }







}
