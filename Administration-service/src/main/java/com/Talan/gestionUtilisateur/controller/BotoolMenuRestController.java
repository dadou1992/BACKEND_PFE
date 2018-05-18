package com.Talan.gestionUtilisateur.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Talan.gestionUtilisateur.entities.*;
import com.Talan.gestionUtilisateur.service.MenuService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@CrossOrigin("*")
@RestController
public class BotoolMenuRestController {
	    
	private static Logger log = LoggerFactory.getLogger(BotoolMenuRestController.class);

	@Autowired
	private MenuService menuService;

	@CrossOrigin("*")
	@HystrixCommand(fallbackMethod = "defaultMenus", commandKey = "menusDetails", groupKey = "user-service", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "5"),

	})
	@RequestMapping(value = "/menus", method = RequestMethod.GET)
	public List<BotoolMenu> getmenus() {
		log.info("Exécution de la méthode getmenus ");

		return menuService.findAll();
	}

	@CrossOrigin("*")
	@HystrixCommand(fallbackMethod = "defaultmenu", commandKey = "menuDetails", groupKey = "user-service", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "5"),

	})
	@RequestMapping(value = "/menu/{id}", method = RequestMethod.GET)
	public BotoolMenu getmenu(@PathVariable Long id) {
		log.info("Exécution de la méthode getmenu ");

		return menuService.findOne(id);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/addMenu", method = RequestMethod.POST)
	public BotoolMenu Save(@RequestBody BotoolMenu c) {
		log.info("Exécution de la méthode Savemenu ");

		return menuService.save(c);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/deleteMenu/{id}", method = RequestMethod.DELETE)
	public void supprimer(@PathVariable Long id) {
		log.info("Exécution de la méthode Deletemenu ");

		menuService.delete(id);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/editMenu/{id}", method = RequestMethod.PUT)
	public BotoolMenu Save(@PathVariable Long id, @RequestBody BotoolMenu c) {
		c.setMenuId(id);
		log.info("Exécution de la méthode editMenu ");

		return menuService.save(c);
	}

	private BotoolMenu defaultMenu(Long id) {
		BotoolMenu menu = new BotoolMenu(id, "dashboard", null);
		return menu;

	}

	public List<BotoolMenu> defaultMenus() {
		BotoolMenu m1 = new BotoolMenu((long) 47932, "dashboard", null);
		BotoolMenu m2 = new BotoolMenu((long) 1253, "Gestion Utilisateur", null);

		Collection liste = new LinkedList<>();
		liste.add(m1);
		liste.add(m2);
		return (List<BotoolMenu>) liste;

	}

}
