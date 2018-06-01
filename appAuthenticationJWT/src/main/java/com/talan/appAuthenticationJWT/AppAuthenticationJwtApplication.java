package com.talan.appAuthenticationJWT;

import com.talan.appAuthenticationJWT.entities.AppRole;
import com.talan.appAuthenticationJWT.entities.AppUser;
import com.talan.appAuthenticationJWT.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.talan.appAuthenticationJWT.dao.TaskRepository;

@SpringBootApplication
public class AppAuthenticationJwtApplication implements CommandLineRunner {
	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(AppAuthenticationJwtApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... arg0) throws Exception {

		/*accountService.saveUser(new AppUser("admin", "admin", null));
		 accountService.saveUser(new AppUser("agent", "agent", null));
		accountService.saveRole(new AppRole("ADMIN"));
		accountService.saveRole(new AppRole("AGENT"));
		accountService.addRoleToUser("admin", "ADMIN");
		accountService.addRoleToUser("admin", "AGENT");
		accountService.addRoleToUser("agent", "AGENT");*/

		taskRepository.findAll().forEach(t -> {
			System.out.println(t.getTaskName());
		});
	}
}
