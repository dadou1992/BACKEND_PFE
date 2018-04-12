package com.Talan.authentification.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Talan.authentification.entities.AppUser;


public interface UserRepository extends JpaRepository<AppUser, Long> {
	AppUser findByUsername(String username);

}
