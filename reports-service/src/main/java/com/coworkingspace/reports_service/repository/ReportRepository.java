package com.coworkingspace.reports_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coworkingspace.reports_service.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>{
	public Report findByTipoReporte(String tipoReporte);
}
