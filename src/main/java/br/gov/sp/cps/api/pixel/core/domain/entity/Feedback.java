package br.gov.sp.cps.api.pixel.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dim_feedback")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dim_feedback")
    private int idFeedback;

    @Column(nullable = false)
    @JsonProperty(value = "descricaoFeedback")
    private String descricao;
}
