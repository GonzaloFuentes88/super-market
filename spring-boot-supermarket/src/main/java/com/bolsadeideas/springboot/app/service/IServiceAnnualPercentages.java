package com.bolsadeideas.springboot.app.service;

import java.util.List;

import com.bolsadeideas.springboot.app.entity.AnnualPercentages;

public interface IServiceAnnualPercentages {
	public List<AnnualPercentages> findAll();
	public void deleteByYearAndCategoria(Long year, String categoria);
	public void save(AnnualPercentages annualPercentages);
	public AnnualPercentages findByYearAndCategoria(Long year, String categoria);

}
