package com.bolsadeideas.springboot.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolsadeideas.springboot.app.entity.User;

public interface IUserDao extends JpaRepository<User, Long>{

	public User findByUsernameAndPassword(String username, String password);
}
