package com.mifinity.controller;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mifinity.service.CreditCardService;

@WebServlet(name="deletecard", urlPatterns="/deletecard")
public class DeleteCreditCard extends Controller {
       
	private static final long serialVersionUID = -2772676346542908819L;

	private CreditCardService creditCardService;

	public DeleteCreditCard() {
        super();
        creditCardService = new CreditCardService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			validateSession(request, response);
			
			String cardId = request.getParameter("id");
			creditCardService.delete(Long.parseLong(cardId));
			response.sendRedirect(request.getContextPath()+ "/creditcardlist");
		} catch (AuthenticationException e) {
			response.sendRedirect(request.getContextPath()+ "/login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
