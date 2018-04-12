package com.Talan.gestionUtilisateur.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Talan.gestionUtilisateur.dao.UserRepository;
import com.Talan.gestionUtilisateur.entities.BotoolUser;
import com.Talan.gestionUtilisateur.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RefreshScope
public class UserRestController {
	private static Logger log = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@CrossOrigin("*")
	@HystrixCommand(fallbackMethod = "defaultUsers", commandKey = "usersDetails", groupKey = "user-service", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "5"),

	})
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<BotoolUser> getUsers() {
		log.info("Exécution de la méthode getUsers ");

		return userService.findAll();
	}

	@CrossOrigin("*")
	@HystrixCommand(fallbackMethod = "defaultUser", commandKey = "userDetails", groupKey = "user-service", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "5"),

	})
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public BotoolUser getUser(@PathVariable Long id) {
		log.info("Exécution de la méthode getUser ");

		return userService.findOne(id);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/findUsers", method = RequestMethod.GET)
	public Page<BotoolUser> findUser(@RequestParam(name = "mc", defaultValue = "") String mc,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {

		return userRepository.findUser("%" + mc + "%", new PageRequest(page, size));
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public BotoolUser Save(@RequestBody BotoolUser c) {
		String hashPW = bCryptPasswordEncoder.encode(c.getPassword());
		c.setPassword(hashPW);
		log.info("Exécution de la méthode addUser ");
		return userService.save(c);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
	public void DeleteUser(@PathVariable Long id) {
		log.info("Exécution de la méthode deleteUser ");
		userService.delete(id);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/editUser/{id}", method = RequestMethod.PUT)
	public BotoolUser Save(@PathVariable Long id, @RequestBody BotoolUser c) {
		c.setUserId(id);
		log.info("Exécution de la méthode editUser ");
		return userService.Update(c);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/editUserProfil/{id}", method = RequestMethod.PUT)
	public BotoolUser UpdateProfil(@PathVariable Long id, @RequestBody BotoolUser c) {
		c.setUserId(id);
		return userService.save(c);
	}

	private BotoolUser defaultUser(Long id) {
		BotoolUser user = new BotoolUser("123", "test", "test", "donia", "hammami", null, null);
		user.setUserId(id);
		return user;

	}

	public List<BotoolUser> defaultUsers() {
		BotoolUser user1 = new BotoolUser("123", "test", "test", "donia", "hammami", null, null);
		BotoolUser user2 = new BotoolUser("1235", "test1", "test1", "abir", "hammami", null, null);

		Collection liste = new LinkedList<>();
		liste.add(user1);
		liste.add(user2);
		return (List<BotoolUser>) liste;

	}

}
