package com.bolsadeideas.springboot.app.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.models.entity.User;

@Service
public interface IServiceUser {

	public User findByUsernameAndPassword(String username, String password);
	
	public User findOne(Long id);
	
	public List<User> findAll();
	
	public void delete(Long id);
	
	public void save(User user);
}
