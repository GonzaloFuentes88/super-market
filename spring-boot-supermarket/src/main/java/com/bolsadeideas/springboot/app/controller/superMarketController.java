package com.bolsadeideas.springboot.app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.app.entity.Carne;
import com.bolsadeideas.springboot.app.entity.User;
import com.bolsadeideas.springboot.app.entity.Verdura;
import com.bolsadeideas.springboot.app.service.IOperaciones;
import com.bolsadeideas.springboot.app.service.IServiceAlimento;
import com.bolsadeideas.springboot.app.service.IServiceAnnualPercentages;
import com.bolsadeideas.springboot.app.service.IServiceUser;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@Controller
@SessionAttributes("user")
public class superMarketController {

	private static final String TITULO_PAGE = "SuperMarket";
	
	@Autowired
	private IServiceUser serviceUser;
	
	@Autowired
	private IServiceAnnualPercentages serviceAnnualPercentage;
	
	@Autowired
	@Qualifier(value = "serviceVerdura")
	IServiceAlimento<Verdura> serviceVerdura;
	
	@Autowired
	@Qualifier(value = "serviceCarne")
	IServiceAlimento<Carne> serviceCarne;
	
	@Autowired
	IOperaciones operaciones;
	
	@GetMapping({"/login","/"})
	public String login(Model model) {
		User user = new User();
		model.addAttribute("titulo", TITULO_PAGE);
		model.addAttribute("user",user);
		
		return "login";
	}
	
	@PostMapping("/login")
	public String loginRealizado(@Valid User user,BindingResult result,Model model) {
		
		if(result.hasFieldErrors("username") || result.hasFieldErrors("password")) {
			return "login";
		}else {
			user = serviceUser.findByUsernameAndPassword(user.getUsername(), user.getPassword());
			if(user == null) {
				return "login";
			}
		}
		
		model.addAttribute("titulo", TITULO_PAGE);
		model.addAttribute("user",user);
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	private String home(User user,Model model) {
		model.addAttribute("titulo",TITULO_PAGE);
		
		return "home";
	}
	
	@GetMapping("/salir")
	public String salir(User user,SessionStatus status,Model model) {
		
		status.setComplete();
		return "redirect:/login";
	}
	
	@GetMapping("/estadisticas")
	public String estadisticas(Model model) {
		model.addAttribute("titulo",TITULO_PAGE);
		model.addAllAttributes(operaciones.obtenerCantidad());
		model.addAttribute("aumentoCarne",serviceAnnualPercentage.findByYearAndCategoria(2022L,"carne").getMonths());
		model.addAttribute("aumentoVerdura",serviceAnnualPercentage.findByYearAndCategoria(2022L,"verdura").getMonths());
		
		return "estadisticas";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("verduras", serviceVerdura.findAll());
		model.addAttribute("carnes", serviceCarne.findAll());
		model.addAttribute("titulo",TITULO_PAGE);
		
		return "listar";
	}
	
	
}
