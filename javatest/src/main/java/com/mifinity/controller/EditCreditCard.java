package com.mifinity.controller;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mifinity.model.CreditCard;
import com.mifinity.service.CreditCardService;

@WebServlet(name="editcard", urlPatterns="/editcard")
public class EditCreditCard extends Controller {
       
	private static final long serialVersionUID = 4537986135617971369L;

	private CreditCardService creditCardService;

	public EditCreditCard() {
        super();
        creditCardService = new CreditCardService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			validateSession(request, response);
			
			String cardId = request.getParameter("id");
			CreditCard card = creditCardService.findById(Long.parseLong(cardId));
			request.setAttribute("card", card);
			getServletContext().getRequestDispatcher("/creditcard/card.jsp").forward(request,response);
		} catch (AuthenticationException e) {
			response.sendRedirect(request.getContextPath()+ "/login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
