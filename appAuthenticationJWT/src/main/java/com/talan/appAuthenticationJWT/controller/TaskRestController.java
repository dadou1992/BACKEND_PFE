package com.talan.appAuthenticationJWT.controller;

import java.util.List;

import com.talan.appAuthenticationJWT.dao.TaskRepository;
import com.talan.appAuthenticationJWT.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskRestController {
	@Autowired // ou on peut faire l'injection avec un constructeur avec param
				// this.taskrepo=taskrepo
	private TaskRepository taskRepository;

	@GetMapping("/allTasks")
	public List<Task> listTasks() {
		return taskRepository.findAll();
	}

	@PostMapping("/addTask")
	public Task save(@RequestBody Task t) {
		return taskRepository.save(t);
	}

}
