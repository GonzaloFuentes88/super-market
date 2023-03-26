package com.bolsadeideas.springboot.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.dao.IAnnualPercentagesDao;
import com.bolsadeideas.springboot.app.models.entity.AnnualPercentages;


@Service
public class ServiceAnnualPercentagesImpl implements IServiceAnnualPercentages {
	
	@Autowired
	IAnnualPercentagesDao annualPercentagesDao;
	
	@Override
	public AnnualPercentages findByYearAndCategoria(Long year, String categoria) {
		return annualPercentagesDao.findByYearAndCategoria(year, categoria).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AnnualPercentages> findAll() {
		return annualPercentagesDao.findAll();
	}
	
	
	@Override
	public void deleteByYearAndCategoria(Long year, String categoria) {
		annualPercentagesDao.deleteByYearAndCategoria(year,categoria);
	}
	@Override
	public void save(AnnualPercentages annualPercentages) {
		annualPercentagesDao.save(annualPercentages);
	}
	
}
