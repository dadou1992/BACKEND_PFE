package com.Talan.gestionUtilisateur.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;



@Entity
public class BotoolUser implements Serializable{
	@Id @GeneratedValue
	private long userId;
	@NotNull
	private String matricule;
	@NotNull
	private String login;
	@NotNull
	private String password;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	@Email
	private String email;
	
	private int isdeleted;
	
	/*@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Profils> roles = new ArrayList<>();*/
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="id_profil",referencedColumnName ="idProfils" )
	private Profils profil;
	
	public BotoolUser(String matricule, String login, String password, String firstName, String lastName,
			String email, Profils profil) {
		super();
		this.matricule = matricule;
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.profil = profil;
		
	}



	public Profils getProfil() {
		return profil;
	}



	public void setProfil(Profils profil) {
		this.profil = profil;
	}



	public BotoolUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
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

	public int getIsdeleted() {
		return isdeleted;
	}



	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}



	@Override
	public String toString() {
		return "BotoolUser [userId=" + userId + ", matricule=" + matricule + ", login=" + login + ", password="
				+ password + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", isdeleted="
				+ isdeleted + ", profil=" + profil + "]";
	}
	


}
