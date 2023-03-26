package com.bolsadeideas.springboot.app.controller;

import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.app.models.entity.AnnualPercentages;
import com.bolsadeideas.springboot.app.models.entity.Carne;
import com.bolsadeideas.springboot.app.models.entity.User;
import com.bolsadeideas.springboot.app.models.entity.Verdura;
import com.bolsadeideas.springboot.app.service.interfaces.IOperaciones;
import com.bolsadeideas.springboot.app.service.interfaces.IServiceAlimento;
import com.bolsadeideas.springboot.app.service.interfaces.IServiceAnnualPercentages;
import com.bolsadeideas.springboot.app.service.interfaces.IServiceUser;

import jakarta.validation.Valid;

/*
 * Controler para manejar la pagina
 * */
@Controller
@SessionAttributes("user")
public class superMarketController {

	
	/* COMENTARIO COSAS A AGREGAR
	 * HACER EL MAPEO DE CATEGORIA, NOMBRE Y VALOR AL OBJETO MES ++
	 * GUARDAR DICHO OBJETO EN LA DB ++
	 * QUE SE LISTEN ++
	 * QUE SE PUEDAN ELIMINAR Y ACTUALIZAR ++
	 * PASAR DICHOS VALORES AL GRAFICO ++
	 * VER COMO SE ALMACENAN LOS PAISES EN EL PROYECTO FORM Y REPLICARLO ++
	 * FINALIZADO ESTO APLICAR VALIDADORES A TODAS LAS CLASES
	 * AGREGAR MENSAJES FLASH
	 * INVESTIGAR E IMPLEMENTAR SPRING SECURITY PARA EL LOGIN Y ENCRIPTAR CONTRASEÑAS
	 * AGREGAR LA OPCION DE RESTAURAR CONTRASEÑA Y LA DE REGISTRAR UN USUARIO
	 * PARA REGISTRAR UN USUARIO SE NECESITA UN USUARIO ADMINISTRADOR
	 * */
	
	
	
	// Titulo de la pagina
	protected static final String TITULO_PAGE = "SuperMarket";
	
	protected static final Long CURRENT_YEAR = Long.valueOf(new GregorianCalendar().get(1));

	// Servicio para manejar los usuarios
	@Autowired
	private IServiceUser serviceUser;

	// Servicio para manejar el porcentaje del aumento mensual de los alimentos
	@Autowired
	private IServiceAnnualPercentages serviceAnnualPercentage;

	// Servicio para manejar las verduras
	@Autowired
	@Qualifier(value = "serviceVerdura")
	IServiceAlimento<Verdura> serviceVerdura;

	// Servicio para manejar las carnes
	@Autowired
	@Qualifier(value = "serviceCarne")
	IServiceAlimento<Carne> serviceCarne;

	@Autowired
	IOperaciones operaciones;

	@GetMapping({ "/login", "/" })
	public String login(Model model) {
		User user = new User();
		model.addAttribute("titulo", TITULO_PAGE);
		model.addAttribute("user", user);

		return "login";
	}

	@PostMapping("/login")
	public String loginRealizado(@Valid User user, BindingResult result, Model model) {

		if (result.hasFieldErrors("username") || result.hasFieldErrors("password")) {
			return "login";
		} else {
			user = serviceUser.findByUsernameAndPassword(user.getUsername(), user.getPassword());
			if (user == null) {
				return "login";
			}
		}

		model.addAttribute("titulo", TITULO_PAGE);
		model.addAttribute("user", user);
		
		if(serviceAnnualPercentage.findByYear(CURRENT_YEAR) == null) {
			AnnualPercentages current = new AnnualPercentages();
			current.setYear(CURRENT_YEAR);
			serviceAnnualPercentage.save(current);
		}
		return "redirect:/home";
	}

	@GetMapping("/home")
	private String home(User user, Model model) {
		model.addAttribute("titulo", TITULO_PAGE);

		return "home";
	}

	@GetMapping("/salir")
	public String salir(User user, SessionStatus status, Model model) {

		status.setComplete();
		return "redirect:/login";
	}

	@GetMapping("/estadisticas")
	public String estadisticas(Model model) {
		Long year = Long.valueOf(new GregorianCalendar().get(1));
		AnnualPercentages percentages = serviceAnnualPercentage.findByYear(year);
		Integer cantCarne = serviceCarne.findAll().size();
		Integer cantVerdura = serviceVerdura.findAll().size();
		model.addAttribute("titulo", TITULO_PAGE);
		model.addAllAttributes(operaciones.obtenerCantidad());
		model.addAttribute("cantCarne", cantCarne);
		model.addAttribute("cantVerdura", cantVerdura);
		model.addAttribute("cantAlimento", (cantCarne + cantVerdura));

		if (percentages != null) {
			model.addAttribute("aumentoCarne", percentages.getMesesCarne());
			model.addAttribute("aumentoVerdura", percentages.getMesesVerdura());
		}

		return "estadisticas";
	}

	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("verduras", serviceVerdura.findAll());
		model.addAttribute("carnes", serviceCarne.findAll());
		model.addAttribute("titulo", TITULO_PAGE);

		return "listar";
	}



}
