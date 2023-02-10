package com.bolsadeideas.springboot.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "annua_percentages")
public class AnnualPercentages {
	
	@Id
	private Long año;

	@Getter
	@Setter
	private Double ene;
	
	@Getter
	@Setter
	private Double feb;
	
	@Getter
	@Setter
	private Double abr;
	
	@Getter
	@Setter
	private Double may;
	
	@Getter
	@Setter
	private Double jun;
	
	@Getter
	@Setter
	private Double jul;
	
	@Getter
	@Setter
	private Double ago;
	
	@Getter
	@Setter
	private Double sep;
	
	@Getter
	@Setter
	private Double oct;
	
	@Getter
	@Setter
	private Double nov;
	
	@Getter
	@Setter
	private Double dic;
	
	public AnnualPercentages() {
	}
	
	
}
