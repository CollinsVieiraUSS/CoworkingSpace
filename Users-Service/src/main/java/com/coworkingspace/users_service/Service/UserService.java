package com.coworkingspace.users_service.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.coworkingspace.users_service.Entity.User;
import com.coworkingspace.users_service.http.response.ReportByUserResponse;

public interface UserService {
	public List<User> findAll(Pageable Page);

	public User findByName(String name);

	public User findById(int id);

	public User Save(User obj);

	public User Delete(int id);
	
	public ReportByUserResponse findReportsByIdUser( int idUser);

}
