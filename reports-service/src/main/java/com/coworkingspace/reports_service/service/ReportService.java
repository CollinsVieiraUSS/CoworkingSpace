package com.coworkingspace.reports_service.service;

import java.util.List;
import org.springframework.data.domain.Pageable;

import com.coworkingspace.reports_service.entity.Report;

public interface ReportService {
	public List<Report> findAll(Pageable page);

	public List<Report> findByReportType(String reportType, Pageable page);

	public Report findById(int id);;
	
	public Report save(Report obj);

	public boolean delete(int id);

	public List<Report> findByUserId(int id, Pageable page);

}
