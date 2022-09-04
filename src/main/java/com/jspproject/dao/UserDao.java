package com.jspproject.dao;

import com.jspproject.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class UserDao extends HttpServlet {
	public static final int PASSWORD = 2;
	public static final int USERNAME = 1;
	public static final String JDBC_DRIVER = "org.postgresql.Driver";
	public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
	public static final String DATABASE_USERNAME = "postgres";
	public static final String DATABASE_PASSWORD = "123";

	public boolean checkCredentials(User user) {
		List<User> userCredentials = new ArrayList<>();

		String userName = user.getUserName();
		String password = user.getPassword();

		try {
			Class.forName(JDBC_DRIVER);
			Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet outputQuery = statement.executeQuery("SELECT * FROM admin_login");

			while (outputQuery.next()) {
				User credential = new User();

				credential.setUserName(outputQuery.getString(USERNAME));
				credential.setPassword(outputQuery.getString(PASSWORD));

				userCredentials.add(credential);
			}

			for (User users : userCredentials) {
				if ((users.getUserName()).equals(userName) && (users.getPassword().equals(password)))
					return true;
			}

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		return false;
	}

}
