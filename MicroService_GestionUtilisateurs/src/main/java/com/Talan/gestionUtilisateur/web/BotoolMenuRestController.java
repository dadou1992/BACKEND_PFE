package com.Talan.gestionUtilisateur.web;

import java.util.List;

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

import com.Talan.gestionUtilisateur.dao.BotoolMenuRepository;
import com.Talan.gestionUtilisateur.dao.UserRepository;
import com.Talan.gestionUtilisateur.entities.BotoolMenu;
import com.Talan.gestionUtilisateur.entities.BotoolUser;

@CrossOrigin("*")
@RestController
public class BotoolMenuRestController {

	@Autowired
	private BotoolMenuRepository botoolMenuRepository;

	@CrossOrigin("*")
	@RequestMapping(value = "/menus", method = RequestMethod.GET)
	public List<BotoolMenu> getmenus() {
		return botoolMenuRepository.findAll();
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/menu/{id}", method = RequestMethod.GET)
	public BotoolMenu getmenu(@PathVariable Long id) {
		return botoolMenuRepository.findOne(id);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/addMenu", method = RequestMethod.POST)
	public BotoolMenu Save(@RequestBody BotoolMenu c) {
		return botoolMenuRepository.save(c);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/deleteMenu/{id}", method = RequestMethod.DELETE)
	public void supprimer(@PathVariable Long id) {
		botoolMenuRepository.delete(id);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/editMenu/{id}", method = RequestMethod.PUT)
	public BotoolMenu Save(@PathVariable Long id, @RequestBody BotoolMenu c) {
		c.setMenuId(id);
		return botoolMenuRepository.save(c);
	}

}
