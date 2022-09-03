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
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet{
	public static final String JDBC_DRIVER = "org.postgresql.Driver";
	public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
	public static final String USERNAME = "postgres";
	public static final String PASSWORD = "123";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Cache-Control", "no-cache,no-Store");
		HttpSession session = req.getSession();
		String password = (String)session.getAttribute("password");
		if(password!=null) {
			RequestDao requestDao = new RequestDao(); 	
			List<Request> contactRequests = requestDao.requestData();
			List<Request> activeRequests = new ArrayList<>();
			List<Request> archiveRequests = new ArrayList<>();
			for(Request contactrequest: contactRequests) {
				Request request = new Request();
				request.setId(contactrequest.getId());
				request.setFullName(contactrequest.getFullName());
				request.setEmail(contactrequest.getEmail());
				request.setMessage(contactrequest.getMessage());
				if(contactrequest.getStatus())
					activeRequests.add(request);
				else
					archiveRequests.add(request);
			}
			req.setAttribute("activeRequests", activeRequests);
			req.setAttribute("archiveRequests", archiveRequests);
			req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);	
		}
		else
			resp.sendRedirect(req.getContextPath()+"/login");
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Connection c = null;
		String sql=null;
		Statement statement = null;
		try {
		Class.forName(JDBC_DRIVER);
		c = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		c.setAutoCommit(false);
		statement = c.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT active FROM active where id ="+id);
		while(resultSet.next()) {
			if(resultSet.getBoolean(1)) {
				System.out.println("->");
			sql = "UPDATE active set active='f' where id="+id;}
			else
				sql = "UPDATE active set active='t' where id="+id;
		}
		statement.executeUpdate(sql);
		statement.close();
		c.commit();
		c.close();
	} catch (Exception e) {
		System.err.println(e.getClass().getName() + ": " + e.getMessage());
		System.exit(0);
	}
		this.doGet(req, resp);
	}
}
