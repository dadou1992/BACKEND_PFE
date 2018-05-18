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
import com.Talan.gestionUtilisateur.entities.BotoolPage;
import com.Talan.gestionUtilisateur.service.PageService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class BotoolPageRestController {
	private static Logger log = LoggerFactory.getLogger(BotoolMenuRestController.class);

	@Autowired
	private PageService pageService;

	@CrossOrigin("*")
	@HystrixCommand(fallbackMethod = "defaultPages", commandKey = "pagesDetails", groupKey = "user-service", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "5"),

	})
	@RequestMapping(value = "/pages", method = RequestMethod.GET)
	public List<BotoolPage> getPages() {
		log.info("Exécution de la méthode getPages ");

		return pageService.findAll();
	}

	@CrossOrigin("*")
	@HystrixCommand(fallbackMethod = "defaultPage", commandKey = "pageDetails", groupKey = "user-service", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "5"),

	})
	@RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
	public BotoolPage getPage(@PathVariable Long id) {
		log.info("Exécution de la méthode getPage ");

		return pageService.findOne(id);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/addPage", method = RequestMethod.POST)
	public BotoolPage Save(@RequestBody BotoolPage c) {
		return pageService.save(c);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/deletePage/{id}", method = RequestMethod.DELETE)
	public void supprimer(@PathVariable Long id) {
		log.info("Exécution de la méthode deletePage ");
		pageService.delete(id);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/editPage/{id}", method = RequestMethod.PUT)

	public BotoolPage Save(@PathVariable Long id, @RequestBody BotoolPage c) {
		c.setPageId(id);
		log.info("Exécution de la méthode editPage ");

		return pageService.Update(c);
	}

	/*******************************************************/
	private BotoolPage defaultPage(Long id) {
		BotoolPage page = new BotoolPage(id, "dashboard", null, null, 1, 1, null);
		return page;

	}

	public List<BotoolPage> defaultPages() {
		BotoolPage p = new BotoolPage((long) 47932, "dashboard", null, null, 1, 1, null );
		BotoolPage p2 = new BotoolPage((long) 1253, "home", null, null, 1, 1, null );

		Collection liste = new LinkedList<>();
		liste.add(p);
		liste.add(p2);
		return (List<BotoolPage>) liste;

	}

}
