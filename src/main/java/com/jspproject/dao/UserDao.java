package com.jspproject.dao;

import com.jspproject.model.Request;
import com.jspproject.model.User;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;


public class UserDao extends HttpServlet{
	public static final String JDBC_DRIVER = "org.postgresql.Driver";
	public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
	public static final String USERNAME = "postgres";
	public static final String PASSWORD = "123";
	
	
	public boolean checkCredentials(User user)
	{
		List<User> userCredentials = new ArrayList<>();
		String userName = user.getUserName();
		String password = user.getPassword();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			c = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
			Statement statement = c.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM admin_login");
//			resultSet.next();
			while(resultSet.next()) {
				User credential = new User();
				credential.setUserName(resultSet.getString(1));
				credential.setPassword(resultSet.getString(2));
				userCredentials.add(credential);
			}
			for(User users : userCredentials) {
				if((users.getUserName()).equals(userName)&&(users.getPassword().equals(password)))
					return true;
			}
			
		}catch(Exception e){System.out.println(e.getMessage());}
		return false;
	}
	
}
