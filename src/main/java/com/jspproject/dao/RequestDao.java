package com.jspproject.dao;

import com.jspproject.model.Request;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class RequestDao extends HttpServlet {
	public static final int ID = 1;
	public static final int FULLNAME = 2;
	public static final int EMAIL = 3;
	public static final int MESSAGE = 4;
	public static final int STATUS = 5;
	public static final String JDBC_DRIVER = "org.postgresql.Driver";
	public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
	public static final String DATABASE_USERNAME = "postgres";
	public static final String DATABASE_PASSWORD = "123";

	public void contactUsRequest(Request request) {
		String fullName = request.getFullName();
		String email = request.getEmail();
		String message = request.getMessage();
	
		try {
			Class.forName(JDBC_DRIVER);
			Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			Statement statement = connection.createStatement();
			
			String inputQuery = "INSERT INTO ACTIVE(full_name,email,message,active) " + "VALUES ('" + fullName + "', '"
					+ email + "', '" + message + "','1' );";
			
			statement.executeUpdate(inputQuery);
			statement.close();
			connection.close();
		} catch (Exception exception) {
			System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
		}
	}

	public List<Request> contactRequestData() {
		List<Request> contactRequests = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet outputQuery = statement.executeQuery("SELECT * FROM active");

			while (outputQuery.next()) {
				Request contactRequest = new Request();
				
				contactRequest.setId(Integer.parseInt(outputQuery.getString(ID)));
				contactRequest.setFullName(outputQuery.getString(FULLNAME));
				contactRequest.setEmail(outputQuery.getString(EMAIL));
				contactRequest.setMessage(outputQuery.getString(MESSAGE));
				contactRequest.setStatus(outputQuery.getBoolean(STATUS));
				
				contactRequests.add(contactRequest);
			}
		} catch (Exception exception) {
			System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
		}
		return contactRequests;
	}
}
