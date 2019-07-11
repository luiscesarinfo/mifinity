package com.mifinity.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet { 

	private static final long serialVersionUID = -5360300038421039595L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		validateSession(req, resp);
	}

	private void validateSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if (req.getSession(false).getAttribute("name") == null) {
			resp.sendRedirect(req.getContextPath()+ "/login");
		}
	}
}
