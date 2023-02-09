package com.bolsadeideas.springboot.app.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.entity.Carne;
import com.bolsadeideas.springboot.app.entity.Verdura;

@Service
public class OperacionesImpl implements IOperaciones{

	@Autowired
	@Qualifier(value = "serviceVerdura")
	IServiceAlimento<Verdura> serviceVerdura;
	
	@Autowired
	@Qualifier(value = "serviceCarne")
	IServiceAlimento<Carne> serviceCarne;
	
	@Override
	public Map<String, Double> obtenerCantidad() {
		Map<String,Double> resultados = new HashMap<>();
		Integer totalVerdura = serviceVerdura.findAll().size();
		Integer totalCarne = serviceCarne.findAll().size();
		Integer total = totalVerdura+totalCarne; 
		
		resultados.put("verdura", Double.valueOf((totalVerdura*100)/total));
		resultados.put("carne", Double.valueOf((totalCarne*100)/total));
		
		return resultados;
	}

}
