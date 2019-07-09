package com.mifinity.service;

import java.util.List;

import com.mifinity.dao.UserDaoImpl;
import com.mifinity.model.User;

public class UserService {

    private static UserDaoImpl dao;
    
    public UserService() {
        dao = new UserDaoImpl();
    }
 
    public void persist(User entity) {
        dao.openCurrentSessionwithTransaction();
        dao.persist(entity);
        dao.closeCurrentSessionwithTransaction();
    }
 
    public void update(User entity) {
        dao.openCurrentSessionwithTransaction();
        dao.update(entity);
        dao.closeCurrentSessionwithTransaction();
    }
 
    public User findById(String id) {
        dao.openCurrentSession();
        User book = dao.findById(id);
        dao.closeCurrentSession();
        return book;
    }
 
    public void delete(String id) {
        dao.openCurrentSessionwithTransaction();
        User book = dao.findById(id);
        dao.delete(book);
        dao.closeCurrentSessionwithTransaction();
    }
 
    public List<User> findAll() {
        dao.openCurrentSession();
        List<User> books = dao.findAll();
        dao.closeCurrentSession();
        return books;
    }
 
    public void deleteAll() {
        dao.openCurrentSessionwithTransaction();
        dao.deleteAll();
        dao.closeCurrentSessionwithTransaction();
    }
 
    public UserDaoImpl userDaoImpl() {
        return dao;
    }
}
