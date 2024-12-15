package com.coworkingspace.reports_service.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coworkingspace.reports_service.entity.Report;
import com.coworkingspace.reports_service.service.ReportService;

@RestController
public class ReportController {
	@Autowired
	private ReportService service;
	
	@GetMapping
	public ResponseEntity<List<Report>> getReport(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
				Pageable page = PageRequest.of(pageNumber, pageSize);
				List<Report> data = service.findAll(page);
				return new ResponseEntity<>(data,HttpStatus.OK);	
	}

}
