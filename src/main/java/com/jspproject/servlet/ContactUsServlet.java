package com.jspproject.servlet;

import com.jspproject.dao.RequestDao;
import com.jspproject.model.Request;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/contactus")
public class ContactUsServlet extends HttpServlet {
	protected RequestDao requestDao = new RequestDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/contactus.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String message = request.getParameter("message");

		Request contactRequest = new Request();
		contactRequest.setFullName(fullName);
		contactRequest.setEmail(email);
		contactRequest.setMessage(message);

		requestDao.contactUsRequest(contactRequest);
		request.getRequestDispatcher("/contactus.jsp").forward(request, response);
	}
}
