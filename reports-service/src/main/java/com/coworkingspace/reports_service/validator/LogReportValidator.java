package com.coworkingspace.reports_service.validator;

import com.coworkingspace.reports_service.entity.LogReport;
import com.coworkingspace.reports_service.exception.ValidateServiceException;

public class LogReportValidator {

	public static void save(LogReport obj) {
		if (obj.getAction()==null || obj.getAction().trim().isEmpty()) {
			throw new ValidateServiceException("El campo 'Action' es obligatorio");
		}
	}
}
