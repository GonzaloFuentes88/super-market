package com.bolsadeideas.springboot.app.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.app.models.entity.AnnualPercentages;
import com.bolsadeideas.springboot.app.models.entity.Mes;
import com.bolsadeideas.springboot.app.service.interfaces.IServiceAnnualPercentages;
import com.bolsadeideas.springboot.app.service.interfaces.IServiceMes;

@Controller
@RequestMapping("/mes")
@SessionAttributes("mes")
public class MesController {

	@Autowired
	private IServiceAnnualPercentages serviceAnnualPercentage;
	
	@Autowired 
	private IServiceMes serviceMes;

	@GetMapping("/listar")
	public String listar(Model model) {
		Long year = Long.valueOf(new GregorianCalendar().get(1));
		AnnualPercentages annualPercentages = serviceAnnualPercentage.findByYear(year);

		model.addAttribute("mesesList", annualPercentages.getMeses());

		return "mes/listar";
	}

	@GetMapping("/addMes")
	public String addMes(Model model) {
		Mes mes = new Mes();
		model.addAttribute("mes", mes);
		return "mes/addMes";
	}
	

	@PostMapping("/addMes")
	public String saveMes(Mes mes, SessionStatus status) {
		AnnualPercentages anP = serviceAnnualPercentage.findByYear(superMarketController.CURRENT_YEAR);
		if (mes == null) {
			return "redirect:/mes/listar";
		}
		if (anP.addMes(mes)) {
			serviceAnnualPercentage.save(anP);

			status.setComplete();

			return "redirect:/mes/listar";
		}

		return "redirect:/mes/addMes";
	}

	@GetMapping("/deleteMes/{id}")
	public String deleteMes(@PathVariable Long id) {
		AnnualPercentages anP = serviceAnnualPercentage.findByYear(superMarketController.CURRENT_YEAR);

		if (id > 0) {
			if (anP.deleteMes(id)) {
				serviceMes.delete(id);
			}
		}
		return "/mes/listar";
	}

	@ModelAttribute(name = "categorias")
	public List<String> categoria() {
		return Arrays.asList("Carne", "Verdura");
	}

	@ModelAttribute(name = "meses")
	public List<String> meses() {
		Calendar calendar = Calendar.getInstance();
		Integer actual = calendar.get(Calendar.MONTH);
		List<String> meses = Arrays.asList(
				"Enero", 
				"Febrero", 
				"Marzo", 
				"Abril", 
				"Mayo", 
				"Junio", 
				"Julio", 
				"Agosto", 
				"Septiembre",
				"Noviembre", 
				"Diciembre"); 
		
		
		return meses.subList(0, actual);
	}

}
