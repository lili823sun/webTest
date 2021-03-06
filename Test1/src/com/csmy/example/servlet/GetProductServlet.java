package com.csmy.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csmy.example.contain.Product;
import com.csmy.example.dao.ProductDao;

public class GetProductServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Boolean isLogin = (Boolean) session.getAttribute("isLogin");
		
		if (isLogin!=null && isLogin) {
			ProductDao dao = new ProductDao();
			List<Product> products = dao.findAllProducts();
			request.setAttribute("products", products);
			request.getRequestDispatcher("ShowProducts.jsp").forward(request, response);
		}else{
			response.sendRedirect("login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
