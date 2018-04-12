package com.talan.client.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talan.client.model.CustomerInformation;
import com.talan.client.model.FetchCustomer;
import com.talan.client.services.GetCustomerInformationService;


@RestController
public class GetCustomerInformationController {
	
	@Autowired
	private GetCustomerInformationService customerInformationService;
	
	@CrossOrigin("*")
	@RequestMapping( value = "/clientInformations")
	public CustomerInformation getCustomerInformation(@RequestParam String value, @RequestParam FetchCustomer type) {
		return customerInformationService.getCustomerInformation(value, type);
	}

}
