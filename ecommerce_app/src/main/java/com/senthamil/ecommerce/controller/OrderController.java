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

@WebServlet("/OrderController")
public class OrderController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("OrderController invoked");

		// Get form values
		String productname = request.getParameter("productname");
		String quantityStr = request.getParameter("quantity");
		System.out.println("productname:" + productname + ",quantity:" + quantityStr);

		Integer quantity = Integer.parseInt(quantityStr);

		// Fetch userId from session
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("LOGGED_IN_USER");
		int userId = loggedInUser.getId();
		System.out.println("Fetching UserId from Session ->" + userId);

		// store in db
		UserDAO dao = new UserDAO();
		dao.saveOrder(userId, productname, quantity);

		response.sendRedirect("home.jsp");

	}
}
