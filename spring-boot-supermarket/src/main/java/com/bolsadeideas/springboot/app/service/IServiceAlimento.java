package com.bolsadeideas.springboot.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.entity.Alimento;

@Service
public interface IServiceAlimento<T extends Alimento> {

	public List<T> findAll();
	public List<T> findAllByCategoria(String categoria);
	
}
