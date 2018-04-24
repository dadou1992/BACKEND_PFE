package com.Talan.gestionUtilisateur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import com.Talan.gestionUtilisateur.dao.*;
import com.Talan.gestionUtilisateur.entities.BotoolMenu;
import com.Talan.gestionUtilisateur.entities.BotoolPage;
import com.Talan.gestionUtilisateur.entities.BotoolUser;
import com.Talan.gestionUtilisateur.entities.Profils;

@CrossOrigin("*")
@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@ComponentScan("com.Talan.gestionUtilisateur")
public class MicroServiceGestionUtilisateursApplication implements CommandLineRunner {
	
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	

	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceGestionUtilisateursApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}

@Configuration
class MyConfig extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(BotoolUser.class);
		config.exposeIdsFor(Profils.class);
		config.exposeIdsFor(BotoolMenu.class);
		config.exposeIdsFor(BotoolPage.class);

	}
}
