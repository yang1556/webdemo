package com.stx;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RegisterDbUtils {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "a201A201";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection mConnection;
    private static final String URL = "jdbc:mysql://sh-cynosdbmysql-grp-dj59h4tu.sql.tencentcdb.com:26130/OrderSystem";

    public boolean userregister(String id,String password){

        boolean b = false;


        try {
            Class.forName(DRIVER);
            System.out.println("加载驱动成功！");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("找不到驱动程序类 ，加载驱动失败！");
            e.printStackTrace();
        }

        boolean count=true;
        try{
            //Class.forName(DRIVER);
            mConnection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String sql1 = "select * from customers where userPhone='"+id+"'";
            Statement stm = mConnection.createStatement();
            ResultSet rs = stm.executeQuery(sql1);

            System.out.println("rs");
            //System.out.println(rs.getString("userPhone"));


            if(!rs.next()){

                String sql2 = "insert into customers(userPhone,userPassword) values('"+id+"','"+password+"')";
                count=stm.execute(sql2);
                System.out.println(count);
                b = true;
            }

            rs.close();
            stm.close();
            mConnection.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

        if(b)
        {
            return true;
        }
        else return false;
    }
}


