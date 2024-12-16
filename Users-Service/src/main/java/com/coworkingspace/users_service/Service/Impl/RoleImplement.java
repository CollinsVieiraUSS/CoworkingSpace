package com.coworkingspace.users_service.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coworkingspace.users_service.Entity.Role;
import com.coworkingspace.users_service.Exception.GeneralServiceException;
import com.coworkingspace.users_service.Exception.NoDataFoundException;
import com.coworkingspace.users_service.Exception.ValidateServiceException;
import com.coworkingspace.users_service.Repository.RoleRepository;
import com.coworkingspace.users_service.Service.RoleService;
import com.coworkingspace.users_service.Validator.RoleValidator;


@Service

public class RoleImplement implements RoleService{
    @Autowired
    private RoleRepository repository;

    @Override
    @Transactional(readOnly = true)
	public List<Role> findAll(Pageable Page) {
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
	public Role findByName(String name) {
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
	public Role findById(int id) {
		try {
            return repository.findById(id).orElseThrow(
                () -> new NoDataFoundException("No role found with the specified ID")
            );
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
	}

	@Override
	@Transactional
	public Role Save(Role obj) {
		try {
            RoleValidator.save(obj);
            if (obj.getId() == 0) {
            	Role rolenew=repository.save(obj);
                return rolenew;
            }
            Role existingRole = findById(obj.getId());
            existingRole.setName(obj.getName());
            return repository.save(existingRole);
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
	}

	@Override
	public Role Delete(int id) {
		try {
            Role role = findById(id);
            repository.delete(role);
            return role;
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
	}
}
