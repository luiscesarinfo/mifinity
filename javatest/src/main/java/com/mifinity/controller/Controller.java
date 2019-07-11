package com.mifinity.controller;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Controller extends HttpServlet { 

	private static final long serialVersionUID = -5360300038421039595L;
	
	protected void validateSession(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException {
		if (req.getSession(false) == null || (req.getSession(false) != null && req.getSession(false).getAttribute("currentUser") == null)) {
			throw new AuthenticationException("No user logged in.");
		}
	}
	
}
