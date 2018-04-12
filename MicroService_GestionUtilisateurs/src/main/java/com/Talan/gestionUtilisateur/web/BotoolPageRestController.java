package com.Talan.gestionUtilisateur.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Talan.gestionUtilisateur.dao.BotoolPageRepository;
import com.Talan.gestionUtilisateur.entities.BotoolMenu;
import com.Talan.gestionUtilisateur.entities.BotoolPage;

@RestController
public class BotoolPageRestController {
	
	@Autowired
	private BotoolPageRepository botoolPageRepository;
	
	@CrossOrigin("*")
	@RequestMapping(value="/pages",method=RequestMethod.GET)
	public List<BotoolPage>getmenus(){
		return botoolPageRepository.findAll();
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/page/{id}",method=RequestMethod.GET)
	public BotoolPage getpage(@PathVariable Long id){
		return botoolPageRepository.findOne(id);
	}
	
	
	
	@CrossOrigin("*")
	@RequestMapping(value="/addPage",method=RequestMethod.POST)
	public BotoolPage Save(@RequestBody BotoolPage c){
		return botoolPageRepository.save(c);
	}

	@CrossOrigin("*")
	@RequestMapping(value="/deletePage/{id}",method=RequestMethod.DELETE)
	public void supprimer(@PathVariable Long id){
		botoolPageRepository.delete(id);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/editPage/{id}",method=RequestMethod.PUT)
	public BotoolPage Save(@PathVariable Long id, @RequestBody BotoolPage c){
		c.setPageId(id);
		return botoolPageRepository.save(c);
	}

}
