package br.gov.sp.cps.api.pixel.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "dim_entrevista")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entrevista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dim_entrevista")
    private int idEntrevista;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtEntrevista;

    @Column(nullable = false)
    private String entrevistador;

    @Column(nullable = false)
    private String resultado;
}
