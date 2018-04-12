package com.Talan.journalisation.web;

import java.util.List;

import javax.persistence.JoinColumn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Talan.journalisation.dao.JournalisationRepository;
import com.Talan.journalisation.entities.Journalisation;


@RestController
public class JournalisationRestController {
	
	@Autowired
	private JournalisationRepository journalisationRepository;
	
	@CrossOrigin("*")
	@RequestMapping(value="/journals",method=RequestMethod.GET)
	public List<Journalisation>getjournlals(){
		return journalisationRepository.findAll();
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/journal/{id}",method=RequestMethod.GET)
	public Journalisation getjournal(@PathVariable Long id){
		return journalisationRepository.findOne(id);
	}
	
	
	
	@CrossOrigin("*")
	@RequestMapping(value="/addOperation",method=RequestMethod.POST)
	public Journalisation Save(@RequestBody Journalisation c){
		return journalisationRepository.save(c);
	}

	@CrossOrigin("*")
	@RequestMapping(value="/deleteOperation/{id}",method=RequestMethod.DELETE)
	public void supprimer(@PathVariable Long id){
		journalisationRepository.delete(id);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/editOperation/{id}",method=RequestMethod.PUT)
	public Journalisation Save(@PathVariable Long id, @RequestBody Journalisation c){
		c.getId();
		return journalisationRepository.save(c);
	}
	



}
