package com.coworkingspace.reports_service.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.coworkingspace.reports_service.entity.LogReport;
import com.coworkingspace.reports_service.service.LogReportService;

@RestController
@RequestMapping("/v1/logs")
public class LogReportController {

	@Autowired
	private LogReportService service;

	// Obtener todos los logs paginados
	@GetMapping
	public ResponseEntity<List<LogReport>> getLogReport(
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<LogReport> data = service.findAll(page);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	// Obtener logs por report ID paginados
	@GetMapping("/{reportId}")
	public ResponseEntity<List<LogReport>> getLogsByReportId(@PathVariable Integer reportId,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<LogReport> data = service.findByLogReports(reportId, page);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	// Obtener un log por acción
	@GetMapping("/action")
	public ResponseEntity<LogReport> getLogByAction(@RequestParam(value = "action") String action,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		LogReport data = service.findByAction(action, page);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	// Obtener un log por ID
	@GetMapping("/{id}/detail")
	public ResponseEntity<LogReport> getLogById(@PathVariable Integer id) {
		LogReport data = service.findById(id);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	// Crear un nuevo log
	@PostMapping
	public ResponseEntity<LogReport> createLog(@RequestBody LogReport log) {
		LogReport data = service.save(log);
		return new ResponseEntity<>(data, HttpStatus.CREATED);
	}

	// Actualizar un log existente
	@PutMapping("/{id}")
	public ResponseEntity<LogReport> updateLog(@PathVariable Integer id, @RequestBody LogReport log) {
		log.setId(id);
		LogReport data = service.save(log);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	// Eliminar un log por ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLog(@PathVariable Integer id) {
		boolean isDeleted = service.delete(id);
		if (isDeleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
