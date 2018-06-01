package com.talan.appAuthenticationJWT.dao;

import com.talan.appAuthenticationJWT.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, Long> {
	public AppRole findByRoleName(String roleName);

}
