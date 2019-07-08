package com.mifinity.service;

import java.util.List;

import com.mifinity.dao.CreditCardDaoImpl;
import com.mifinity.domain.CreditCard;

public class CreditCardService {

    private static CreditCardDaoImpl dao;
    
    public CreditCardService() {
        dao = new CreditCardDaoImpl();
    }
 
    public void persist(CreditCard entity) {
        dao.openCurrentSessionwithTransaction();
        dao.persist(entity);
        dao.closeCurrentSessionwithTransaction();
    }
 
    public void update(CreditCard entity) {
        dao.openCurrentSessionwithTransaction();
        dao.update(entity);
        dao.closeCurrentSessionwithTransaction();
    }
 
    public CreditCard findById(String id) {
        dao.openCurrentSession();
        CreditCard book = dao.findById(id);
        dao.closeCurrentSession();
        return book;
    }
 
    public void delete(String id) {
        dao.openCurrentSessionwithTransaction();
        CreditCard book = dao.findById(id);
        dao.delete(book);
        dao.closeCurrentSessionwithTransaction();
    }
 
    public List<CreditCard> findAll() {
        dao.openCurrentSession();
        List<CreditCard> books = dao.findAll();
        dao.closeCurrentSession();
        return books;
    }
 
    public void deleteAll() {
        dao.openCurrentSessionwithTransaction();
        dao.deleteAll();
        dao.closeCurrentSessionwithTransaction();
    }
 
    public CreditCardDaoImpl creditCardDaoImpl() {
        return dao;
    }
}
