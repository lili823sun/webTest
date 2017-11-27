package com.csmy.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.csmy.example.contain.Product;
import com.csmy.example.contain.User;
import com.csmy.example.utils.JDBCUtils;

public class ProductDao {
	// 查询所有商品
	public List<Product> findAllProducts() {
		List<Product> products = new ArrayList<Product>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();

			// 3
			stmt = conn.createStatement();

			// 4
			String sql = "select id,name,price,category,pnum from products";
			rs = stmt.executeQuery(sql);

			// 5
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getString("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setNum(rs.getInt("pnum"));
				products.add(p);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs);

			JDBCUtils.release(conn, stmt);
		}
		return products;
	}

	// 根据所给商品id查询相应商品
	public Product findProductById(String id) {
		Product p = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			// 4
			String sql = "select id,name,price,category,pnum,imgurl,description from products where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			// 5
			while (rs.next()) {
				p = new Product();
				p.setId(rs.getString("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setCategory(rs.getString("category"));
				p.setNum(rs.getInt("pnum"));
				p.setImgUrl(rs.getString("imgurl"));
				p.setDescription(rs.getString("description"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs);
			JDBCUtils.release(conn, stmt);
		}
		return p;
	}

	// 改
	public boolean updateProduct(Product p) {
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 1
		try {
			conn = JDBCUtils.getConnection();

			// 3
			String sql = "update products set name=?,price=?,pnum=?,category=?,description=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getName());
			pstmt.setDouble(2, p.getPrice());
			pstmt.setInt(3, p.getNum());
			pstmt.setString(4, p.getCategory());
			pstmt.setString(5, p.getDescription());
			pstmt.setString(6, p.getId());

			int num = pstmt.executeUpdate();
			if (num > 0) {
				result = true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(conn, stmt);
		}
		return result;
	}

	// 删
	public boolean deleteUserById(String id) {
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 1
		try {
			conn = JDBCUtils.getConnection();

			// 3
			String sql = "delete from products where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int num = pstmt.executeUpdate();
			if (num > 0) {
				result = true;
			} else {
				result = false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(conn, stmt);
		}
		return result;
	}
}
