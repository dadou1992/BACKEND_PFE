package com.Talan.history;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;




@CrossOrigin("*")
@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@ComponentScan("com.Talan.history")
@EnableTurbine
public class HistoryServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HistoryServiceApplication.class, args);
	}
	    
}

/*@Configuration
class MyConfig extends RepositoryRestConfigurerAdapter{
	
	
	@Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(History.class);
        

}
    }*/

