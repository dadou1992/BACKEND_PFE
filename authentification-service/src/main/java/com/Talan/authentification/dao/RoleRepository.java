package com.Talan.authentification.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Talan.authentification.entities.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, Long> {
	public AppRole findByRoleName(String roleName);

}
