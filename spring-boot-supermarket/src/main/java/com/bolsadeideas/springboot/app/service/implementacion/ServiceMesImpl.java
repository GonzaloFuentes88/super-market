package com.bolsadeideas.springboot.app.service.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.dao.IMesDao;
import com.bolsadeideas.springboot.app.models.entity.Mes;
import com.bolsadeideas.springboot.app.service.interfaces.IServiceMes;

@Service
public class ServiceMesImpl implements IServiceMes{

	@Autowired
	public IMesDao mesDao;
	
	@Override
	public Mes findOne(Long id) {
		return mesDao.findById(id).orElse(null);
	}

	@Override
	public List<Mes> findAll() {
		return mesDao.findAll();
	}

	@Override
	public void delete(Long id) {
		mesDao.deleteById(id);
	}

	@Override
	public void save(Mes mes) {
		if(mes != null) {
			mesDao.save(mes);
		}
		
		
	}
	
	

}
