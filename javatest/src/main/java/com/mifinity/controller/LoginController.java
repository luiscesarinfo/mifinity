package com.mifinity.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mifinity.model.User;
import com.mifinity.service.UserService;

@WebServlet("/signin")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 4616121606191466715L;

	private UserService userService;
	
    public LoginController() {
    	super();
    	userService = new UserService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("psw"));
		try {
			User authUser = userService.authenticate(user);

			if (authUser == null) {
				response.sendRedirect("/javatest/login/index.html?r=notfound");
			} else {
				request.getSession(true).setAttribute("currentUser", authUser);

				RequestDispatcher view = request.getRequestDispatcher("/creditcardlist");
				view.forward(request, response);  				
			}
		} catch (Exception e) {
			response.sendRedirect("/javatest/login/index.html?r=fail");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
