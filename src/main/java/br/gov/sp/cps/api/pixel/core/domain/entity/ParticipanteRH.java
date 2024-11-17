package br.gov.sp.cps.api.pixel.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dim_participante_rh")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipanteRH {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dim_participante_rh")
    private int idParticipanteRh;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cargo;
}
