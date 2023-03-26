package com.bolsadeideas.springboot.app.service.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.dao.IAnnualPercentagesDao;
import com.bolsadeideas.springboot.app.models.entity.AnnualPercentages;
import com.bolsadeideas.springboot.app.service.interfaces.IServiceAnnualPercentages;


@Service
public class ServiceAnnualPercentagesImpl implements IServiceAnnualPercentages {
	
	@Autowired
	IAnnualPercentagesDao annualPercentagesDao;
	
	@Override
	public AnnualPercentages findByYear(Long year) {
		return annualPercentagesDao.findById(year).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AnnualPercentages> findAll() {
		return annualPercentagesDao.findAll();
	}
	
	
	@Override
	public void deleteByYear(Long year) {
		annualPercentagesDao.deleteById(year);
	}
	@Override
	public void save(AnnualPercentages annualPercentages) {
		annualPercentagesDao.save(annualPercentages);
	}
	
}
