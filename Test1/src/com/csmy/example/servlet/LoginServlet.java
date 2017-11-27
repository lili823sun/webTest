package com.csmy.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csmy.example.contain.User;
import com.csmy.example.dao.UserDao;

public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		UserDao dao = new UserDao();
		User user = dao.findUserByName(username);
		
		if(user == null){
			out.println("该用户不存在");
		}else{
			if(pwd.equals(user.getPassword())){
				//此处实现记住用户名和密码功能
				Cookie usercookie = new Cookie("aa",username);
				Cookie pwdcookie = new Cookie("bb",pwd);
				usercookie.setMaxAge(60*60);
				pwdcookie.setMaxAge(60*60);
				response.addCookie(usercookie);
				response.addCookie(pwdcookie);
				
				//此处实现用户登录后才能访问网站其他资源的功能
				HttpSession session = request.getSession();
				session.setAttribute("isLogin", true);
				
				String newUrl = response.encodeRedirectURL("GetProductServlet");
				response.sendRedirect(newUrl);
			}else{
				out.println("密码输入不正确");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
