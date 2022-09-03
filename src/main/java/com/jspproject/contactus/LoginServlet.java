package com.jspproject.contactus;

import com.jspproject.dao.UserDao;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.jspproject.model.Request;
import com.jspproject.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		UserDao userDao = new UserDao();
		User user = new User();

		String userName = req.getParameter("UserName");
		String password = req.getParameter("Password");
		user.setUserName(userName);
		user.setPassword(password);
		HttpSession session = req.getSession();
		try {
			if (userDao.checkCredentials(user)) {
				session.setAttribute("password", password);

				resp.sendRedirect(req.getContextPath() + "/dashboard");
			} else {
				session.removeAttribute("password");
				resp.sendRedirect(req.getContextPath() +"/login");
			}
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
}
