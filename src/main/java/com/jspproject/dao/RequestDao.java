package com.jspproject.dao;
import com.jspproject.model.Request;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestDao extends HttpServlet {
	public static final String JDBC_DRIVER = "org.postgresql.Driver";
	public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
	public static final String USERNAME = "postgres";
	public static final String PASSWORD = "123";
	
	public void contactUsRequest(Request request){
		String fullName = request.getFullName();
		String email = request.getEmail();
		String message = request.getMessage();
		Connection c = null;
		Statement statement = null;
		try {
			Class.forName(JDBC_DRIVER);
			c = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			c.setAutoCommit(false);
			statement = c.createStatement();
			String sql = "INSERT INTO ACTIVE(full_name,email,message,active) " + "VALUES ('" + fullName + "', '" + email	+ "', '" + message + "','1' );";
			statement.executeUpdate(sql);
			statement.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

}
