package com.Talan.gestionUtilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;

public class ClientAppConfiguration {
	@Autowired
	IClientConfig ribbonClientConfig;
	
	@Bean
	public IPing ribbonPing(IClientConfig config) {
		return new PingUrl();
	}
	
	@Bean
	public IRule ribbonRule(IClientConfig config) {
		return new AvailabilityFilteringRule();
		
	}
 
}
