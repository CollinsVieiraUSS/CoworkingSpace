package com.coworkingspace.users_service.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.coworkingspace.users_service.Entity.Role;


public interface RoleService {
	public List<Role> findAll(Pageable Page);
	public Role findByName(String name);
	public Role findById(int id);
	public Role Save(Role obj);
	public Role Delete(int id);
}
