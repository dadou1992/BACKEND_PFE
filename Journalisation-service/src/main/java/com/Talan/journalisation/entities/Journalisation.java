package com.Talan.journalisation.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Journalisation {
	
	@Id @GeneratedValue
	private Long id;
	private String operationName;
	private String operationDate;
	private String userIP;
	private Long userId;
	private String operationDescription;
	private String commentaire;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	
	public String getUserIP() {
		return userIP;
	}
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getOperationDescription() {
		return operationDescription;
	}
	public void setOperationDescription(String operationDescription) {
		this.operationDescription = operationDescription;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
		
	}
	public String getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}
	public Journalisation(Long id, String operationName, String operationDate, String userIP, Long userId,
			String operationDescription, String commentaire) {
		super();
		this.id = id;
		this.operationName = operationName;
		this.operationDate = operationDate;
		this.userIP = userIP;
		this.userId = userId;
		this.operationDescription = operationDescription;
		this.commentaire = commentaire;
	}
	public Journalisation() {
		super();
		// TODO Auto-generated constructor stub
	}
	


}
