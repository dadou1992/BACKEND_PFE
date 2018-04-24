package com.Talan.gestionUtilisateur.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Talan.gestionUtilisateur.entities.BotoolPage;

@Repository
public interface BotoolPageRepository extends JpaRepository<BotoolPage, Long>{


}
