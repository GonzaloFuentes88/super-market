package com.bolsadeideas.springboot.app.service.interfaces;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.AnnualPercentages;

public interface IServiceAnnualPercentages {
	public List<AnnualPercentages> findAll();
	public void deleteByYear(Long year);
	public void save(AnnualPercentages annualPercentages);
	public AnnualPercentages findByYear(Long year);

}
