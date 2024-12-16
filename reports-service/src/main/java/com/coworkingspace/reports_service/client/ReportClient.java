package com.coworkingspace.reports_service.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service",url="localhost:8081/v1/users")
public interface ReportClient {

}
