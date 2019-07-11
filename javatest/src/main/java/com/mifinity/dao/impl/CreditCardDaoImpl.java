package com.mifinity.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

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
    		    .add(Restrictions.like("cardNumber", cardNumber, MatchMode.ANYWHERE)) 
    		    .list();

    	closeCurrentSession();

    	return results;
    }

    @SuppressWarnings("unchecked")
    public List<CreditCard> findByCardNumberAndUser(String cardNumber, Integer userId) {
    	openCurrentSession();

    	Criteria criteria = getCurrentSession().createCriteria(CreditCard.class, "cc");
    	criteria.createAlias("cc.user", "u", JoinType.INNER_JOIN);
    	criteria.add(Restrictions.eq("u.id", userId));
    	criteria.add(Restrictions.ilike("cardNumber", "%"+cardNumber+"%"));
    	List<CreditCard> results = criteria.list();

    	closeCurrentSession();
    	return results;
    }

    @SuppressWarnings("unchecked")
    public List<CreditCard> findByUser(Integer userId) {
    	openCurrentSession();

    	Criteria criteria = getCurrentSession().createCriteria(CreditCard.class, "cc");
    	criteria.createAlias("cc.user", "u", JoinType.INNER_JOIN);
    	criteria.add(Restrictions.eq("u.id", userId));
    	List<CreditCard> results = criteria.list();
   	
    	closeCurrentSession();
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