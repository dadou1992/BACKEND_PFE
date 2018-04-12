package com.Talan.authentification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import com.Talan.authentification.service.AccountService;

@RefreshScope
@CrossOrigin("*")
@EnableEurekaClient
@SpringBootApplication
@ComponentScan("com.Talan.authentification")
public class AuthentificationServiceApplication implements CommandLineRunner {
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(AuthentificationServiceApplication.class, args);
	}
	

	@Override
	public void run(String... arg0) throws Exception {
		
	}
}
