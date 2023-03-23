package com.bolsadeideas.springboot.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolsadeideas.springboot.app.models.entity.Carne;

public interface ICarnesDao extends JpaRepository<Carne, Long>{

	public List<Carne> findAllByCategoria(String categoria);
}
