package com.jspproject.contactus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.dao.RequestDao;
import com.jspproject.model.Request;

@SuppressWarnings("serial")
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	public static final String JDBC_DRIVER = "org.postgresql.Driver";
	public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
	public static final String USERNAME = "postgres";
	public static final String PASSWORD = "123";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache,no-Store");
		HttpSession session = request.getSession();
		String password = (String) session.getAttribute("password");
		if (password != null) {
			RequestDao requestDao = new RequestDao();
			
			List<Request> contactRequests = requestDao.contactRequestData();
			List<Request> activeRequests = new ArrayList<>();
			List<Request> archiveRequests = new ArrayList<>();
			
			for (Request contact : contactRequests) {
				Request contactRequest = new Request();
				contactRequest.setId(contact.getId());
				contactRequest.setFullName(contact.getFullName());
				contactRequest.setEmail(contact.getEmail());
				contactRequest.setMessage(contact.getMessage());
			
				if (contact.getStatus())
					activeRequests.add(contactRequest);
				else
					archiveRequests.add(contactRequest);
			}
			
			request.setAttribute("activeRequests", activeRequests);
			request.setAttribute("archiveRequests", archiveRequests);
			request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
		} else
			response.sendRedirect(request.getContextPath() + "/login");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOfContactRequest = request.getParameter("id");
		String query = null;
		try {
			Class.forName(JDBC_DRIVER);
			Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet outputQuery = statement.executeQuery("SELECT active FROM active where id =" + idOfContactRequest);
			
			while (outputQuery.next()) {
				if (outputQuery.getBoolean(1)) {
					query = "UPDATE active set active='f' where id=" + idOfContactRequest;
				} else
					query = "UPDATE active set active='t' where id=" + idOfContactRequest;
			}
			statement.executeUpdate(query);
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		this.doGet(request, response);
	}
}
