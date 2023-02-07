package com.bolsadeideas.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.dao.ICarnesDao;
import com.bolsadeideas.springboot.app.entity.Carne;

@Service(value = "serviceCarne")
public class ServiceCarneImpl implements IServiceAlimento<Carne>{
	
	@Autowired
	ICarnesDao carnesDao;
	
	@Override
	public List<Carne> findAll() {
		return carnesDao.findAll();
	}
	@Override
	public List<Carne> findAllByCategoria(String categoria) {
		return carnesDao.findAllByCategoria(categoria);
	}

}
