package com.Talan.authentification.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="BOTOOL_USER")
public class AppUser implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private Long id;
	@Column(name="login")
	private String username;
	@Column(name="password")
	private String password;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	@NotNull
	@Email
	@Column(name="email")
	private String email;
	
	@Column(name="matricule")
	private String matricule;

//	
//	@ManyToMany(fetch = FetchType.EAGER)
//	private Collection<AppRole> roles = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="ID_PROFIL",referencedColumnName ="ID_PROFILS" )
	private AppRole roles;



	public AppUser(String username, String password, String firstName, String lastName, String email, String matricule,
			//Collection<AppRole> roles
			AppRole roles) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.matricule = matricule;
		this.roles = roles;
	}
	
	
	
	

	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public Collection<AppRole> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Collection<AppRole> roles) {
//		this.roles = roles;
//	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}


	
	/*****************************************/
	public AppRole getRoles() {
		return roles;
	}

	public void setRoles(AppRole roles) {
		this.roles = roles;
	}
	

}
