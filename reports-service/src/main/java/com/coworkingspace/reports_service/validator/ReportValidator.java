package com.coworkingspace.reports_service.validator;

import com.coworkingspace.reports_service.entity.Report;
import com.coworkingspace.reports_service.exception.ValidateServiceException;

public class ReportValidator {

	public static void save(Report obj) {
		if (obj.getReportType()==null || obj.getReportType().trim().isEmpty()) {
			throw new ValidateServiceException("El campo 'ReportType' es obligatorio");
		}
	}
}
