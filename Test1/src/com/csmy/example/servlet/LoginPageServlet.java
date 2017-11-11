package com.csmy.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		String username = "";
		String pwd = "";
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if("aa".equals(cookies[i].getName())){
					username = cookies[i].getValue();
				}
				if("bb".equals(cookies[i].getName())){
					pwd = cookies[i].getValue();
				}
			}
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>用户登录</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action=\"LoginServlet\" method=\"Get\">");
		out.println("用户名：<input type=\"text\" name=\"username\" value=\""+ username +"\"><br/>");
		out.println("密码：<input type=\"password\" name=\"password\" value=\""+ pwd +"\"><br/>");
		out.println("<input type=\"submit\" value=\"登录\">");
		out.println("</form>");
		out.println("<body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
