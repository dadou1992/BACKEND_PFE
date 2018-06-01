package com.talan.appAuthenticationJWT.service;

import com.talan.appAuthenticationJWT.entities.AppRole;
import com.talan.appAuthenticationJWT.entities.AppUser;

public interface AccountService {
	public AppUser saveUser(AppUser user);

	public AppRole saveRole(AppRole role);

	public void addRoleToUser(String username, String roleName);

	public AppUser findUserByUsername(String username);
}