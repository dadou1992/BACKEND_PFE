package com.Talan.authentification.service;

import com.Talan.authentification.entities.AppRole;
import com.Talan.authentification.entities.AppUser;

public interface AccountService {
	public AppUser saveUser(AppUser user);

	public AppRole saveRole(AppRole role);

	public void addRoleToUser(String username, String roleName);

	public AppUser findUserByUsername(String username);
}