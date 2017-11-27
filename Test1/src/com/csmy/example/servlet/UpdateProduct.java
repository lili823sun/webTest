package com.csmy.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csmy.example.contain.Product;
import com.csmy.example.dao.ProductDao;

public class UpdateProduct extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		double price = Double.valueOf(request.getParameter("price"));
		int num = Integer.valueOf(request.getParameter("num"));
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		
		Product p = new Product();
		p.setId(id);
		p.setName(name);
		p.setPrice(price);
		p.setNum(num);
		p.setCategory(category);
		p.setDescription(description);
		
		ProductDao dao = new ProductDao();
		boolean result = dao.updateProduct(p);
		if (result) {
			out.println("<javacript>alert('商品修改成功');</javascript>");
			String newUrl = response.encodeRedirectURL("GetProductServlet");
			response.sendRedirect(newUrl);
		}else{
			out.println("<javacript>alert('商品修改失败');</javascript>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
