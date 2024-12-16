package com.coworkingspace.users_service.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coworkingspace.users_service.Entity.User;
import com.coworkingspace.users_service.Exception.GeneralServiceException;
import com.coworkingspace.users_service.Exception.NoDataFoundException;
import com.coworkingspace.users_service.Exception.ValidateServiceException;
import com.coworkingspace.users_service.Repository.UserRepository;
import com.coworkingspace.users_service.Service.UserService;
import com.coworkingspace.users_service.Validator.UserValidator;
import com.coworkingspace.users_service.client.ReportClient;
import com.coworkingspace.users_service.dto.ReportDTO;
import com.coworkingspace.users_service.http.response.ReportByUserResponse;

@Service
public class UserImplement implements UserService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private ReportClient reportClient;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll(Pageable Page) {
		// TODO Auto-generated method stub
		try {
			return repository.findAll(Page).toList();
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public User findByName(String name) {
		try {
			return repository.findByName(name);
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(int id) {
		try {
			return repository.findById(id)
					.orElseThrow(() -> new NoDataFoundException("No User found with the specified ID"));
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public User Save(User obj) {
		try {
			UserValidator.validate(obj);
			if (obj.getId() == 0) {
				return repository.save(obj);
			}
			User existingUser = findById(obj.getId());
			existingUser.setName(obj.getName());
			existingUser.setLastName(obj.getLastName());
			existingUser.setPhoneNumber(obj.getPhoneNumber());
			existingUser.setAddress(obj.getAddress());
			existingUser.setEmail(obj.getEmail());
			existingUser.setRole(obj.getRole());
			return repository.save(existingUser);
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	public User Delete(int id) {
		try {
			User user = findById(id);
			repository.delete(user);
			return user;
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	public ReportByUserResponse findReportsByIdUser(int userId) {
		try {
			// Consultamos el user
			User user = repository.findById(userId).orElse(new User());
			// Recuperamos los reportes del usuario
			List<ReportDTO> list = reportClient.findAllReportsByUser(userId);
			return ReportByUserResponse.builder()
					.name(user.getName())
					.lastName(user.getLastName())
					.phoneNumber(user.getPhoneNumber())
					.email(user.getEmail())
					.role(user.getRole())
					.reportDTOList(list)
					.build();
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

}
