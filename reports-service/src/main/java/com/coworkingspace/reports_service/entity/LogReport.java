package com.coworkingspace.reports_service.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "log_reports")
@EntityListeners(AuditingEntityListener.class)
public class LogReport {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	//Generacion de la dependencia uno a muchos
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_report")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Report report;
	
    @Column(name = "id_user")
    private int userId;
	
	@Column(name = "action", nullable = false, length = 50)
    private String action;
	
	@Column(name = "description", length = 500)
    private String description;
	
	@Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
}
