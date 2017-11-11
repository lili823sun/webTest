package com.csmy.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
	//��ȡ���ݿ�����
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		// 1
		Class.forName("com.mysql.jdbc.Driver");

		// 2
		String url = "jdbc:mysql://localhost:3307/jdbc";
		conn = DriverManager.getConnection(url, "root", "123456");
		return conn;
	}

	//�رս����
	public static void release(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rs = null;
	}
	
	//�ر�conn��statement
	public static void release(Connection conn, Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		stmt = null;

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn = null;
	}
}
