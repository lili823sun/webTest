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
		//�õ�ҳ�����������
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		String email = request.getParameter("email");
		String birthday = request.getParameter("birthday");
		
		//�����ݿ��ȡ�����û�
		UserDao dao = new UserDao();
		//List<User> users = dao.findAllUsers();
		User user = dao.findUserByName(username);
		
		if(user == null){  //�û�Ϊ�գ�������û������ݿ���û�У������û�ע����û�
			//�����ݿ�洢ҳ���ϵ�����
			//���ʵ��StringתDate
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
				out.println("ע��ɹ���<a href=\"login.html\">��ȥ��¼</a>");
			}else{
				out.println("������˼��ע��ʧ����");
			}
		}else{
			out.println("����û����Ѿ�����");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
