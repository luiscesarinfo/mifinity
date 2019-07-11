package com.mifinity.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.mifinity.model.CreditCard;
import com.mifinity.service.CreditCardService;

@WebServlet(name="ccard", urlPatterns="/ccard")
public class CreditCardController extends Controller {
       
	private static final long serialVersionUID = 1111087078551487176L;

	private CreditCardService service;

	public CreditCardController() {
        super();
        service = new CreditCardService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CreditCard creditCard = new CreditCard();
		Long cardId = StringUtils.isEmpty(request.getParameter("id")) ? null : Long.parseLong(request.getParameter("id"));
		creditCard.setId(cardId);
		creditCard.setCardHolder(request.getParameter("cardholder"));
		creditCard.setCardNumber(request.getParameter("cardnumber"));
		creditCard.setExpiryDate(request.getParameter("expirydate"));
		
		try {
			if (cardId == null) {
				service.persist(creditCard);
			} else {
				service.update(creditCard);
			}

			response.sendRedirect(request.getContextPath()+ "/creditcardlist");
		} catch (Exception e) {
			response.sendRedirect("/javatest/creditcard/card.jsp?r=fail");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
