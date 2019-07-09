package com.mifinity.dao;

import java.util.List;

import com.mifinity.model.User;

public class UserDaoImpl extends Dao implements UserDao<User, String> {
	 
    public UserDaoImpl() {
    	super();
    }
 
    public void persist(User entity) {
        getCurrentSession().save(entity);
    }
 
    public void update(User entity) {
        getCurrentSession().update(entity);
    }
 
    public User findById(String id) {
        User user = (User) getCurrentSession().get(User.class, id);
        return user; 
    }
 
    public void delete(User entity) {
        getCurrentSession().delete(entity);
    }
 
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        List<User> users = (List<User>) getCurrentSession().createQuery("from User").list();
        return users;
    }
 
    public void deleteAll() {
        List<User> entityList = findAll();
        for (User entity : entityList) {
            delete(entity);
        }
    }
}