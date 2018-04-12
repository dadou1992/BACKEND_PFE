package com.Talan.client.services;

import com.Talan.client.model.CustomerInformation;
import com.Talan.client.model.FetchCustomer;

public interface GetCustomerInformationService {
	/**
	 * 
	 * @param value : it can be msisdn, custCode or contractNumber
	 * @return
	 */
	public CustomerInformation getCustomerInformation(String value, FetchCustomer type);

}
