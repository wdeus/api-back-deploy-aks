package br.gov.sp.cps.api.pixel.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dim_vaga")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dim_vaga")
    private int idVaga;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private int nrPosicoes;

    @Column(nullable = false)
    private String requisitos;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String localizacao;

    @Column(nullable = false)
    private String responsavel;
}
