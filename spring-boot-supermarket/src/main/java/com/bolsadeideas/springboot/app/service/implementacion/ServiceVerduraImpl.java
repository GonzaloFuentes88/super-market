package com.bolsadeideas.springboot.app.service.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.dao.IVerdurasDao;
import com.bolsadeideas.springboot.app.models.entity.Verdura;
import com.bolsadeideas.springboot.app.service.interfaces.IServiceAlimento;

@Service(value = "serviceVerdura")
public class ServiceVerduraImpl implements IServiceAlimento<Verdura>{

	@Autowired
	IVerdurasDao verdurasDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Verdura> findAll() {
		return verdurasDao.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public List<Verdura> findAllByCategoria(String categoria) {
		return verdurasDao.findAllByCategoria(categoria);
	}
	@Override
	@Transactional(readOnly = true)
	public Verdura findOne(Long id) {
		return verdurasDao.findById(id).orElse(null);
	}
	@Override
	@Transactional
	public void save(Verdura t) {
		verdurasDao.save(t);
	}
	@Override
	@Transactional
	public void delete(Long id) {
		verdurasDao.deleteById(id);
	}
}
