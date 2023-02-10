package com.bolsadeideas.springboot.app.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="verduras")
public class Verdura extends Alimento implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Boolean congelado;
	
	public Verdura() {
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Boolean getCongelado() {
		return congelado;
	}
	public void setCongelado(Boolean congelado) {
		this.congelado = congelado;
	} 
	
	
	
	
	
}
