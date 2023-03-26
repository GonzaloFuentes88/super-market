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

import com.bolsadeideas.springboot.app.models.entity.Verdura;
import com.bolsadeideas.springboot.app.service.interfaces.IServiceAlimento;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/verdura")
@SessionAttributes("verdura")
public class VerduraController {

	@Autowired
	@Qualifier(value = "serviceVerdura")
	IServiceAlimento<Verdura> serviceVerdura;
	
	@GetMapping("/addVerdura")
	public String addVerdura(Model model) {
		model.addAttribute("titulo",superMarketController.TITULO_PAGE);
		Verdura verdura = new Verdura();
		model.addAttribute("verdura",verdura);
		
		return "addVerdura";
	}
	@GetMapping("/addVerdura/{id}")
	public String editartVerdura(@PathVariable(value = "id") Long id,Model model) {
		model.addAttribute("titulo",superMarketController.TITULO_PAGE);
		Verdura verdura = null; 
		if(id > 0) {
			verdura = serviceVerdura.findOne(id);
			if(verdura == null) {
				return "redirect:/listar";
			}
		}
		
		model.addAttribute("verdura",verdura);
		
		return "addVerdura";
	}
	
	@PostMapping("/addVerdura")
	public String saveVerdura(@Valid Verdura verdura,BindingResult result,SessionStatus status,Model model) {
		model.addAttribute("titulo",superMarketController.TITULO_PAGE);
		
		if(result.hasErrors()) {
			return "addVerdura";
		}
		if(verdura !=null) {
			serviceVerdura.save(verdura);
			status.setComplete();
			return "redirect:/listar";
		}
		return "addVerdura";
		
	}
	@GetMapping("/eliminar/{id}")
	public String eliminarVerdura(@PathVariable(value = "id") Long id,Model model) {
		Verdura verdura = null;
		if (id > 0) {
			verdura  = serviceVerdura.findOne(id);
			if (verdura  == null) {
				return "redirect:/listar";
			}
		}
		serviceVerdura.delete(id);
		model.addAttribute("titulo", superMarketController.TITULO_PAGE);
		
		return "redirect:/listar";
	}
}
