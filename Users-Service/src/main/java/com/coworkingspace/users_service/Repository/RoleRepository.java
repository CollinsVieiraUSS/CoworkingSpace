package com.coworkingspace.users_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coworkingspace.users_service.Entity.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {
	public Role findByName(String name);
}
