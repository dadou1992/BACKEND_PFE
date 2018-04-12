package com.Talan.history.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Talan.history.entities.History;
import com.Talan.history.service.HistoryService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@CrossOrigin("*")
@RefreshScope
@RestController
public class HistoryRestController {
	
	@Autowired
	private HistoryService historyService;
	
	 private static Logger log = LoggerFactory.getLogger(HistoryRestController.class);

	
	@CrossOrigin("*")
	@HystrixCommand(fallbackMethod="defaultOperations",commandKey ="OperationsDetails",
	groupKey="journalisation-service",commandProperties= {
			@HystrixProperty (name="circuitBreaker.sleepWindowInMilliseconds",value="60000"),
			@HystrixProperty (name="circuitBreaker.errorThresholdPercentage",value="5"),

	})
	@RequestMapping(value="/operations",method=RequestMethod.GET)
	public List<History>getOperations(){
		log.info("Exécution de la méthode getOperations ");

		return historyService.findAll();
	}
	
	
	@CrossOrigin("*")
	@HystrixCommand(fallbackMethod="defaultOperation",commandKey ="OperationDetails",
	groupKey="journalisation-service",commandProperties= {
			@HystrixProperty (name="circuitBreaker.sleepWindowInMilliseconds",value="60000"),
			@HystrixProperty (name="circuitBreaker.errorThresholdPercentage",value="5"),

	})
	@RequestMapping(value="/operation/{id}",method=RequestMethod.GET)
	public History getOperation(@PathVariable String id){
		return historyService.findOne(id);
	}
	
	
	
	private History defaultOperation(String id) {
		History op = new History("test", "test", "test", (long) 12, "test", "test", "ok");
		return op;
		
	}
	
	private List<History> defaultOperations() {
		History op1 = new History("test", "test", "test", (long) 12, "test", "test", "ok");
		History op2 = new History("test2", "test2", "test2", (long) 1289, "test", "test2", "ok");
		Collection liste = new LinkedList<>();
		liste.add(op1);
		liste.add(op2);
		return (List<History>) liste;
		
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/addOperation",method=RequestMethod.POST)
	public History Save(@RequestBody History c){
		// IP
		InetAddress IP = null;
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		c.setUserIP(IP.toString());
		
		//date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		c.setOperationDate(dateFormat.format(date));
		log.info("Exécution de la méthode SaveOperation ");


		return historyService.save(c);
	}


	
	

}
