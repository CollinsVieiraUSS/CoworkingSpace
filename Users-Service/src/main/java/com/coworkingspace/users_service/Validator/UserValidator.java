package com.coworkingspace.users_service.Validator;

import com.coworkingspace.users_service.Entity.User;
import com.coworkingspace.users_service.Exception.ValidateServiceException;

public class UserValidator {
	 public static void validate(User obj) {
	        if (obj.getName() == null || obj.getName().trim().isEmpty()) {
	            throw new ValidateServiceException("The name is required");
	        }
	        if (obj.getName().length() >= 100) {
	            throw new ValidateServiceException("The name must not exceed 100 characters");
	        }
	        if (obj.getEmail() == null || obj.getEmail().trim().isEmpty()) {
	            throw new ValidateServiceException("The email is required");
	        }
	        if (!obj.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
	            throw new ValidateServiceException("The email format is invalid");
	        }
	        if (obj.getPhoneNumber() != null && obj.getPhoneNumber().length() > 9) {
	            throw new ValidateServiceException("The phone number must not exceed 15 characters");
	        }
	    }
}
