package com.coworkingspace.reports_service.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Arrays;
import java.util.Date;

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
@Table(name = "reports")
@EntityListeners(AuditingEntityListener.class)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "reportType", nullable = false, length = 100)
    private String reportType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user_generator", referencedColumnName = "id", nullable = false)
    //Relación con la tabla Usuario
    private User userGenerator; 

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    // Enum para validar los valores permitidos de tipoReporte
    public enum TipoReporte {
        USUARIOS,
        RESERVAS,
        PAGOS
    }

    public void setTipoReporte(String tipoReporte) {
        if (Arrays.stream(TipoReporte.values()).noneMatch(e -> e.name().equalsIgnoreCase(tipoReporte))) {
            throw new IllegalArgumentException("El tipo de reporte no es válido. Valores permitidos: USUARIOS, RESERVAS, PAGOS.");
        }
        this.reportType = tipoReporte.toUpperCase();
    }
}
