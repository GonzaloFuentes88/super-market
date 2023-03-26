package com.bolsadeideas.springboot.app.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.dao.IUserDao;
import com.bolsadeideas.springboot.app.models.entity.User;

@Service
public class ServiceUserImpl implements IServiceUser{

	@Autowired
	IUserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public User findByUsernameAndPassword(String username, String password) {
		return userDao.findByUsernameAndPassword(username, password);
	}

	@Override
	@Transactional(readOnly = true)
	public User findOne(Long id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		userDao.deleteById(id);
	}

	@Override
	@Transactional
	public void save(User user) {
		userDao.save(user);
	}
	
	
	
}
