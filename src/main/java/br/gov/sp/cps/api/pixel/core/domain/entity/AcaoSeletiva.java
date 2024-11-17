package br.gov.sp.cps.api.pixel.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dim_acao_seletiva")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcaoSeletiva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dim_acao_seletiva")
    private int idAcao;

    @JsonProperty("tipoAcaoSeletiva")
    private String tipo;

    @JsonProperty("dtAcaoSeletiva")
    private Date dtHora;
}
