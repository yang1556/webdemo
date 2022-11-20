package com.stx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/login")
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TestDbUtils mTestDbUtils;
    private RegisterDbUtils mRegBdUtils=new RegisterDbUtils();
    private ShopDbUtils mShopUtils=new ShopDbUtils();
    HashMap<String, String> map = new HashMap<String, String>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hello Servlet");
        resp.getWriter().write("login..");

        mShopUtils.ShopItem();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setHeader("content-type","text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();
        //获取实体User中的变量，将变量插入到数据库中，完成注册
        //获取注册的用户名,"name"要与jsp页面中的变量名一致
        String name=req.getParameter("username");
        String password=req.getParameter("password");
        String name1=req.getParameter("name1");
        String regpassword=req.getParameter("regpassword");
        String tag=req.getParameter("tag");
        System.out.println("用户名: "+name);
        System.out.println("密码: "+password);
        System.out.println("注册用户名: "+name1);
        System.out.println("注册密码: "+regpassword);
        System.out.println("tag: "+tag);
        if(tag.equals("register")){
            if(checkReg(name1,regpassword)){
            System.out.println("register success");
            out.print("注册成功");
            out.flush();
            out.close();
            }
            else{
                System.out.println("register failed");
                System.out.println("用户名已存在");
                out.print("用户名已存在");
                out.flush();
                out.close();
            }
        }
        else if(tag.equals("login")){

            if(checkLogin(name,password)){
                System.out.println("login success");
                //resp.getWriter().write("login success");
                out.print("success");
                out.flush();
                out.close();
            }
        else {
            System.out.println("login failed");
            //resp.getWriter().write("failed");
            out.print("登陆失败");
            out.flush();
            out.close();
        }
        }
    }

    private  boolean checkReg(String userName,String password){

        if(mRegBdUtils.userregister(userName,password))
            return true;
        else return false;
    }
    private boolean checkLogin(String userName,String password){
        List<Map<String,String>> results=new ArrayList<Map<String,String>>();
        //获得数据库操作实例
        mTestDbUtils=TestDbUtils.getSington();
        results=mTestDbUtils.getCheckUserInfo();
        System.out.println(results.toString());
        for(Map<String,String> map:results){

            if(map.get("userName").equals(userName)&&map.get("passWord").equals(password)){
                return true;
            }
        }
        return false;
    }




}
