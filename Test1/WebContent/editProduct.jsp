<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.csmy.example.contain.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Product p = (Product)request.getAttribute("product");
%>
<form action="UpdateProduct" method="post">
	<input type="hidden" name="id" value="${product.id }"/>
	<table>
		<tr>
			<td>商品名称：</td>
			<td><input type="text" name="name" value="${product.name }" /></td>
			<td>商品价格：</td>
			<td><input type="text" name="price" value="${product.price }"/></td>
		</tr>
		<tr>
			<td>商品数量：</td>
			<td><input type="text" name="num" value="${product.num }"/></td>
			<td>商品类别：</td>
			<td><select name="category" id="category">
					<option>--选择商品类别--</option>
					<option value="文学">文学</option>
					<option value="生活">生活</option>
					<option value="计算机">计算机</option>
					<option value="外语">外语</option>
					<option value="经营">经营</option>
					<option value="励志">励志</option>
					<option value="社科">社科</option>
					<option value="学术">学术</option>
					<option value="少儿">少儿</option>
					<option value="艺术">艺术</option>
					<option value="原版">原版</option>
					<option value="科技">科技</option>
					<option value="考试">考试</option>
					<option value="生活百科">生活百科</option>
			</select></td>
		</tr>
		<tr>
			<td>商品图片：</td>
			<td colspan="3"><input type="file" size="30" value=""
				name="upload"></td>
		</tr>
		<tr>
			<td>商品描述：</td>
			<td colspan="3"><textarea name="description" rows="3" cols="60">${product.description }</textarea></td>
		</tr>
		<tr>
			<td align="center" colspan="4">
				<input type="submit" value="确定"/>
				<input type="reset" value="重置"/>
				<input type="button" onclick="history.go(-1)" value="返回"/>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>