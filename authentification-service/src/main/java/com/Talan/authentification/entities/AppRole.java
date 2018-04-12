package com.Talan.authentification.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROFILS")
public class AppRole implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="ID_PROFILS")
	private Long idProfils;
	@Column(name="PROFIL_NAME")
	private String roleName;
	@Column(name="DESCRIPTION")
	private String description;

	public AppRole(String roleName, String desc) {
		super();
		this.roleName = roleName;
		this.description = desc;
	}

	public AppRole() {
		super();

	}

	public Long getIdProfils() {
		return idProfils;
	}

	public void setIdProfils(Long id) {
		this.idProfils = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
