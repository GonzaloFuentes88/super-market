package com.bolsadeideas.springboot.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.entity.Alimento;

@Service
public interface IServiceAlimento<T extends Alimento> {

	public List<T> findAll();
	public T findOne(Long id);
	public List<T> findAllByCategoria(String categoria);
	
	public void save(T t);
	public void delete(Long id);
	
}
