package com.mifinity.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.mifinity.model.CreditCard;
import com.mifinity.model.User;


public class CreditCardDaoImpl extends Dao implements CreditCardDao<CreditCard, String> {
	 
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
 
    public CreditCard findById(String id) {
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

    public List<CreditCard> findByCardNumber() {
    	openCurrentSession();
		Query<User> query= getCurrentSession().createQuery("from CreditCard where cardnumber ilike :username and password=:password");
    	query.setParameter("username", user.getUsername());
    	query.setParameter("password", user.getPassword());
    	User u = (User) query.uniqueResult();
    	
    	return u; 
    	
    	List<CreditCard> creditCards = (List<CreditCard>) getCurrentSession().createQuery("from CreditCard").list();
    	closeCurrentSession();
    	
    	return creditCards;
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