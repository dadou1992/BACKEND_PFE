package com.Talan.gestionUtilisateur.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Talan.gestionUtilisateur.dao.BotoolMenuRepository;
import com.Talan.gestionUtilisateur.entities.BotoolMenu;

@Service
public class MenuServiceImp implements MenuService{

	@Autowired
	private BotoolMenuRepository botoolMenuRepository;

	@Override
	public List<BotoolMenu> findAll() {
		return botoolMenuRepository.findAll();
	}

	@Override
	public BotoolMenu findOne(Long id) {
		return botoolMenuRepository.findOne(id);
	}

	@Override
	public BotoolMenu save(BotoolMenu c) {
		return botoolMenuRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		botoolMenuRepository.delete(id);
		
	}


}
