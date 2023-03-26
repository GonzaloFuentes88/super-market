package com.bolsadeideas.springboot.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolsadeideas.springboot.app.models.entity.Mes;

public interface IMesDao extends JpaRepository<Mes,Long>{

}
