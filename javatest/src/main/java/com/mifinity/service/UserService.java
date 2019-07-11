package com.mifinity.service;

import java.util.List;

import com.mifinity.dao.impl.UserDaoImpl;
import com.mifinity.model.User;

public class UserService {

    private static UserDaoImpl dao;
    
    public UserService() {
        dao = new UserDaoImpl();
    }
 
    public void persist(User entity) {
        dao.persist(entity);
    }
 
    public void update(User entity) {
        dao.update(entity);
    }
 
    public User findById(Long id) {
        User user = dao.findById(id);
        return user;
    }
 
    public void delete(Long id) {
        User user = dao.findById(id);
        dao.delete(user);
    }
 
    public List<User> findAll() {
        List<User> users = dao.findAll();
        return users;
    }
 
    public void deleteAll() {
        dao.deleteAll();
    }
 
    public UserDaoImpl userDaoImpl() {
        return dao;
    }

	public User authenticate(User user) {
		dao.openCurrentSession();
		User u = dao.authenticate(user);
		dao.closeCurrentSession();
		return u;
	}
}
