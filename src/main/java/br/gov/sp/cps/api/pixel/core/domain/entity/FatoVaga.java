package br.gov.sp.cps.api.pixel.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fato_vaga")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FatoVaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFatoVaga;

    @ManyToOne
    @JoinColumn(name = "id_dim_vaga", nullable = false)
    @JsonIgnore
    private Vaga vaga;

    @ManyToOne
    @JoinColumn(name = "id_dim_processo_seletivo", nullable = false)
    @JsonIgnore
    private ProcessoSeletivo processoSeletivo;

    @ManyToOne
    @JoinColumn(name = "id_dim_periodo", nullable = false)
    @JsonIgnore
    private Periodo periodo;

    @JsonProperty("nrPosicoesAbertas")
    @Column(nullable = false)
    private int nrPosicoesAbertas;

    @JsonProperty("nrCandidatosInscritos")
    @Column(nullable = false)
    private int nrCandidatosInscritos;

    @JsonProperty("salarioInicialMedio")
    @Column(nullable = false)
    private double salarioInicialMedio;

    @JsonProperty("tempoMedioProcesso")
    @Column(nullable = false)
    private int tempoMedioProcesso;
}
