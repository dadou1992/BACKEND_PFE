package com.Talan.gestionUtilisateur.web;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Talan.gestionUtilisateur.dao.UserRepository;
import com.Talan.gestionUtilisateur.entities.BotoolUser;

@RestController

public class UserRestController {
	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@CrossOrigin("*")
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public List<BotoolUser>getusers(){
		return userRepository.findAll();
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public BotoolUser getuser(@PathVariable Long id){
		return userRepository.findOne(id);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/findUsers",method=RequestMethod.GET)
	public Page<BotoolUser>chercher(@RequestParam(name="mc",defaultValue="") String mc, 
			@RequestParam(name="page",defaultValue="0")int page, 
			@RequestParam(name="size",defaultValue="5")int size){
		
			return userRepository.findUser("%"+mc+"%", new PageRequest(page, size));
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public BotoolUser Save(@RequestBody BotoolUser c){
		String hashPW = bCryptPasswordEncoder.encode(c.getPassword());
		c.setPassword(hashPW);
		return userRepository.save(c);
	}

	@CrossOrigin("*")
	@RequestMapping(value="/deleteUser/{id}",method=RequestMethod.DELETE)
	public void supprimer(@PathVariable Long id){
		 userRepository.delete(id);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/editUser/{id}",method=RequestMethod.PUT)
	public BotoolUser Save(@PathVariable Long id, @RequestBody BotoolUser c){
		c.setUserId(id);
		return userRepository.save(c);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/editUserProfil/{id}",method=RequestMethod.PUT)
	public BotoolUser SaveProfil(@PathVariable Long id, @RequestBody BotoolUser c){
		c.setUserId(id);
		return userRepository.save(c);
	}
	

}
