package com.talan.client.services;

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

import com.example.xmlns._1361376195562.Error;
import com.example.xmlns._1361376195562.PortType;
import com.talan.client.model.CustomerInformation;
import com.talan.client.model.FetchCustomer;
import com.tunisiana.ws.bscs.getcustomer.GetCustomerRequest;
import com.tunisiana.ws.bscs.getcustomer.GetCustomerResponse;

@Service
public class GetCustomerInformationServiceImp implements GetCustomerInformationService {

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
				System.out.println(customerInformation);
				return customerInformation;
			}
		} catch (Error e) {
			String msg = e.getFaultInfo().getErrorMessage();
			String code = e.getFaultInfo().getErrorMessageCode();
			customerInformation.setErrorMsg(msg);
			customerInformation.setErrorCode(code);
			e.printStackTrace();
		
		} catch (SOAPFaultException e) {
			String errorMsg = "WS Technical Exception";
			customerInformation.setErrorMsg(errorMsg);
		}catch (Exception e) {
			customerInformation.setErrorMsg("Server Exception");
		} /*catch (Error e) {
			customerInformation.setErrorMsg(e.getFaultInfo().getErrorMessage());
			customerInformation.setErrorMsg(e.getFaultInfo().getErrorMessageCode());
			e.printStackTrace();
		}*/
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

	private PortType createClient() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.setServiceClass(PortType.class);
		factory.setAddress("http://127.0.0.1:8088/mockws_CUSTOMER_EndpointBinding");

		Map<String, Object> properties = factory.getProperties();
		// to void null pointer exception
		if (properties == null) {
			properties = new HashMap<>();
			factory.setProperties(properties);
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
