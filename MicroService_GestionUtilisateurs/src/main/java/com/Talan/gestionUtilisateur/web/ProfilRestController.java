package com.Talan.gestionUtilisateur.web;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Talan.gestionUtilisateur.dao.ProfilRespository;
import com.Talan.gestionUtilisateur.entities.*;

@RestController
public class ProfilRestController {
	
	@Autowired
	private ProfilRespository profilRepository;
	
	@CrossOrigin("*")
	@RequestMapping(value="/profils",method=RequestMethod.GET)
	public List<Profils>getProfils(){
		return profilRepository.findAll();
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/profil/{id}",method=RequestMethod.GET)
	public Profils getProfils(@PathVariable Long id){
		return profilRepository.findOne(id);
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
		return profilRepository.save(p);
	}

	@CrossOrigin("*")
	@RequestMapping(value="/deleteProfil/{id}",method=RequestMethod.DELETE)
	public void supprimer(@PathVariable Long id){
		
		
		
		profilRepository.delete(id);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/editProfil/{id}",method=RequestMethod.PUT)
	public Profils Save(@PathVariable Long id, @RequestBody Profils p){
		p.getIdProfils();
		return profilRepository.save(p);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/ProfilName",method=RequestMethod.GET)
	public List<String> ProfilName() {
		return profilRepository.findByProfilName();
		
	}
	


}
