package com.coworkingspace.reports_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coworkingspace.reports_service.entity.LogReport;
import com.coworkingspace.reports_service.exception.GeneralServiceException;
import com.coworkingspace.reports_service.exception.NoDataFoundException;
import com.coworkingspace.reports_service.exception.ValidateServiceException;
import com.coworkingspace.reports_service.repository.LogReportRepository;
import com.coworkingspace.reports_service.service.LogReportService;
import com.coworkingspace.reports_service.validator.LogReportValidator;

import org.springframework.transaction.annotation.Transactional;
@Service
public class LogReportServiceImpl implements LogReportService {

	@Autowired
	private LogReportRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<LogReport> findAll(Pageable page) {
		try {
			List<LogReport> data = repository.findAll(page).toList();
			return data;

		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public LogReport findByAction(String action, Pageable page) {
		try {
			LogReport data = repository.findByAction(action);
			return data;
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	@Override
	@Transactional
	public LogReport findById(int id) {
		try {
			LogReport data = repository.findById(id)
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
	public LogReport save(LogReport obj) {
		try {
			LogReportValidator.save(obj);
			if(obj.getId()==0) {
				LogReport data = repository.save(obj);
				return data;
			}
			else {
				LogReport data = findById(obj.getId());
				data.setAction(obj.getAction());
				data.setDescription(obj.getDescription());
				data.setReport(obj.getReport());
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
			LogReport data = findById(id);
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
	public List<LogReport> findByLogReports(int id, Pageable page) {
		try {
			List<LogReport> logs = repository.findByReportId(id, page);
			return logs;
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

}
