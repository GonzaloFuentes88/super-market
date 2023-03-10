package com.bolsadeideas.springboot.app.controller;

import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.app.entity.AnnualPercentages;
import com.bolsadeideas.springboot.app.entity.Carne;
import com.bolsadeideas.springboot.app.entity.User;
import com.bolsadeideas.springboot.app.entity.Verdura;
import com.bolsadeideas.springboot.app.service.IOperaciones;
import com.bolsadeideas.springboot.app.service.IServiceAlimento;
import com.bolsadeideas.springboot.app.service.IServiceAnnualPercentages;
import com.bolsadeideas.springboot.app.service.IServiceUser;

import jakarta.validation.Valid;

/*
 * Controler para manejar la pagina
 * */
@Controller
@SessionAttributes("user")
public class superMarketController {

	//Titulo de la pagina
	private static final String TITULO_PAGE = "SuperMarket";
	
	
	//Servicio para manejar los usuarios
	@Autowired
	private IServiceUser serviceUser;
	
	//Servicio para manejar el porcentaje del aumento mensual de los alimentos
	@Autowired
	private IServiceAnnualPercentages serviceAnnualPercentage;
	
	//Servicio para manejar las verduras
	@Autowired
	@Qualifier(value = "serviceVerdura")
	IServiceAlimento<Verdura> serviceVerdura;
	
	//Servicio para manejar las carnes	
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
		Long year = Long.valueOf(new GregorianCalendar().get(1));
		AnnualPercentages percentagesCarne = serviceAnnualPercentage.findByYearAndCategoria(year, "carne");
		AnnualPercentages percentagesVerdura = serviceAnnualPercentage.findByYearAndCategoria(year, "verdura");
		Integer cantCarne = serviceCarne.findAll().size();
		Integer cantVerdura = serviceVerdura.findAll().size();
		model.addAttribute("titulo",TITULO_PAGE);
		model.addAllAttributes(operaciones.obtenerCantidad());
		model.addAttribute("cantCarne", cantCarne);
		model.addAttribute("cantVerdura",cantVerdura);
		model.addAttribute("cantAlimento", (cantCarne+cantVerdura));
		
		if(percentagesCarne !=null) {
			model.addAttribute("aumentoCarne",percentagesCarne.getMonths());
		}
		if(percentagesVerdura !=null) {
			model.addAttribute("aumentoVerdura",percentagesVerdura.getMonths());
		}
		
		return "estadisticas";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("verduras", serviceVerdura.findAll());
		model.addAttribute("carnes", serviceCarne.findAll());
		model.addAttribute("titulo",TITULO_PAGE);
		
		return "listar";
	}
	@GetMapping("/addCarne")
	public String addCarne(Model model) {
		model.addAttribute("titulo",TITULO_PAGE);
		Carne carne = new Carne();
		model.addAttribute("carne",carne);
		
		return "addCarne";
	}
	@PostMapping("/addCarne")
	public String addCarnePost(@Valid Carne carne,BindingResult result, Model model) {
		model.addAttribute("titulo",TITULO_PAGE);
		
		if(result.hasErrors()) {
			return "addCarne";
		}
		serviceCarne.save(carne);
		return "redirect:/listar";
	}
	
	
	
	@GetMapping("/addVerdura")
	public String addVerdura(Model model) {
		model.addAttribute("titulo",TITULO_PAGE);
		Verdura verdura = new Verdura();
		model.addAttribute("verdura",verdura);
		
		return "addVerdura";
	}
	@PostMapping("/addVerdura")
	public String addVerduraPost(@Valid Verdura verdura,BindingResult result,Model model) {
		model.addAttribute("titulo",TITULO_PAGE);
		
		if(result.hasErrors()) {
			return "addVerdura";
		}
		serviceVerdura.save(verdura);
		return "redirect:/listar";
	}
	
	@GetMapping("/carne/eliminar/{id}")
	public String eliminarCarne(@PathVariable(value = "id") Long id,Model model) {
		Carne carne = null;
		if (id > 0) {
			carne = serviceCarne.findOne(id);
			if (carne == null) {
				return "redirect:/listar";
			}
		}
		serviceCarne.delete(id);
		model.addAttribute("titulo", TITULO_PAGE);
		
		return "redirect:/listar";
	}
	@GetMapping("/verdura/eliminar/{id}")
	public String eliminarVerdura(@PathVariable(value = "id") Long id,Model model) {
		Verdura verdura = null;
		if (id > 0) {
			verdura  = serviceVerdura.findOne(id);
			if (verdura  == null) {
				return "redirect:/listar";
			}
		}
		serviceVerdura.delete(id);
		model.addAttribute("titulo", TITULO_PAGE);
		
		return "redirect:/listar";
	}
	
	@GetMapping("/editarMes")
	public String editarMes() {
		return "editarMes";
	}
	
	@GetMapping("/addMes")
	public String addMes() {
		return "addMes";
	}
	
}
