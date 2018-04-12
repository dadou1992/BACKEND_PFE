package com.Talan.gestionUtilisateur.service;

import java.net.UnknownHostException;
import java.util.List;
import com.Talan.gestionUtilisateur.entities.Profils;

public interface ProfilService {
	
	public List<Profils> findAll();
	public Profils findOne(Long id);
	public Profils save (Profils p);
	public Profils Update (Profils p);
	public void delete(Long id);

}
