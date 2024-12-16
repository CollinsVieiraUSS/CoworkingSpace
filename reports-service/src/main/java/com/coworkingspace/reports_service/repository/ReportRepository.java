package com.coworkingspace.reports_service.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coworkingspace.reports_service.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
	public List<Report> findByReportType(String reportType, Pageable page);

	public List<Report> findByUserId(Integer userId, Pageable page);
}
