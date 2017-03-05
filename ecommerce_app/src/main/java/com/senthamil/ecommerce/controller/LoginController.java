package com.senthamil.ecommerce.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.senthamil.ecommerce.dao.UserDAO;
import com.senthamil.ecommerce.model.User;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("LoginController invoked");

		// Get form values
		String emailId = request.getParameter("email_id");
		String password = request.getParameter("user_password");
		System.out.println("email_id:" + emailId + ",userPasswor:" + password);

		UserDAO dao = new UserDAO();
		User user = dao.validate(emailId, password);
		if (user != null) {
			System.out.println("Valid User " + user);
		} else {
			System.out.println("invalid user");
		}

		// Store the user Details in session
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("LOGGED_IN_USER", user);

			// Fetch from the session
			User loggedInUser = (User) session.getAttribute("LOGGED_IN_USER");
			System.out.println(loggedInUser);

			// Logout
			// session.invalidate();

		}

		response.sendRedirect("home.jsp");

	}
}
