package com.talan.client.services;

import com.talan.client.model.CustomerInformation;
import com.talan.client.model.FetchCustomer;

public interface GetCustomerInformationService {
	/**
	 * 
	 * @param value : it can be msisdn, custCode or contractNumber
	 * @return
	 */
	public CustomerInformation getCustomerInformation(String value, FetchCustomer type);

}
