package com.Talan.gestionUtilisateur.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class BotoolMenu {
	
	@Id @GeneratedValue
	private Long menuId;
	
	@NotNull
	private String MenuName;
	
	@JsonBackReference
	//@JsonBackReference
	@OneToMany
	List<BotoolPage> pages = new ArrayList<>() ;

	
	
	public Long getMenuId() {
		return menuId;
	}


	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}


	public String getMenuName() {
		return MenuName;
	}


	public void setMenuName(String menuName) {
		MenuName = menuName;
	}


	public BotoolMenu() {
		super();
		// TODO Auto-generated constructor stub
	}



	public BotoolMenu(Long menuId, String menuName, List<BotoolPage> pages) {
		super();
		this.menuId = menuId;
		MenuName = menuName;
		this.pages = pages;
	}


	public List<BotoolPage> getPages() {
		return pages;
	}


	public void setPages(List<BotoolPage> pages) {
		this.pages = pages;
	}


	@Override
	public String toString() {
		return "BotoolMenu [menuId=" + menuId + ", MenuName=" + MenuName + "]";
	}
	
	
	
	

}
