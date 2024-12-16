package com.coworkingspace.users_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.coworkingspace.users_service.dto.ReportDTO;

@FeignClient(name = "reports-service", url = "http://localhost:8086/v1/reports/")
public interface ReportClient {
	
	@GetMapping("/user/{userId}")
	public List<ReportDTO> findAllReportsByUser(@PathVariable("userId") int userId);
}


