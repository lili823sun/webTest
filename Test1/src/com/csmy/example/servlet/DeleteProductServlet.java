package com.csmy.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csmy.example.dao.ProductDao;

public class DeleteProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("product_id");
		
		ProductDao dao = new ProductDao();
		boolean result = dao.deleteUserById(id);
		if (result) {
			String newUrl = response.encodeRedirectURL("GetProductServlet");
			response.sendRedirect(newUrl);
		}else{
			out.println("É¾³ýÊ§°Ü");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
