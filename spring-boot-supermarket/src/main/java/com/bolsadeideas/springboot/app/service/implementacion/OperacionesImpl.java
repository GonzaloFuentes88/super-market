package com.bolsadeideas.springboot.app.service.implementacion;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.models.entity.Carne;
import com.bolsadeideas.springboot.app.models.entity.Verdura;
import com.bolsadeideas.springboot.app.service.interfaces.IOperaciones;
import com.bolsadeideas.springboot.app.service.interfaces.IServiceAlimento;

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
		
		if(totalVerdura!=0) {
			resultados.put("verdura", Double.valueOf((totalVerdura*100)/total));
		}
		if(totalCarne !=0) {
			resultados.put("carne", Double.valueOf((totalCarne*100)/total));
		}
		
		return resultados;
	}
	
	
	@Override
	public Double obtenerAumentoCarnes() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Double obtenerAumentoVerduras() {
		// TODO Auto-generated method stub
		return null;
	}

}
