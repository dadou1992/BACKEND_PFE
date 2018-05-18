package com.Talan.gestionUtilisateur.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Talan.gestionUtilisateur.entities.BotoolMenu;

@Repository
public interface BotoolMenuRepository extends JpaRepository<BotoolMenu, Long>{

}
