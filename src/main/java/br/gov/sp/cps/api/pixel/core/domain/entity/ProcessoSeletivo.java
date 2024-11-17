package br.gov.sp.cps.api.pixel.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "dim_processo_seletivo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessoSeletivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dim_processo_seletivo")
    private int idProcessoSeletivo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtInicio;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtFim;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String criador;

    @Column(nullable = false)
    private String descricao;
}
