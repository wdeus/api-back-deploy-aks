package br.gov.sp.cps.api.pixel.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fato_entrevista")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FatoEntrevista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFatoEntrevista;

    @ManyToOne
    @JoinColumn(name = "id_dim_entrevista", nullable = false)
    @JsonIgnore
    private Entrevista entrevista;

    @ManyToOne
    @JoinColumn(name = "id_dim_vaga", nullable = false)
    @JsonIgnore
    private Vaga vaga;

    @ManyToOne
    @JoinColumn(name = "id_dim_feedback")
    @JsonIgnore
    private Feedback feedback;

    @ManyToOne
    @JoinColumn(name = "id_dim_acao_seletiva")
    @JsonIgnore
    private AcaoSeletiva acaoSeletiva;

    @ManyToOne
    @JoinColumn(name = "id_dim_participante_rh")
    @JsonIgnore
    private ParticipanteRH participanteRh;

    @ManyToOne
    @JoinColumn(name = "id_dim_contratacao", nullable = false)
    @JsonIgnore
    private Contratacao contratacao;

    @JsonProperty("nrEntrevistas")
    @Column(nullable = false)
    private int nrEntrevistas;

    @JsonProperty("nrContratacoes")
    @Column(nullable = false)
    private int nrContratacoes;
}
