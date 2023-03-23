package com.bolsadeideas.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.dao.ICarnesDao;
import com.bolsadeideas.springboot.app.models.entity.Carne;

@Service(value = "serviceCarne")
public class ServiceCarneImpl implements IServiceAlimento<Carne>{
	
	@Autowired
	ICarnesDao carnesDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Carne> findAll() {
		return carnesDao.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public List<Carne> findAllByCategoria(String categoria) {
		return carnesDao.findAllByCategoria(categoria);
	}
	@Override
	@Transactional(readOnly = true)
	public Carne findOne(Long id) {
		return carnesDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void save(Carne t) {
		carnesDao.save(t);
	}
	@Override
	@Transactional
	public void delete(Long id) {
		carnesDao.deleteById(id);
	}
	
	

}
