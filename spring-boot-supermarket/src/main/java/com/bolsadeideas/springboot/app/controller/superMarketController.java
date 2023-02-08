package com.bolsadeideas.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.app.entity.Carne;
import com.bolsadeideas.springboot.app.entity.User;
import com.bolsadeideas.springboot.app.entity.Verdura;
import com.bolsadeideas.springboot.app.service.IServiceAlimento;
import com.bolsadeideas.springboot.app.service.IServiceUser;

@Controller
@SessionAttributes("user")
public class superMarketController {

	private static final String TITULO_PAGE = "SuperMarket";
	
	@Autowired
	private IServiceUser serviceUser;
	
	@Autowired
	@Qualifier(value = "serviceVerdura")
	IServiceAlimento<Verdura> serviceVerdura;
	
	@Autowired
	@Qualifier(value = "serviceCarne")
	IServiceAlimento<Carne> serviceCarne;
	
	@GetMapping({"/login","/"})
	public String login(Model model) {
		model.addAttribute("titulo", TITULO_PAGE);
		User user = new User();
		
		return "login";
	}
	
	@PostMapping("/login")
	public String loginRealizado(String username, String password,Model model) {
		User user = serviceUser.findByUsernameAndPassword(username, password);
		System.out.println(username+" "+password);
		
		if(user !=null) {
			return "redirect:/home";
		}
				
		return "login";
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
