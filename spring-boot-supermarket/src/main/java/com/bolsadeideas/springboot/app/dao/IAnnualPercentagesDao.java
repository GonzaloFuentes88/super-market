package com.bolsadeideas.springboot.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolsadeideas.springboot.app.models.entity.AnnualPercentages;

public interface IAnnualPercentagesDao extends JpaRepository<AnnualPercentages, Long>{

	public Optional<AnnualPercentages> findByYear(Long year);
	public void deleteByYear(Long year);
}
