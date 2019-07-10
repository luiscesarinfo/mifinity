package com.mifinity.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.mifinity.model.CreditCard;
import com.mifinity.service.CreditCardService;

@WebServlet(name="creditcardlist", urlPatterns="/creditcardlist")
public class CreditCardListController extends HttpServlet {

	private static final long serialVersionUID = 6554574018270711650L;

	private CreditCardService creditCardService;

	public CreditCardListController() {
        super();
        creditCardService = new CreditCardService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		StringUtils.isEmpty(search) {
			List<CreditCard> cards = creditCardService.findAll();
			request.setAttribute("cardsList", cards);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/creditcard/creditcardlist.jsp");
			dispatcher.forward(request,response);
		} else {
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
