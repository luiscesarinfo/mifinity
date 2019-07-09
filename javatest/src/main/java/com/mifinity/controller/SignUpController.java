package com.mifinity.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mifinity.model.User;
import com.mifinity.service.UserService;

@WebServlet("/signup")
public class SignUpController extends HttpServlet {
       
	private static final long serialVersionUID = -4076264317874604197L;

	private UserService userService;
	
    public SignUpController() {
        super();
        userService = new UserService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User newUser = new User();
		newUser.setUsername(request.getParameter("username"));
		newUser.setPassword(request.getParameter("psw"));
		
		userService.persist(newUser);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
