package com.Talan.gestionUtilisateur.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private int isdeleted;
	
	@JoinTable(name = "Profil_Page", joinColumns = { @JoinColumn(name = "id_profil", referencedColumnName = "idProfils") }, inverseJoinColumns = {
			@JoinColumn(name = "id_page",referencedColumnName = "pageId") })
	@ManyToMany(cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
	private List<BotoolPage> pages = new ArrayList<BotoolPage>();	
	
	
	public Profils() {
		super();
		// TODO Auto-generated constructor stub
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



	public int getIsdeleted() {
		return isdeleted;
	}



	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}



	public Profils(String profilName, String description, int isdeleted) {
		super();
		this.profilName = profilName;
		this.description = description;
		this.isdeleted = isdeleted;
	}



	@Override
	public String toString() {
		return "Profils [idProfils=" + idProfils + ", profilName=" + profilName + ", description=" + description
				+ ", isdeleted=" + isdeleted + "]";
	}

}
