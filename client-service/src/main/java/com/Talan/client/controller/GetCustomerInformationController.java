package com.Talan.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Talan.client.model.CustomerInformation;
import com.Talan.client.model.FetchCustomer;
import com.Talan.client.services.GetCustomerInformationService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
@RequestMapping("/")
@RefreshScope
@RestController
public class GetCustomerInformationController {
	  private static Logger log = LoggerFactory.getLogger(GetCustomerInformationController.class);

	
	@Autowired
	private GetCustomerInformationService customerInformationService;
	
	@CrossOrigin("*")
	@HystrixCommand(fallbackMethod="clientInfo",commandKey ="clientInformations",
	groupKey="client-service",commandProperties= {
			@HystrixProperty (name="circuitBreaker.sleepWindowInMilliseconds",value="60000"),
			@HystrixProperty (name="circuitBreaker.errorThresholdPercentage",value="5"),

	})
	@RequestMapping(value = "/clientInformations", method = RequestMethod.GET) 
	public CustomerInformation getCustomerInformation(@RequestParam String value, @RequestParam FetchCustomer type)throws Exception {
		log.info("Exécution de la méthode getCustomerInformation ");
		return customerInformationService.getCustomerInformation(value, type);
	}
	
	private CustomerInformation clientInfo(String value,FetchCustomer type) {
		CustomerInformation info = customerInformationService.getCustomerInformation("21625478569", FetchCustomer.Msisdn);
		return info;
		
	}

}
