package br.gov.sp.cps.api.pixel.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "dim_periodo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Periodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dim_periodo")
    private int idPeriodo;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtAbertura;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtFechamento;
}
