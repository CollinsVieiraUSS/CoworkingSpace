package com.coworkingspace.reports_service.service;

import java.util.List;
import org.springframework.data.domain.Pageable;

import com.coworkingspace.reports_service.entity.LogReport;


public interface LogReportService {
	public List<LogReport> findAll(Pageable page);
	public LogReport findByAction(String action,Pageable page);
	public List<LogReport> findByLogReports(int id,Pageable page);
	public LogReport findById(int id);
	public LogReport save(LogReport obj);
	public boolean delete(int id);

}
