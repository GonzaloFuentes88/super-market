package com.bolsadeideas.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bolsadeideas.springboot.app.entity.Carne;
import com.bolsadeideas.springboot.app.entity.User;
import com.bolsadeideas.springboot.app.entity.Verdura;
import com.bolsadeideas.springboot.app.service.IServiceAlimento;
import com.bolsadeideas.springboot.app.service.IServiceUser;

@Controller
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
	public String home(Model model) {
		model.addAttribute("verduras", serviceVerdura.findAll());
		model.addAttribute("carnes", serviceCarne.findAll());
		
		return "home";
	}
	
	
}
