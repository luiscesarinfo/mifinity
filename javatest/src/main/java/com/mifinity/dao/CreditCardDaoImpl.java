package com.mifinity.dao;

import java.util.List;

import com.mifinity.model.CreditCard;


public class CreditCardDaoImpl extends Dao implements CreditCardDao<CreditCard, String> {
	 
    public CreditCardDaoImpl() {
    	super();
    }
 
    public void persist(CreditCard entity) {
        getCurrentSession().save(entity);
    }
 
    public void update(CreditCard entity) {
        getCurrentSession().update(entity);
    }
 
    public CreditCard findById(String id) {
        CreditCard creditCard = (CreditCard) getCurrentSession().get(CreditCard.class, id);
        return creditCard; 
    }
 
    public void delete(CreditCard entity) {
        getCurrentSession().delete(entity);
    }
 
    @SuppressWarnings("unchecked")
    public List<CreditCard> findAll() {
        List<CreditCard> creditCards = (List<CreditCard>) getCurrentSession().createQuery("from CreditCard").list();
        return creditCards;
    }
 
    public void deleteAll() {
        List<CreditCard> entityList = findAll();
        for (CreditCard entity : entityList) {
            delete(entity);
        }
    }
}