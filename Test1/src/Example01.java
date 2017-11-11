import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import com.csmy.example.contain.User;
import com.csmy.example.dao.UserDao;
import com.csmy.example.utils.JDBCUtils;

public class Example01 {

	public static void main(String[] args) {
		UserDao dao = new UserDao();
		User user = new User();
		user.setName("admin");
		user.setPassword("admin");
		user.setEmail("1234@qq.com");
		user.setBirthday(new Date(1999,12,12));
		

	}	
}
