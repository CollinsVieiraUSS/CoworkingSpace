package com.coworkingspace.reports_service.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.coworkingspace.reports_service.entity.Report;
import com.coworkingspace.reports_service.exception.GeneralServiceException;
import com.coworkingspace.reports_service.exception.NoDataFoundException;
import com.coworkingspace.reports_service.exception.ValidateServiceException;
import com.coworkingspace.reports_service.repository.ReportRepository;
import com.coworkingspace.reports_service.service.ReportService;
import com.coworkingspace.reports_service.validator.ReportValidator;

import org.springframework.transaction.annotation.Transactional;
@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepository repository;

	@Override
	@Transactional(readOnly = true)
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
	@Transactional
	public List<Report> findByReportType(String reportType, Pageable page) {
		try {
			List<Report> data = repository.findByReportType(reportType,page);
			return data;
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Report findById(int id) {
		try {
			Report data = repository.findById(id)
					.orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
			return data;
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Report save(Report obj) {
		try {
			ReportValidator.save(obj);
			if(obj.getId()==0) {
				Report data = repository.save(obj);
				return data;
			}
			else {
				Report data = findById(obj.getId());
				data.setReportType(obj.getReportType());
				data.setUserId(obj.getUserId());
				return repository.save(data);				
			}
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public boolean delete(int id) {
		try {
			Report data = findById(id);
			repository.delete(data);
			return true;
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional
	public List<Report> findByUserId(int id, Pageable page) {
		try {
			List<Report> logs = repository.findByUserId(id, page);
			return logs;
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

}
