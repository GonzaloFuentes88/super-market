package com.bolsadeideas.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.dao.IVerdurasDao;
import com.bolsadeideas.springboot.app.entity.Verdura;

@Service(value = "serviceVerdura")
public class ServiceVerduraImpl implements IServiceAlimento<Verdura>{

	@Autowired
	IVerdurasDao verdurasDao;
	
	@Override
	public List<Verdura> findAll() {
		return verdurasDao.findAll();
	}
	@Override
	public List<Verdura> findAllByCategoria(String categoria) {
		return verdurasDao.findAllByCategoria(categoria);
	}
}
