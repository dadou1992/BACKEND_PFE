package com.Talan.gestionUtilisateur.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Profils implements Serializable{
	@Id @GeneratedValue
	private Long idProfils;
	private String profilName;
	private String description;
	
	//@ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name = "Profil_Page", joinColumns = { @JoinColumn(name = "fk_profil") }, inverseJoinColumns = {
			@JoinColumn(name = "fk_page") })
	private List<BotoolPage> pages = new ArrayList<>();	
	
	
	
	
	public Profils() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Profils(Long idProfils, String profilName, String description, List<BotoolUser> users) {
		super();
		this.idProfils = idProfils;
		this.profilName = profilName;
		this.description = description;
	}


	public Long getIdProfils() {
		return idProfils;
	}


	public void setIdProfils(Long idProfils) {
		this.idProfils = idProfils;
	}


	public String getProfilName() {
		return profilName;
	}


	public void setProfilName(String profilName) {
		this.profilName = profilName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}



	public List<BotoolPage> getPages() {
		return pages;
	}



	public void setPages(List<BotoolPage> pages) {
		this.pages = pages;
	}



	@Override
	public String toString() {
		return "Profils [idProfils=" + idProfils + ", profilName=" + profilName + ", description=" + description + "]";
	}
	
	
	
	
	
	

}
