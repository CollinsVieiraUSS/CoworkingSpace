package com.coworkingspace.reports_service.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coworkingspace.reports_service.entity.Report;
import com.coworkingspace.reports_service.service.ReportService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RequestMapping("/v1/reports")
public class ReportController {

    @Autowired
    private ReportService service;

    // Obtener todos los reports paginados
    @GetMapping
    public ResponseEntity<List<Report>> getAllReports(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Report> data = service.findAll(page);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // Obtener reports por tipo paginados
    @GetMapping("/type/{reportType}")
    public ResponseEntity<List<Report>> getReportsByType(
            @PathVariable("reportType") String reportType,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Report> data = service.findByReportType(reportType, page);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // Obtener un report por ID
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable("id") int id) {
        Report data = service.findById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // Crear o actualizar un report
    @PostMapping
    public ResponseEntity<Report> saveReport(@RequestBody Report report) {
        Report data = service.save(report);
        if (report.getId() == 0) {
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
    }

    // Eliminar un report por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable("id") int id) {
        boolean isDeleted = service.delete(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtener reports por User ID paginados
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Report>> getReportsByUserId(
            @PathVariable("userId") int userId,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Report> data = service.findByUserId(userId, page);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
