package com.bolsadeideas.springboot.app.service.interfaces;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Mes;

public interface IServiceMes {
	public Mes findOne(Long id);
	
	public List<Mes> findAll();
	
	public void delete(Long id);
	
	public void save(Mes mes);

}
