package com.Talan.gestionUtilisateur.controller;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.PreRemove;

import org.springframework.data.domain.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Talan.gestionUtilisateur.dao.ProfilRespository;
import com.Talan.gestionUtilisateur.entities.*;
import com.Talan.gestionUtilisateur.service.ProfilService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RestController
@RefreshScope
public class ProfilRestController {
	  private static Logger log = LoggerFactory.getLogger(ProfilRestController.class);


	@Autowired
	private ProfilRespository profilRepository;
	
	@Autowired
	private ProfilService profilService;
	
	@CrossOrigin("*")
	@RequestMapping(value="/findProfil",method=RequestMethod.GET)
	public Page<Profils> findProfil(
			@RequestParam (name ="mc",defaultValue="")String mc,
			@RequestParam (name ="page",defaultValue= "0")int page,
			@RequestParam (name ="size",defaultValue= "5")int size){
		return profilRepository.findProfil("%"+mc+"%", new PageRequest(page, size));
		
	}
	
	
	@CrossOrigin("*")
	@HystrixCommand(fallbackMethod="defaultProfils",commandKey ="profilsDetails",
	groupKey="user-service",commandProperties= {
			@HystrixProperty (name="circuitBreaker.sleepWindowInMilliseconds",value="60000"),
			@HystrixProperty (name="circuitBreaker.errorThresholdPercentage",value="5"),

	})
	@RequestMapping(value="/profils",method=RequestMethod.GET)
	public List<Profils>getProfils(){
		log.info("Exécution de la méthode getProfils ");

		return profilService.findAll();
	}
	
	
	@HystrixCommand(fallbackMethod="defaultProfil",commandKey ="profilDetails",
			groupKey="user-service",commandProperties= {
					@HystrixProperty (name="circuitBreaker.sleepWindowInMilliseconds",value="60000"),
					@HystrixProperty (name="circuitBreaker.errorThresholdPercentage",value="5"),

	})
	@CrossOrigin("*")
	@RequestMapping(value="/profil/{id}",method=RequestMethod.GET)
	public Profils getProfil(@PathVariable Long id){
		log.info("Exécution de la méthode getProfil ");

		return profilService.findOne(id);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/findProfils",method=RequestMethod.GET)
	public Page<Profils>FindProfil(@RequestParam(name="mc",defaultValue="") String mc, 
			@RequestParam(name="page",defaultValue="0")int page, 
			@RequestParam(name="size",defaultValue="5")int size){
		
			return profilRepository.findProfil("%"+mc+"%", new PageRequest(page, size));
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/addProfil",method=RequestMethod.POST)
	public Profils Save(@RequestBody Profils p){
		log.info("Exécution de la méthode saveProfil ");

			return profilService.save(p);
	}
	

	@CrossOrigin("*")
	@RequestMapping(value="/deleteProfil/{id}",method=RequestMethod.DELETE)
	@PreRemove
	public void deleteProfil(@PathVariable Long id){	
		log.info("Exécution de la méthode deleteProfil ");
		profilService.delete(id);
	}
	
	
	
	@CrossOrigin("*")
	@RequestMapping(value="/editProfil/{id}",method=RequestMethod.PUT)
	public Profils Save(@PathVariable Long id, @RequestBody Profils p){
		log.info("Exécution de la méthode editProfil ");

		p.getIdProfils();
		return profilService.Update(p);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/ProfilName",method=RequestMethod.GET)
	public List<String> ProfilName() {
		return profilRepository.findByProfilName();
		
	}
	
	
	/*************************************/
	
	private Profils defaultProfil(Long id) {
		Profils p = new Profils("test", null);
		return p;
	}
	
	public List<Profils>defaultProfils(){
		Profils p = new Profils("test", null);
		Profils p1 = new Profils("test2", null);

		Collection liste = new LinkedList<>();
		liste.add(p);
		liste.add(p1);
		return (List<Profils>) liste;
		
	}

}
