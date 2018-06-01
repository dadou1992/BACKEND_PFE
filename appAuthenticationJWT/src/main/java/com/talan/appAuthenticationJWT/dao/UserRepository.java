package com.talan.appAuthenticationJWT.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talan.appAuthenticationJWT.entities.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
	AppUser findByUsername(String username);

}
