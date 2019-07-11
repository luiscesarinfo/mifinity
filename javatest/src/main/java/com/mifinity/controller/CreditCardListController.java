package com.mifinity.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.mifinity.model.CreditCard;
import com.mifinity.service.CreditCardService;

@WebServlet(name="creditcardlist", urlPatterns="/creditcardlist")
public class CreditCardListController extends Controller {

	private static final long serialVersionUID = 6554574018270711650L;

	private CreditCardService creditCardService;

	public CreditCardListController() {
        super();
        creditCardService = new CreditCardService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		
		List<CreditCard> cards = new ArrayList<CreditCard>();
		if (StringUtils.isEmpty(search)) {
			cards = creditCardService.findAll();
		} else {
			cards = creditCardService.findByCardNumber(search);
		}

		request.setAttribute("cardsList", cards);
		getServletContext().getRequestDispatcher("/creditcard/list.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
