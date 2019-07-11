package com.mifinity.service;

import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.mifinity.dao.impl.CreditCardDaoImpl;
import com.mifinity.model.CreditCard;
import com.mifinity.model.User;
import com.mifinity.model.UserRole;

public class CreditCardService {

    private static CreditCardDaoImpl dao;
    
    public CreditCardService() {
        dao = new CreditCardDaoImpl();
    }
 
    public void persist(CreditCard entity) throws Exception {
    	validateData(entity);
        dao.persist(entity);
    }
 
    private void validateData(CreditCard entity) throws Exception {
    	if (isInvalidCardNumber(entity.getCardNumber()) || isInvalidExpityDate(entity.getExpiryDate())) {
    		throw new Exception("Invalid data.");
    	}
	}

    private boolean isInvalidCardNumber(String cardNumber) {
    	if (!NumberUtils.isDigits(cardNumber) || cardNumber.length() < 16 || cardNumber.length() > 16) {
    		return true;
    	} else {
    		return false;
    	}
    }

	private boolean isInvalidExpityDate(String expiryDate) {
		if (StringUtils.isEmpty(expiryDate)) {
			return true;
		}
		String[] expiry = expiryDate.split("/");
		if (expiry.length != 2) {
			return true;
		}
		
		
		try {
			YearMonth yearMonth = YearMonth.parse("20"+ expiry[0] + "-" + expiry[1]);
			if (yearMonth != null) {
				return false;
			}
		} catch(DateTimeParseException timeException) {
			return true;
		}

		return true;
	}

	public void update(CreditCard creditCard) {
		CreditCard entity = findById(creditCard.getId());
		creditCard.setUser(entity.getUser());
    	dao.update(creditCard);
    }
 
    public CreditCard findById(Long id) {
        CreditCard card = dao.findById(id);
        return card;
    }
 
    public void delete(Long id) {
        CreditCard card = dao.findById(id);
        dao.delete(card);
    }
 
    public List<CreditCard> findByUser(User currentUser) {
    	List<CreditCard> cards = new ArrayList<CreditCard>();
    	if (currentUser.getUserRole() == UserRole.ADMIN) {
    		cards = dao.findAll();
    	} else {
    		cards = dao.findByUser(currentUser.getId());
    	}

    	return cards;
    }
 
    public List<CreditCard> findByCardNumber(String cardNumber, User currentUser) {
    	List<CreditCard> cards = new ArrayList<CreditCard>();
    	if (currentUser.getUserRole() == UserRole.ADMIN) {
    		cards = dao.findByCardNumber(cardNumber);
    	} else {
    		cards = dao.findByCardNumberAndUser(cardNumber, currentUser.getId());
    	}
    	return cards;
    }

	public void deleteAll() {
        dao.deleteAll();
    }
 
    public CreditCardDaoImpl creditCardDaoImpl() {
        return dao;
    }
}
