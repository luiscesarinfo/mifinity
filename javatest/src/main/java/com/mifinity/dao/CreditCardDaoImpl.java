package com.mifinity.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.mifinity.domain.CreditCard;


public class CreditCardDaoImpl implements CreditCardDao<CreditCard, String> {
	 
    private Session currentSession;
     
    private Transaction currentTransaction;
 
    public CreditCardDaoImpl() {
    }
 
    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }
 
    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
     
    public void closeCurrentSession() {
        currentSession.close();
    }
     
    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
     
    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }
 
    public Session getCurrentSession() {
        return currentSession;
    }
 
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
 
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
 
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
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