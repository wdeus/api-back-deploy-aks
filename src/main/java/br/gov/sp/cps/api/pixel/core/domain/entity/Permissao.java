package br.gov.sp.cps.api.pixel.core.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permissao")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPermissao;

    @Column(name = "chave")
    private String chave;

    @Column(name = "descricao")
    private String descricao;

    @ManyToMany(mappedBy = "permissoes", fetch = FetchType.EAGER)
    private List<Grupo> grupos;
}
