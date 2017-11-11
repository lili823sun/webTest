package com.csmy.example.servlet;

import java.io.IOException;
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
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		UserDao dao = new UserDao();
		User user = dao.findUserByName(username);
		
		if(user == null){
			System.out.println("���û�������");
		}else{
			if(pwd.equals(user.getPassword())){
				//�˴�ʵ�ּ�ס�û��������빦��
				Cookie usercookie = new Cookie("aa",username);
				Cookie pwdcookie = new Cookie("bb",pwd);
				usercookie.setMaxAge(60*60);
				pwdcookie.setMaxAge(60*60);
				response.addCookie(usercookie);
				response.addCookie(pwdcookie);
				
				//�˴�ʵ���û���¼����ܷ�����վ������Դ�Ĺ���
				HttpSession session = request.getSession();
				session.setAttribute("isLogin", true);
			}else{
				System.out.println("�������벻��ȷ");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
