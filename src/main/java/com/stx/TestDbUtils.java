package com.stx;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDbUtils {

    private final String USERNAME = "root";
    private final String PASSWORD="a201A201";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection mConnection;
    private final String URL ="jdbc:mysql://sh-cynosdbmysql-grp-dj59h4tu.sql.tencentcdb.com:26130/OrderSystem";
    private PreparedStatement pstmt;
    static{
        try {
            Class.forName(DRIVER);
            System.out.println("加载驱动成功！");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("找不到驱动程序类 ，加载驱动失败！");
            e.printStackTrace();
        }

    }
    private static TestDbUtils mTestDbUtils;

    public static synchronized TestDbUtils getSington(){
        if(mTestDbUtils==null){
            mTestDbUtils=new TestDbUtils();
        }
        return mTestDbUtils;
    }

    public List<Map<String,String>> getCheckUserInfo() {
        List<Map<String,String>> results=new ArrayList<Map<String,String>>();
        Map<String,String> result=null;

        ResultSet resultSet=null;
        try {
            //获得数据库连接
            mConnection= DriverManager.getConnection(URL, "root", "a201A201");
            //获得SQL语句执行对象
            String sql="select userPhone,userPassword from customers";
            pstmt=mConnection.prepareStatement(sql);
            resultSet=pstmt.executeQuery();
            while(resultSet.next()){
                result=new HashMap<String, String>();
                result.put("userName", resultSet.getString("userPhone"));
                result.put("passWord", resultSet.getString("userPassword"));
                results.add(result);
                result=null;
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            //先关结果集
            if(resultSet!=null){
                try {
                    resultSet.close();
                    resultSet=null;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            //再关SQL语句执行对象
            if(pstmt!=null){
                try {
                    pstmt.close();
                    pstmt=null;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            //最后关数据库连接
            if(mConnection!=null){
                try {
                    mConnection.close();
                    mConnection=null;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
        return results;
    }

    private TestDbUtils(){

    }


}
