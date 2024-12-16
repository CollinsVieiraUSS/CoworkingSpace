package com.coworkingspace.users_service.Validator;

import com.coworkingspace.users_service.Entity.Role;
import com.coworkingspace.users_service.Exception.ValidateServiceException;

public class RoleValidator {
    public static void save(Role obj) {
        if (obj.getName() == null || obj.getName().trim().isEmpty()) {
            throw new ValidateServiceException("The role name is required");
        }
        if (obj.getName().length() > 50) {
            throw new ValidateServiceException("The role name must not exceed 50 characters");
        }
    }
}
