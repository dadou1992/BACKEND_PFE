package com.Talan.gestionUtilisateur.entities;

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
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Entity
public class BotoolPage {

	@Id
	@GeneratedValue
	private Long pageId;
	private String pageName;
	private String url;
	private String Acronyme;
	@Min(0)
	@Max(1)
	private int isAcitvated;
	@Min(0)
	@Max(1)
	private int isManaged;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// @JsonManagedReference
	
	@JoinColumn(name = "MENU_ID")
	private BotoolMenu menu;
	
	
//	, mappedBy = "pages"
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Profil_Page", joinColumns = { @JoinColumn(name = "fk_page") }, inverseJoinColumns = {
			@JoinColumn(name = "fk_profil") })
	private List<Profils> profils = new ArrayList<>();

	public BotoolPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BotoolPage(Long pageId, String pageName, String url, String acronyme, int isAcitvated, int isManaged,
			BotoolMenu menu, List<Profils> profils) {
		super();
		this.pageId = pageId;
		this.pageName = pageName;
		this.url = url;
		Acronyme = acronyme;
		this.isAcitvated = isAcitvated;
		this.isManaged = isManaged;
		this.menu = menu;
		this.profils = profils;
	}

	public BotoolMenu getMenu() {
		return menu;
	}

	public void setMenu(BotoolMenu menu) {
		this.menu = menu;
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAcronyme() {
		return Acronyme;
	}

	public void setAcronyme(String acronyme) {
		Acronyme = acronyme;
	}

	public int getIsAcitvated() {
		return isAcitvated;
	}

	public void setIsAcitvated(int isAcitvated) {
		this.isAcitvated = isAcitvated;
	}

	public int getIsManaged() {
		return isManaged;
	}

	public void setIsManaged(int isManaged) {
		this.isManaged = isManaged;
	}

	public List<Profils> getProfils() {
		return profils;
	}

	public void setProfils(List<Profils> profils) {
		this.profils = profils;
	}

	@Override
	public String toString() {
		return "BotoolPage [pageId=" + pageId + ", pageName=" + pageName + ", url=" + url + ", Acronyme=" + Acronyme
				+ ", isAcitvated=" + isAcitvated + ", isManaged=" + isManaged + "]";
	}

}