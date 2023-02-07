package com.bolsadeideas.springboot.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.dao.IUserDao;
import com.bolsadeideas.springboot.app.entity.User;

@Service
public class ServiceUserImpl implements IServiceUser{

	@Autowired
	IUserDao userDao;
	
	
	@Transactional(readOnly = true)
	public User findByUsernameAndPassword(String username, String password) {
		return userDao.findByUsernameAndPassword(username, password);
	}
	
}
