package com.bolsadeideas.springboot.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface IOperaciones {
	
	public Map<String,Double> obtenerCantidad();
	
	public List<Integer> obtenerAumentoCarnes();
	public List<Integer> obtenerAumentoVerduras();

}
