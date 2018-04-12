package com.Talan.gestionUtilisateur.service;

import java.util.List;

import com.Talan.gestionUtilisateur.entities.BotoolPage;

public interface PageService {
	public List<BotoolPage> findAll();
	public BotoolPage findOne(Long id);
	public BotoolPage save (BotoolPage p);
	public void delete(Long id);
	public BotoolPage Update (BotoolPage p);


}
