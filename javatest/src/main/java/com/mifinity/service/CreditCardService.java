package com.mifinity.service;

import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.mifinity.dao.CreditCardDaoImpl;
import com.mifinity.model.CreditCard;

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
    	if (!NumberUtils.isDigits(entity.getCardNumber()) || isInvalidExpityDate(entity.getExpiryDate())) {
    		throw new Exception("Invalid data.");
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

	public void update(CreditCard entity) {
    	dao.update(entity);
    }
 
    public CreditCard findById(String id) {
        CreditCard card = dao.findById(id);
        return card;
    }
 
    public void delete(String id) {
        CreditCard card = dao.findById(id);
        dao.delete(card);
    }
 
    public List<CreditCard> findAll() {
        List<CreditCard> cards = dao.findAll();
        return cards;
    }
 
    public void deleteAll() {
        dao.deleteAll();
    }
 
    public CreditCardDaoImpl creditCardDaoImpl() {
        return dao;
    }
}
