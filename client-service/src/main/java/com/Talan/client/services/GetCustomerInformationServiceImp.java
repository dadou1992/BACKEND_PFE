package com.Talan.client.services;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.soap.SOAPFaultException;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.stereotype.Service;

import com.Talan.client.model.CustomerInformation;
import com.Talan.client.model.FetchCustomer;
import com.example.xmlns._1361376195562.Error;
import com.example.xmlns._1361376195562.PortType;
import com.tunisiana.ws.bscs.getcustomer.GetCustomerRequest;
import com.tunisiana.ws.bscs.getcustomer.GetCustomerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

@Service
public class GetCustomerInformationServiceImp implements GetCustomerInformationService {
	private static final Logger logger = LoggerFactory.getLogger(GetCustomerInformationServiceImp.class);

	@Override
	public CustomerInformation getCustomerInformation(String value, FetchCustomer type) {

		// generate request, fill the GetCustomerRequest java object
		GetCustomerRequest getCustomerRequest = generateRequest(value, type);
		CustomerInformation customerInformation = new CustomerInformation();
		PortType client = createClient();
		GetCustomerResponse webServiceResponse = new GetCustomerResponse();
		try {
			webServiceResponse = client.getCustomerOperation(getCustomerRequest);

			if (webServiceResponse != null) {
				customerInformation = generateCustomerFromWebServiceResponse(webServiceResponse);
				return customerInformation;
			}
		} catch (SOAPFaultException e) {
			String errorMsg = "WS Technical Exception";
			customerInformation.setErrorMsg(errorMsg);
		} catch (Error e) {
			String msg = e.getFaultInfo().getErrorMessage();
			String code = e.getFaultInfo().getErrorMessageCode();
			customerInformation.setErrorMsg(msg);
			customerInformation.setErrorCode(code);
			e.printStackTrace();

		} catch (Exception e) {
			customerInformation.setErrorMsg("Server Exception");
		}
		return customerInformation;
	}

	private CustomerInformation generateCustomerFromWebServiceResponse(GetCustomerResponse webServiceResponse) {

		CustomerInformation customerInformation = new CustomerInformation();

		customerInformation.setCin((webServiceResponse.getIdentityInformation() != null)
				? webServiceResponse.getIdentityInformation().getCIN()
				: null);
		customerInformation.setCompanyRgister((webServiceResponse.getIdentityInformation() != null)
				? webServiceResponse.getIdentityInformation().getCompanyRegister()
				: null);
		customerInformation.setCustCode((webServiceResponse.getCustomerIdentifier() != null)
				? webServiceResponse.getCustomerIdentifier().getCustCode()
				: null);
		customerInformation
				.setDummy((webServiceResponse.isIsDummy() != null) ? webServiceResponse.isIsDummy().toString() : null);
		customerInformation
				.setfName((webServiceResponse.getPersonalnformation() != null)
						? ((webServiceResponse.getPersonalnformation().getIdentity() != null)
								? webServiceResponse.getPersonalnformation().getIdentity().getLName()
								: null)
						: null);
		customerInformation
				.setlName((webServiceResponse.getPersonalnformation() != null)
						? ((webServiceResponse.getPersonalnformation().getIdentity() != null)
								? webServiceResponse.getPersonalnformation().getIdentity().getFName()
								: null)
						: null);
		customerInformation.setPaymentResp(
				(webServiceResponse.isPaymentResp() != null) ? webServiceResponse.isPaymentResp().toString() : null);
		customerInformation.setReason(webServiceResponse.getReason());

		return customerInformation;
	}

	@SuppressWarnings("deprecation")
	private PortType createClient() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.setServiceClass(PortType.class);
		factory.setAddress("http://127.0.0.1:8088/mockws_CUSTOMER_EndpointBinding");

		Map<String, Object> properties = factory.getProperties();
		// to void null pointer exception
//		if (properties == null) {
//			properties = new HashMap<>();
//			factory.setProperties(properties);
//						
//		}
		
		if (properties == null) {
		properties = new HashMap<>();
		factory.setProperties(properties);
		logger.info("************ Request : ************" );
		factory.getInInterceptors().add(new LoggingInInterceptor());
		logger.info("************ Response : ************" );
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		}

		properties.put("set-jaxb-validation-event-handler", "false");

		PortType client = (PortType) factory.create();

		Client proxy = ClientProxy.getClient(client);
		HTTPConduit http = (HTTPConduit) proxy.getConduit();
		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
		httpClientPolicy.setAllowChunking(false);
		http.setClient(httpClientPolicy);
		AuthorizationPolicy authorizationPolicy = new AuthorizationPolicy();
		authorizationPolicy.setUserName("botool");
		authorizationPolicy.setPassword("botool");
		http.setAuthorization(authorizationPolicy);
		return client;
	}

	/**
	 * This method is used to generate request
	 * 
	 * @param value
	 * @return
	 */
	private GetCustomerRequest generateRequest(String value, FetchCustomer type) {
		GetCustomerRequest getCustomerRequest = new GetCustomerRequest();

		switch (type) {
		case Contract_ID:
			getCustomerRequest.setContractID(Long.parseLong(value));
			break;
		case CustCode:
			getCustomerRequest.setCustCode(value);
			break;
		case Msisdn:
			getCustomerRequest.setMsisdn(value);
			break;

		default:
			break;
		}

		return getCustomerRequest;
	}

}
