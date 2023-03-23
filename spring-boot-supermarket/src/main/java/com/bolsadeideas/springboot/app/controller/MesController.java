package com.bolsadeideas.springboot.app.controller;

import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.springboot.app.models.entity.AnnualPercentages;
import com.bolsadeideas.springboot.app.service.IServiceAnnualPercentages;

@Controller
@RequestMapping("/mes")
public class MesController {
	
	@Autowired
	private IServiceAnnualPercentages serviceAnnualPercentage;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		Long year = Long.valueOf(new GregorianCalendar().get(1));
		AnnualPercentages percentagesCarne = serviceAnnualPercentage.findByYearAndCategoria(year, "carne");
		AnnualPercentages percentagesVerdura = serviceAnnualPercentage.findByYearAndCategoria(year, "verdura");
		
		if (percentagesCarne != null) {
			model.addAttribute("listCarne", percentagesCarne.getMonths());
		}
		if (percentagesVerdura != null) {
			model.addAttribute("listVerdura", percentagesVerdura.getMonths());
		}
		
		return "mes/listar";
	}

	@GetMapping("/addMes")
	public String addMes() {
		return "addMes";
	}

}
