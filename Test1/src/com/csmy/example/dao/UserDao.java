package com.csmy.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.csmy.example.contain.User;
import com.csmy.example.utils.JDBCUtils;

public class UserDao {
	// 查询所有用户
	public List<User> findAllUsers() {
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();

			// 3
			stmt = conn.createStatement();

			// 4
			String sql = "select id,name,password,email,birthday from users";
			rs = stmt.executeQuery(sql);

			// 5
			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setId(rs.getInt("id"));
				users.add(user);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs);

			JDBCUtils.release(conn, stmt);
		}
		return users;
	}

	// 增
	public boolean addUser(User user) {
		boolean isSuccess = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 1
		try {
			conn = JDBCUtils.getConnection();

			// 3
			String sql = "insert into users(name,password,email,birthday) values(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			pstmt.setString(4, sdf.format(user.getBirthday()));
			int num = pstmt.executeUpdate();

			if (num > 0) {
				isSuccess = true;
			} 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(conn, stmt);
		}
		return isSuccess;
	}

	// 改
	public void updateTest(User user) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 1
		try {
			conn = JDBCUtils.getConnection();

			// 3
			String sql = "update users set password=?,email=?,birthday=? where name=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(4, user.getName());
			int num = pstmt.executeUpdate();
			if (num > 0) {
				System.out.println("修改成功");
			} else {
				System.out.println("修改失败");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(conn, stmt);
		}
	}

	// 删
	public void deleteTest() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 1
		try {
			conn = JDBCUtils.getConnection();

			// 3
			String sql = "delete from users where name=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "zs");
			int num = pstmt.executeUpdate();
			if (num > 0) {
				System.out.println("删除成功");
			} else {
				System.out.println("删除失败");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(conn, stmt);
		}
	}

	//根据用户名返回该用户
	public User findUserByName(String name) {
		User user = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			// 4
			String sql = "select * from users where name=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			// 5
			while (rs.next()) {
				user = new User();
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs);
			JDBCUtils.release(conn, stmt);
		}
		return user;
	}
}
