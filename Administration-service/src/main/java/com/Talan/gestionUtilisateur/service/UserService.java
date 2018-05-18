package com.Talan.gestionUtilisateur.service;

import java.util.List;
import com.Talan.gestionUtilisateur.entities.BotoolUser;

public interface UserService {
	
	public List<BotoolUser> findAll();
	public BotoolUser findOne(Long id);
	public BotoolUser save (BotoolUser p);
	public BotoolUser Update (BotoolUser p);
	public void delete(Long id);

}
