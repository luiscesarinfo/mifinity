package com.mifinity.dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import com.mifinity.dao.UserDao;
import com.mifinity.model.User;

public class UserDaoImpl extends Dao implements UserDao<User, Long> {
	 
    public UserDaoImpl() {
    	super();
    }
 
    public void persist(User entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().save(entity);
        closeCurrentSessionwithTransaction();
    }
 
    public void update(User entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().update(entity);
        closeCurrentSessionwithTransaction();
    }
 
    public User findById(Long id) {
        openCurrentSession();
        User user = (User) getCurrentSession().get(User.class, id);
        closeCurrentSession();

        return user; 
    }

    @SuppressWarnings("unchecked")
    public User authenticate(User user) {
		Query<User> query= getCurrentSession().createQuery("from User where username=:username and password=:password");
    	query.setParameter("username", user.getUsername());
    	query.setParameter("password", user.getPassword());
    	User u = (User) query.uniqueResult();
    	
    	return u; 
    }
 
    public void delete(User entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().delete(entity);
        closeCurrentSessionwithTransaction();
    }
 
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        openCurrentSession();
        List<User> users = (List<User>) getCurrentSession().createQuery("from User").list();
        closeCurrentSession();

        return users;
    }
 
    public void deleteAll() {
        openCurrentSessionwithTransaction();

        List<User> entityList = findAll();
        for (User entity : entityList) {
            delete(entity);
        }

        closeCurrentSessionwithTransaction();
    }
}