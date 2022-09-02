package com.jspproject.contactus;

import com.jspproject.dao.RequestDao;
import com.jspproject.model.Request;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/contactus")
public class ContactUsServlet extends HttpServlet {
	protected RequestDao requestDao = new RequestDao();
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  req.getRequestDispatcher("/contactus.jsp").forward(req, resp);
  }
  

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fullName = req.getParameter("fullName");
		String email = req.getParameter("email");
		String message = req.getParameter("message");
		
		Request request = new Request();
		request.setFullName(fullName);
		request.setEmail(email);
		request.setMessage(message);
		
		requestDao.contactUsRequest(request);
		req.getRequestDispatcher("/contactus.jsp").forward(req, resp);
	}

}
