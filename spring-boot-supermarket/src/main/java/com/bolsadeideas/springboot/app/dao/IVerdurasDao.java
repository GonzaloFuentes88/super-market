package com.bolsadeideas.springboot.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolsadeideas.springboot.app.entity.Verdura;

public interface IVerdurasDao extends JpaRepository<Verdura, Long>{

	public List<Verdura> findAllByCategoria(String categoria);
}
