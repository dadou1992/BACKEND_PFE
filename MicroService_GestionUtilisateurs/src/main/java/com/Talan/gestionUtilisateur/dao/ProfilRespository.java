package com.Talan.gestionUtilisateur.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Talan.gestionUtilisateur.entities.*;

public interface ProfilRespository  extends JpaRepository<Profils, Long>{
	
	@Query("select p from Profils p where p.profilName like :x")
	public Page<Profils> findProfil(@Param("x") String mc,Pageable pageable);

	@Query("select profilName from Profils")
	public List<String> findByProfilName();

}
