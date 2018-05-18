package com.Talan.gestionUtilisateur.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Talan.gestionUtilisateur.entities.*;

@Repository
public interface UserRepository extends JpaRepository<BotoolUser, Long> {
	
	@Query("select c from BotoolUser c where c.firstName like :x")
	public Page<BotoolUser> findUser(@Param("x") String mc,Pageable pageable);
	
	public BotoolUser findByLogin(String login);
	
	
	
	

}
