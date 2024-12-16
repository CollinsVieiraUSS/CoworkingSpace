package com.coworkingspace.users_service.http.response;

import java.util.List;

import com.coworkingspace.users_service.Entity.Role;
import com.coworkingspace.users_service.dto.ReportDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportByUserResponse {
	private String name;
	private String lastName;
	private String phoneNumber;
	private String email;
	private Role role;
	List<ReportDTO> reportDTOList;
}
