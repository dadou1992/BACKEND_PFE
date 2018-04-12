package com.Talan.history.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "botoolHistory")
public class History {
	
	@Id
	private String id;
	private String operationName;
	private String operationDate;
	private String userIP;
	private Long userId;
	private String operationDescription;
	private String commentaire;
	private String status;
	
	
	
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public History(String operationName, String operationDate, String userIP, Long userId, String operationDescription,
			String commentaire, String status) {
		super();
		this.operationName = operationName;
		this.operationDate = operationDate;
		this.userIP = userIP;
		this.userId = userId;
		this.operationDescription = operationDescription;
		this.commentaire = commentaire;
		this.status = status;
	}
	public History() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "History [id=" + id + ", operationName=" + operationName + ", operationDate=" + operationDate
				+ ", userIP=" + userIP + ", userId=" + userId + ", operationDescription=" + operationDescription
				+ ", commentaire=" + commentaire + ", status=" + status + "]";
	}

}
