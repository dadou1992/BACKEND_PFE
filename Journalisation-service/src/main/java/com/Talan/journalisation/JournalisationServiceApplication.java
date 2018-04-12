package com.Talan.journalisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.Talan.journalisation.dao.JournalisationRepository;
import com.Talan.journalisation.entities.Journalisation;

@RefreshScope
@CrossOrigin("*")
@EnableEurekaClient
@SpringBootApplication
public class JournalisationServiceApplication {
	@Autowired
	private JournalisationRepository journalisationRepository;

	public static void main(String[] args) {
		SpringApplication.run(JournalisationServiceApplication.class, args);
	}
}

@Configuration
class MyConfig extends RepositoryRestConfigurerAdapter{
	
	
	@Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Journalisation.class);
        


    }
}
