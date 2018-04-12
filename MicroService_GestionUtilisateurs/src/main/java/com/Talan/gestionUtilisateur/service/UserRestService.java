package com.Talan.gestionUtilisateur.service;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RefreshScope
@RestController
public class UserRestService {

	@Value("${me}")
	private String message;

	@RequestMapping("/messages")
	public String tellMe() {
		System.out.println("********************************");
		System.out.println("it's me");
		System.out.println("********************************");

		return message;
	}

}