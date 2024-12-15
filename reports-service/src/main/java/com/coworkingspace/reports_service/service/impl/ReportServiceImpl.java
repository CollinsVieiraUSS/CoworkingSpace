package com.coworkingspace.reports_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.coworkingspace.reports_service.entity.Report;
import com.coworkingspace.reports_service.exception.GeneralServiceException;
import com.coworkingspace.reports_service.exception.NoDataFoundException;
import com.coworkingspace.reports_service.exception.ValidateServiceException;
import com.coworkingspace.reports_service.repository.ReportRepository;
import com.coworkingspace.reports_service.service.ReportService;
import org.springframework.transaction.annotation.Transactional;

public class ReportServiceImpl implements ReportService{

	@Autowired
	private ReportRepository repository;
	
	@Override
	public List<Report> findAll(Pageable page) {
		try {
			List<Report> data = repository.findAll(page).toList();
			return data;

		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Report findByReportType(String reportType, Pageable page) {
		return null;
	}

	@Override
	public Report findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Report save(Report obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
