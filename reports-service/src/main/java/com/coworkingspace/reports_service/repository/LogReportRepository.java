package com.coworkingspace.reports_service.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coworkingspace.reports_service.entity.LogReport;

@Repository
public interface LogReportRepository extends JpaRepository<LogReport, Integer>{
	public LogReport findByAction(String action);
	
	public List<LogReport> findByReportId(Integer reportId, Pageable page);
}
