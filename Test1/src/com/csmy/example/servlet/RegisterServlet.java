package com.csmy.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csmy.example.contain.User;
import com.csmy.example.dao.UserDao;
public class RegisterServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//得到页面输入的内容
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		String email = request.getParameter("email");
		String birthday = request.getParameter("birthday");
		
		//从数据库获取所有用户
		UserDao dao = new UserDao();
		//List<User> users = dao.findAllUsers();
		User user = dao.findUserByName(username);
		
		if(user == null){  //用户为空，代表该用户在数据库中没有，允许用户注册该用户
			//往数据库存储页面上的数据
			//如何实现String转Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = sdf.parse(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			User user1 = new User(username,pwd,email,date);
			boolean isRegisterSuccess = dao.addUser(user1);
			if(isRegisterSuccess){
				out.println("注册成功，<a href=\"login.html\">请去登录</a>");
			}else{
				out.println("不好意思，注册失败了");
			}
		}else{
			out.println("这个用户名已经存在");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
