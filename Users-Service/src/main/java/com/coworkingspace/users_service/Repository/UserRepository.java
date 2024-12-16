package com.coworkingspace.users_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coworkingspace.users_service.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByName(String nombre);
}
