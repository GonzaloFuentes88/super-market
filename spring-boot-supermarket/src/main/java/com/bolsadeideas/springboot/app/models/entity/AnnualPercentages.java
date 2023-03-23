package com.bolsadeideas.springboot.app.models.entity;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "annual_percentages")
public class AnnualPercentages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private Long year;

	@Getter
	@Setter
	private Float ene;
	
	@Getter
	@Setter
	private Float feb;
	
	@Getter
	@Setter
	private Float mar;
	
	@Getter
	@Setter
	private Float abr;
	
	@Getter
	@Setter
	private Float may;
	
	@Getter
	@Setter
	private Float jun;
	
	@Getter
	@Setter
	private Float jul;
	
	@Getter
	@Setter
	private Float ago;
	
	@Getter
	@Setter
	private Float sep;
	
	@Getter
	@Setter
	private Float oct;
	
	@Getter
	@Setter
	private Float nov;
	
	@Getter
	@Setter
	private Float dic;
	
	@Getter
	@Setter
	private String categoria;
	
	public AnnualPercentages() {
	}
	
	public List<Float> getMonths(){
		return (List<Float>) Arrays.asList(ene,feb,mar,abr,may,jun,jul,ago,sep,oct,nov,dic);
	}
	
	
}
