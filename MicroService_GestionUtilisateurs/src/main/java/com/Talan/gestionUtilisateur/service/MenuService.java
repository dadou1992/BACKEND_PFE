package com.Talan.gestionUtilisateur.service;

import java.util.List;
import com.Talan.gestionUtilisateur.entities.BotoolMenu;


public interface MenuService {
	
	public List<BotoolMenu> findAll();
	public BotoolMenu findOne(Long id);
	public BotoolMenu save (BotoolMenu Save);
	public void delete(Long id);

}
