package com.jspproject.dao;

import com.jspproject.model.Request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

public class RequestDao extends HttpServlet {
	public static final String JDBC_DRIVER = "org.postgresql.Driver";
	public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
	public static final String USERNAME = "postgres";
	public static final String PASSWORD = "123";

	public void contactUsRequest(Request request) {
		String fullName = request.getFullName();
		String email = request.getEmail();
		String message = request.getMessage();
		Connection c = null;
		Statement statement = null;
		try {
			Class.forName(JDBC_DRIVER);
			c = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			statement = c.createStatement();
			String sql = "INSERT INTO ACTIVE(full_name,email,message,active) " + "VALUES ('" + fullName + "', '" + email
					+ "', '" + message + "','1' );";
			statement.executeUpdate(sql);
			statement.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public List<Request> requestData() {
		List<Request> requests = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM active");
			while (resultSet.next()) {
				Request request = new Request();
				request.setId(Integer.parseInt(resultSet.getString(1)));
				request.setFullName(resultSet.getString(2));
				request.setEmail(resultSet.getString(3));
				request.setMessage(resultSet.getString(4));
				request.setStatus(resultSet.getBoolean(5));
				requests.add(request);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return requests;
	}
}
