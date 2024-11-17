package br.gov.sp.cps.api.pixel.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "dim_contratacao")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contratacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dim_contratacao")
    private int idContratacao;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtContratacao;

    @Column(nullable = false)
    private double salarioInicial;

    @Column(nullable = false)
    private int tipoContrato;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtAceiteOferta;
}
