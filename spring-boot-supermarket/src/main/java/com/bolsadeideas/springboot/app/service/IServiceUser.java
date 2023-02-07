package com.bolsadeideas.springboot.app.service;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.entity.User;

@Service
public interface IServiceUser {

	public User findByUsernameAndPassword(String username, String password);
}
