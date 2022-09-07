package com.jspproject.servlet;

import com.jspproject.dao.UserDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.model.User;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("password");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserDao userDao = new UserDao();
		User user = new User();

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		user.setUserName(userName);
		user.setPassword(password);

		HttpSession session = request.getSession();

		if (userDao.checkCredentials(user)) {
			session.setAttribute("password", password);
			response.sendRedirect(request.getContextPath() + "/dashboard");
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}
}
