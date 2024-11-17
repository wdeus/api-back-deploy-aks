package br.gov.sp.cps.api.pixel.core.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class SalvarPermissoesGrupoDTO {
    private Long grupoId;
    private List<Integer> permissoesIds;
}
