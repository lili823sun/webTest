<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.csmy.example.contain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<%
		List<Product> products = (List<Product>) request.getAttribute("products");
	%>
	<input type="button" value="添加商品" onclick="addProduct.jsp
	
	">
	<table border="1">
		<thead>
			<th>商品id</th>
			<th>商品名称</th>
			<th>商品价格</th>
			<th>商品数量</th>
			<th>商品类别</th>
			<th>编辑</th>
			<th>删除</th>
		</thead>
		<tbody>
			<%
				if (products.size() == 0) {
			%>
			<tr>
				<td colspan="7" align="center">很抱歉，目前没有商品了</td>
			</tr>
			<%
				} else {
					for (Product p : products) {
			%>
			<tr>
				<td><%=p.getId()%></td>
				<td><%=p.getName()%></td>
				<td><%=p.getPrice()%></td>
				<td><%=p.getNum()%></td>
				<td><%=p.getCategory()%></td>
				<td><a href="EditProductServlet?product_id=<%=p.getId()%>">
						<img src="imgs/i_edit.gif" border="0" style="cursor: hand" />
				</a></td>
				<td><a href="DeleteProductServlet?product_id=<%=p.getId()%>">
						<img src="imgs/i_del.gif" border="0" style="cursor: hand" />
				</a></td>
			</tr>
			<%
				}
				}
			%>
		</tbody>
	</table>
</body>
</html>