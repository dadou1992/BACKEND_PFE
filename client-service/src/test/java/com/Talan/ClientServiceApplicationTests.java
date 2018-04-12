package com.Talan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.Talan.client.model.CustomerInformation;
import com.Talan.client.model.FetchCustomer;
import com.Talan.client.services.GetCustomerInformationService;
import com.Talan.client.services.GetCustomerInformationServiceImp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ClientServiceApplicationTests.class)
@EnableAutoConfiguration
public class ClientServiceApplicationTests {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceApplicationTests.class);
	
	 @TestConfiguration
	    static class ClientServiceImplTestContextConfiguration {
	  
	        @Bean
	        public GetCustomerInformationService customerInformationService() {
	            return new GetCustomerInformationServiceImp();
	        }
	    }
	
	@Autowired(required = true)
	private GetCustomerInformationService customerInformationService;
	
	
	@Test
	public void getCustomerInformationTest() {
		LOGGER.info("Début d'exécution de test unitaire de méthode getCustomerInformationTest ");
		CustomerInformation result = customerInformationService.getCustomerInformation("21625478569", FetchCustomer.Msisdn);
		LOGGER.info("Resultat: "+ result.toString());
		LOGGER.info("Fin d'exécution de test unitaire de méthode getCustomerInformationTest ");
		
	}
}
