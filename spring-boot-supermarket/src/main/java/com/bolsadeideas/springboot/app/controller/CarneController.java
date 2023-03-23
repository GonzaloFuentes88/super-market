package com.bolsadeideas.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.app.models.entity.Carne;
import com.bolsadeideas.springboot.app.service.IServiceAlimento;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/carne")
@SessionAttributes("carne")
public class CarneController {
	
	//Servicio para manejar las carnes	
	@Autowired
	@Qualifier(value = "serviceCarne")
	IServiceAlimento<Carne> serviceCarne;
	
	@GetMapping("/addCarne")
	public String addCarne(Model model) {
		model.addAttribute("titulo",superMarketController.TITULO_PAGE);
		Carne carne = new Carne();
		model.addAttribute("carne",carne);
		
		return "addCarne";
	}
	
	@PostMapping("/addCarne")
	public String saveCarne(@Valid Carne carne,BindingResult result,SessionStatus status,Model model) {
		model.addAttribute("titulo",superMarketController.TITULO_PAGE);
		
		if(result.hasErrors()) {
			return "addCarne";
		}
		if(carne !=null) {
			serviceCarne.save(carne);
			status.setComplete();
			return "redirect:/listar";
		}
		return "addCarne";
		
	}
	@GetMapping("/addCarne/{id}")
	public String editartCarne(@PathVariable(value = "id") Long id,Model model) {
		model.addAttribute("titulo",superMarketController.TITULO_PAGE);
		Carne carne = null; 
		if(id > 0) {
			carne = serviceCarne.findOne(id);
			if(carne == null) {
				return "redirect:/listar";
			}
		}
		
		model.addAttribute("carne",carne);
		
		return "addCarne";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarCarne(@PathVariable(value = "id") Long id,Model model) {
		Carne carne = null;
		if (id > 0) {
			carne = serviceCarne.findOne(id);
			if (carne == null) {
				return "redirect:/listar";
			}
		}
		serviceCarne.delete(id);
		model.addAttribute("titulo", superMarketController.TITULO_PAGE);
		
		return "redirect:/listar";
	}

}
