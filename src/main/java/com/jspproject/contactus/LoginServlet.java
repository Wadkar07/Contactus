package com.jspproject.contactus;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/admin/contactus")
public class LoginServlet extends HttpServlet{
	public static final String JDBC_DRIVER = "org.postgresql.Driver";
	public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
	public static final String USERNAME = "postgres";
	public static final String PASSWORD = "123";
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		String userName = req.getParameter("UserName");
		String password = req.getParameter("Password");
		try {
			Class.forName(JDBC_DRIVER);
	        Connection con = DriverManager.getConnection(
	                DATABASE_URL,
	                USERNAME, PASSWORD);
	        Statement statement = con.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM admin_login");
	        resultSet.next();
	        if(userName.equals(resultSet.getString("username")) && password.equals(resultSet.getString("password"))) {
	        	req.getRequestDispatcher("/Request").forward(req, resp);
	        }
	        else {
	        	req.getRequestDispatcher("admin.html").forward(req, resp);
	        }
	         
		}catch(Exception e){out.println(e.getMessage());}
	}
}
