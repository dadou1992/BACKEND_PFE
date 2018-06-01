package com.talan.appAuthenticationJWT.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talan.appAuthenticationJWT.entities.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {

}
