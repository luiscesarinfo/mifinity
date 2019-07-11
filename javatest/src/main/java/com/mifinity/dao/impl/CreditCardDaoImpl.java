package com.mifinity.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.mifinity.dao.CreditCardDao;
import com.mifinity.model.CreditCard;


public class CreditCardDaoImpl extends Dao implements CreditCardDao<CreditCard, Long> {
	 
    public CreditCardDaoImpl() {
    	super();
    }
 
    public void persist(CreditCard entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().save(entity);
        closeCurrentSessionwithTransaction();
    }
 
    public void update(CreditCard entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().update(entity);
        closeCurrentSessionwithTransaction();

    }
 
    public CreditCard findById(Long id) {
        openCurrentSession();
        CreditCard creditCard = (CreditCard) getCurrentSession().get(CreditCard.class, id);
        closeCurrentSession();

        return creditCard; 
    }
 
    public void delete(CreditCard entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().delete(entity);
        closeCurrentSessionwithTransaction();
    }
 
    @SuppressWarnings("unchecked")
    public List<CreditCard> findAll() {
        openCurrentSession();
        List<CreditCard> creditCards = (List<CreditCard>) getCurrentSession().createQuery("from CreditCard").list();
        closeCurrentSession();

        return creditCards;
    }

    @SuppressWarnings("unchecked")
    public List<CreditCard> findByCardNumber(String cardNumber) {
    	openCurrentSession();

    	List<CreditCard> results = getCurrentSession().createCriteria(CreditCard.class)
    		    .add(Restrictions.like("cardNumber", "%"+cardNumber+"%"))
    		    .list();
    	return results;
    }
 
    public void deleteAll() {
        openCurrentSessionwithTransaction();

        List<CreditCard> entityList = findAll();
        for (CreditCard entity : entityList) {
            delete(entity);
        }

        closeCurrentSessionwithTransaction();
    }
}